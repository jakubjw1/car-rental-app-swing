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
        Database stuffDatabase = new Database("jdbc:mysql://localhost/car_rental", "root", "");
        ArrayList<Client> stuffList = stuffDatabase.getClients();

        String[] columns = {"Producer", "Name", "Volume",  "Number", "Price", "Remaining"};

        String[][] rows = new String[stuffList.size()][6];

        int i=0;
        for (Client e : stuffList) {
            if (e instanceof Food) {
                rows[i][0] = e.getProducer();
                rows[i][1] = e.getName();
                rows[i][2] = "---";
                rows[i][3] = e.getNumber();
                rows[i][4] = String.valueOf(e.getPrice());
                rows[i][5] = String.valueOf(e.getRemaining());

            } else {
                rows[i][0] = e.getProducer();
                rows[i][1] = e.getName();
                rows[i][2] = String.valueOf(((Drink) e).getVolume());
                rows[i][3] = e.getNumber();
                rows[i][4] = String.valueOf(e.getPrice());
                rows[i][5] = String.valueOf(e.getRemaining());
            }
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(rows, columns);
        stuffTable.setModel(model);
        stuffTable.getColumnModel().getColumn(0).setPreferredWidth(180);
        stuffTable.getColumnModel().getColumn(1).setPreferredWidth(220);
        return model;
    }
}
