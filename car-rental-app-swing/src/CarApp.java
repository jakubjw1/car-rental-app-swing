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
    private JButton usuńButton;
    private JButton zapiszButton;
    private JTable tableSamochody;
    private JButton button1;
    private JButton wyróbKartęButton;
    private JButton odswiezButton;
    Configuration config = new Configuration("jdbc:mysql://localhost/car_rental", "root", "");
    String host = config.host;
    String username = config.username;
    String password = config.password;

    public static void main(String[] args) {
        CarApp carApp1 = new CarApp();
        carApp1.setVisible(true);
    }

    public CarApp(){
        super("CarApp");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,500);

        DefaultTableModel model = createTable();

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                                DefaultTableModel model = (DefaultTableModel)tableKlienci.getModel();
                                model.addRow(newClient);
                            }
                            catch (SQLException ex){
                                System.err.println(ex);
                                JOptionPane.showMessageDialog(null,"Nie udało się wprowadzić klienta do bazy danych.");
                            }
                        }
                        catch (SQLException ex) {
                            System.err.println(ex);
                            JOptionPane.showMessageDialog(null, "Nie udało się połączyć z bazą danych.");
                        }
                        textFieldImie.setText("");
                        textFieldNazwisko.setText("");
                        textFieldEmail.setText("");
                        textFieldNrTel.setText("");
                        textFieldAdres.setText("");
                    }
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
                        System.err.println(ex);
                        JOptionPane.showMessageDialog(null,"Nie udało się edytować danych klienta.");
                    }
                } catch (SQLException ex) {
                    System.err.println(ex);
                    JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
                }
            }
            textFieldImie.setText("");
            textFieldNazwisko.setText("");
            textFieldEmail.setText("");
            textFieldNrTel.setText("");
            textFieldAdres.setText("");
        });
        usuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imie = textFieldImie.getText();
                String nazwisko = textFieldNazwisko.getText();
                String email = textFieldEmail.getText();
                String nrTel = textFieldNrTel.getText();
                String adres = textFieldAdres.getText();
                try {
                    DatabaseQuery db = new DatabaseQuery(host,username,password);
                    try {
                        db.deleteRecord(imie,nazwisko,email,nrTel,adres);
                        DefaultTableModel model = (DefaultTableModel) tableKlienci.getModel();
                        model.removeRow(tableKlienci.getSelectedRow());
                    } catch (SQLException ex){
                        System.err.println(ex);
                        JOptionPane.showMessageDialog(null,"Nie udało się usunąć klienta z bazy danych.");
                    }
                } catch (SQLException ex){
                    System.err.println(ex);
                    JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
                }
                textFieldImie.setText("");
                textFieldNazwisko.setText("");
                textFieldEmail.setText("");
                textFieldNrTel.setText("");
                textFieldAdres.setText("");
            }
        });
        wyróbKartęButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imie = textFieldImie.getText();
                String nazwisko = textFieldNazwisko.getText();
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

        odswiezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database clientsDatabase = new Database(host, username, password);
                ArrayList<Client> clientList = clientsDatabase.getClients();

                String[] columns = {"Imie", "Nazwisko", "Email",  "Numer Telefonu", "Adres", "Numer Karty"};

                String[][] rows = new String[clientList.size()][6];

                int i=0;
                for (Client c : clientList) {
                    if (c instanceof RegularClient) {
                        rows[i][0] = c.getImie();
                        rows[i][1] = c.getNazwisko();
                        rows[i][2] = c.getEmail();
                        rows[i][3] = c.getNrTel();
                        rows[i][4] = c.getAdres();
                        rows[i][5] = String.valueOf(((RegularClient) c).getNrKarty());
                    } else {
                        rows[i][0] = c.getImie();
                        rows[i][1] = c.getNazwisko();
                        rows[i][2] = c.getEmail();
                        rows[i][3] = c.getNrTel();
                        rows[i][4] = c.getAdres();
                    }
                    i++;
                }

                DefaultTableModel model = new DefaultTableModel(rows, columns);
                tableKlienci.setModel(model);
                tableKlienci.getColumnModel().getColumn(2).setPreferredWidth(140);
                tableKlienci.getColumnModel().getColumn(5).setPreferredWidth(20);
                textFieldImie.setText("");
                textFieldNazwisko.setText("");
                textFieldEmail.setText("");
                textFieldNrTel.setText("");
                textFieldAdres.setText("");
            }
        });
    }

    public DefaultTableModel createTable() {
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
}
