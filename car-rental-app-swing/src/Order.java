import javax.swing.*;
import java.sql.SQLException;

public class Order extends JFrame{
    private JButton OKButton;
    private JTextField textFieldIDKlienta;
    private JPanel mainPanel;
    //Configuration config = new Configuration("jdbc:mysql://localhost/car_rental","root", "");

    public Order(){
        super("Zamawianie samochodu");
        this.setContentPane(mainPanel);
        this.setSize(400,300);

        OKButton.addActionListener(e -> {
            try {
                DatabaseQuery db = new DatabaseQuery(Configuration.host, Configuration.username, Configuration.password);
                try {
                    int ID_klienta = Integer.parseInt(textFieldIDKlienta.getText());
                    db.orderCar(ID_klienta, CarApp.ID_samochodu);
                    dispose();
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Podany numer ID musi być liczbą.");
                }
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
            }
        });
    }
}
