import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Order extends JFrame{
    private JButton OKButton;
    private JTextField textFieldIDKlienta;
    private JPanel mainPanel;
    Configuration config = new Configuration("jdbc:mysql://localhost/car_rental","root", "");

    public Order(){
        super("Zamawianie samochodu");
        this.setContentPane(mainPanel);
        this.setSize(400,300);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseQuery db = new DatabaseQuery(config.host,config.username,config.password);
                    try {
                        int ID_klienta = Integer.parseInt(textFieldIDKlienta.getText());
                        if (ID_klienta == 0){
                            JOptionPane.showMessageDialog(null,"Podaj numer ID klienta!");
                        } else {
                            db.orderCar(ID_klienta, CarApp.ID_samochodu);
                            dispose();
                        }
                    }
                    catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Podany numer ID musi być liczbą.");
                    }
                } catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
                }
            }
        });
    }
}
