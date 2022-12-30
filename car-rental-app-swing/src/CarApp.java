import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public static void main(String[] args) {
        CarApp carApp1 = new CarApp();
        carApp1.setVisible(true);
    }

    public CarApp(){
        super("CarApp");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,400);

        DefaultTableModel model = createTable();
    }

    public DefaultTableModel createTable() {
        Database clientsDatabase = new Database("jdbc:mysql://localhost/car_rental", "root", "");
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
                rows[i][5] = "-";
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
