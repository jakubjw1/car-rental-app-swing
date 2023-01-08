import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarApp extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel klienciPanel;
    private JPanel samochodyPanel;
    private JTable tableKlienci;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldEmail;
    private JTextField textFieldNrTel;
    private JTextField textFieldAdres;
    private JTextField textFieldNrKarty;
    private JButton dodajButton;
    private JButton usuńButton;
    private JButton zapiszButton;
    private JTable tableSamochody;
    private JButton button1;

    public static void main(String[] args) {
        CarApp carApp1 = new CarApp();
        carApp1.setVisible(true);
    }
    String host = "jdbc:mysql://localhost/car_rental";
    String username = "root";
    String password = "";
    public CarApp(){
        super("CarApp");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,400);

        DefaultTableModel model = createTable();

        String imie = textFieldImie.getText();
        String nazwisko = textFieldNazwisko.getText();
        String email = textFieldEmail.getText();
        String nrTel = textFieldNrTel.getText();
        String adres = textFieldAdres.getText();
        String nrKarty = textFieldNrKarty.getText();

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                        textFieldNrKarty.setText("");
                    }
                }
        });

        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseQuery db = new DatabaseQuery(host,username,password);
                    try {
                        db.editRecord(imie,nazwisko,email,nrTel,adres,nrKarty);
                        DefaultTableModel model = (DefaultTableModel)tableKlienci.getModel();
                        model.setValueAt(imie, 5 ,0);
                        model.setValueAt(nazwisko, 5 ,1);
                        model.setValueAt(email, 5 ,2);
                        model.setValueAt(nrTel, 5 ,3);
                        model.setValueAt(adres, 5 ,4);
                        model.setValueAt(nrKarty, 5 ,5);
                        model.fireTableCellUpdated();
                    }
                    catch (SQLException ex) {
                        System.err.println(ex);
                        System.out.println("Nie udało się edytować danych klienta.");
                    }
                }
                catch (SQLException ex){
                    System.err.println(ex);
                    System.out.println("Nie udało się połączyć z bazą danych.");
                }
            }
        });

        usuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                String nrKarty = tableKlienci.getModel().getValueAt(row,5).toString();
                textFieldImie.setText(imie);
                textFieldNazwisko.setText(nazwisko);
                textFieldEmail.setText(email);
                textFieldNrTel.setText(nrTel);
                textFieldAdres.setText(adres);
                textFieldNrKarty.setText(nrKarty);
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
                rows[i][5] = "0";
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
