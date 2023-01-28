import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarApp extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel klienciPanel;
    private JPanel samochodyPanel;
    public JTable tableKlienci;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldEmail;
    private JTextField textFieldNrTel;
    private JTextField textFieldAdres;
    private JButton dodajButton;
    private JButton usunButton;
    private JButton zapiszButton;
    private JButton wyrobKarteButton;
    private JButton odswiezButton;
    private JButton dodajSamochodButton;
    private JButton usunButton1;
    private JButton zamowButton;
    private JTable tableSamochody;
    private JButton odswiezButton1;
    Configuration config = new Configuration("jdbc:mysql://localhost/car_rental", "root", "");
    String host = config.host;
    String username = config.username;
    String password = config.password;
    public static String ID_samochodu;

    public static void main(String[] args) {
        CarApp carApp1 = new CarApp();
        carApp1.setVisible(true);
    }

    public CarApp(){
        super("CarApp");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,500);

        DefaultTableModel model = createTableKlienci();
        DefaultTableModel modelCars = createTableSamochody();

        dodajButton.addActionListener(e -> {
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String email = textFieldEmail.getText();
            String nrTel = textFieldNrTel.getText();
            String adres = textFieldAdres.getText();

                if (imie.equals("") || nazwisko.equals("") || email.equals("") || nrTel.equals("") || adres.equals("")){
                    JOptionPane.showMessageDialog(null,"Wypełnij wszystkie pola!");
                } else if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(null,"Najprawdopodobniej wprowadziłeś zły adres e-mail");
                } else {
                    try {
                        DatabaseQuery db = new DatabaseQuery(host,username,password);
                        try {
                            db.addRecord(imie,nazwisko,email,nrTel,adres);
                            String[] newClient = {imie, nazwisko, email, nrTel, adres};
                            DefaultTableModel model12 = (DefaultTableModel)tableKlienci.getModel();
                            model12.addRow(newClient);
                        }
                        catch (SQLException ex){
                            JOptionPane.showMessageDialog(null,"Nie udało się wprowadzić klienta do bazy danych.");
                        }
                    }
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Nie udało się połączyć z bazą danych.");
                    }
                    textFieldImie.setText("");
                    textFieldNazwisko.setText("");
                    textFieldEmail.setText("");
                    textFieldNrTel.setText("");
                    textFieldAdres.setText("");
                }
            });
        zapiszButton.addActionListener(e -> {
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String email = textFieldEmail.getText();
            String nrTel = textFieldNrTel.getText();
            String adres = textFieldAdres.getText();
            if (imie.equals("") || nazwisko.equals("") || email.equals("") || nrTel.equals("") || adres.equals("")){
                JOptionPane.showMessageDialog(null,"Wypełnij wszystkie pola!");
            } else if (!email.contains("@")) {
                JOptionPane.showMessageDialog(null,"Najprawdopodobniej wprowadziłeś zły adres e-mail");
            } else {
                try {
                    DatabaseQuery db = new DatabaseQuery(host, username, password);
                    try {
                        db.editRecord(imie, nazwisko, email, nrTel, adres);
                        DefaultTableModel model1 = (DefaultTableModel) tableKlienci.getModel();
                        model1.setValueAt(imie, tableKlienci.getSelectedRow(), 0);
                        model1.setValueAt(nazwisko, tableKlienci.getSelectedRow(), 1);
                        model1.setValueAt(email, tableKlienci.getSelectedRow(), 2);
                        model1.setValueAt(nrTel, tableKlienci.getSelectedRow(), 3);
                        model1.setValueAt(adres, tableKlienci.getSelectedRow(), 4);
                        for (int i = 0; i <= 4; i++) {
                            model1.fireTableCellUpdated(tableKlienci.getSelectedRow(), i);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Nie udało się edytować danych klienta.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
                }
            }
            textFieldImie.setText("");
            textFieldNazwisko.setText("");
            textFieldEmail.setText("");
            textFieldNrTel.setText("");
            textFieldAdres.setText("");
        });
        usunButton.addActionListener(e -> {
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String email = textFieldEmail.getText();
            String nrTel = textFieldNrTel.getText();
            String adres = textFieldAdres.getText();
            if (imie.equals("") && nazwisko.equals("") && email.equals("") && nrTel.equals("") && adres.equals("")) {
                JOptionPane.showMessageDialog(null, "Wybierz klienta");
            } else {
                try {
                    DatabaseQuery db = new DatabaseQuery(host, username, password);
                    try {
                        db.deleteRecord(imie, nazwisko, email, nrTel, adres);
                        DefaultTableModel model13 = (DefaultTableModel) tableKlienci.getModel();
                        model13.removeRow(tableKlienci.getSelectedRow());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Nie udało się usunąć klienta z bazy danych.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Nie udało się połączyć z bazą danych.");
                }
                textFieldImie.setText("");
                textFieldNazwisko.setText("");
                textFieldEmail.setText("");
                textFieldNrTel.setText("");
                textFieldAdres.setText("");
            }
        });
        wyrobKarteButton.addActionListener(e -> {
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            if (imie.equals("") || nazwisko.equals("")){
                JOptionPane.showMessageDialog(null, "Wybierz klienta!");
            }else {
                KartaKlienta kartaKlienta = new KartaKlienta(imie, nazwisko);
                kartaKlienta.setVisible(true);
            }
        });
        tableKlienci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableKlienci.rowAtPoint(e.getPoint());
                String imie = tableKlienci.getModel().getValueAt(row,0).toString();
                String nazwisko = tableKlienci.getModel().getValueAt(row,1).toString();
                String email = tableKlienci.getModel().getValueAt(row,2).toString();
                String nrTel = tableKlienci.getModel().getValueAt(row,3).toString();
                String adres = tableKlienci.getModel().getValueAt(row,4).toString();
                textFieldImie.setText(imie);
                textFieldNazwisko.setText(nazwisko);
                textFieldEmail.setText(email);
                textFieldNrTel.setText(nrTel);
                textFieldAdres.setText(adres);
            }
        });

        odswiezButton.addActionListener(e -> {
            createTableKlienci();
            textFieldImie.setText("");
            textFieldNazwisko.setText("");
            textFieldEmail.setText("");
            textFieldNrTel.setText("");
            textFieldAdres.setText("");
        });
        dodajSamochodButton.addActionListener(e -> {
            CarAdding carAdding = new CarAdding();
            carAdding.setVisible(true);
        });
        odswiezButton1.addActionListener(e -> createTableSamochody());
        usunButton1.addActionListener(e -> {
            try {
                DatabaseQuery db = new DatabaseQuery(host, username, password);
                try {
                    db.deleteCar(ID_samochodu);
                    DefaultTableModel model14 = (DefaultTableModel) tableKlienci.getModel();
                    model14.removeRow(tableKlienci.getSelectedRow());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Nie udało się usunąć samochodu z bazy danych.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Nie udało się połączyć z bazą danych.");
            }
        });

        zamowButton.addActionListener(e -> {
            Order order = new Order();
            order.setVisible(true);
        });

        tableSamochody.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableSamochody.rowAtPoint(e.getPoint());
                CarApp.ID_samochodu = tableSamochody.getModel().getValueAt(row,0).toString();
                System.out.println();
            }
        });
    }

    public DefaultTableModel createTableKlienci() {
        Database clientsDatabase = new Database(host, username, password);
        ArrayList<Client> clientList = clientsDatabase.getClients();

        String[] columns = {"Imie", "Nazwisko", "Email",  "Numer Telefonu", "Adres", "Numer Karty"};

        String[][] rows = new String[clientList.size()][6];

        int i=0;
        for (Client e : clientList) {
            if (e instanceof RegularClient) {
                rows[i][0] = e.getImie();
                rows[i][1] = e.getNazwisko();
                rows[i][2] = e.getEmail();
                rows[i][3] = e.getNrTel();
                rows[i][4] = e.getAdres();
                rows[i][5] = String.valueOf(((RegularClient) e).getNrKarty());
            } else {
                rows[i][0] = e.getImie();
                rows[i][1] = e.getNazwisko();
                rows[i][2] = e.getEmail();
                rows[i][3] = e.getNrTel();
                rows[i][4] = e.getAdres();
            }
                i++;
            }

        DefaultTableModel model = new DefaultTableModel(rows, columns);
        tableKlienci.setModel(model);
        tableKlienci.getColumnModel().getColumn(2).setPreferredWidth(140);
        tableKlienci.getColumnModel().getColumn(5).setPreferredWidth(20);
        return model;
    }

    public DefaultTableModel createTableSamochody() {
        Database carsDatabase = new Database(host, username, password);
        ArrayList<Car> carList = carsDatabase.getCars();

        String[] columns = {"ID_Samochodu", "Marka", "Model", "ID_klienta"};

        String[][] rows = new String[carList.size()][4];

        int i=0;
        for (Car c : carList) {
            if (c != null) {
                rows[i][0] = String.valueOf(c.getID_samochodu());
                rows[i][1] = c.getMarka();
                rows[i][2] = c.getModel();
                rows[i][3] = String.valueOf(c.getID_klienta());
                i++;
            }
        }

        DefaultTableModel modelCars = new DefaultTableModel(rows, columns);
        tableSamochody.setModel(modelCars);
        return modelCars;
    }
}
