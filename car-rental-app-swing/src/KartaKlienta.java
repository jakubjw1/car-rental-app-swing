import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class KartaKlienta extends JFrame {

    static int numer;
    private JPanel mainPanel;
    private JTextField textFieldNrKarty;
    private JButton zatwierdźButton;

    Configuration config = new Configuration("jdbc:mysql://localhost/car_rental","root", "");

    public KartaKlienta(String imie, String nazwisko){
        super("Karta Klienta");
        this.setContentPane(mainPanel);
        this.setSize(400,300);

        zatwierdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseQuery db = new DatabaseQuery(config.host,config.username,config.password);
                    try {
                        String NrKarty = textFieldNrKarty.getText();
                        if (NrKarty.length() != 4){
                            JOptionPane.showMessageDialog(null,"Numer powinien zawierać 4 cyfry.");
                            textFieldNrKarty.setText("");
                        } else {
                            try {
                                numer = Integer.parseInt(NrKarty);
                                db.addNrKarty(imie, nazwisko, Integer.parseInt(NrKarty));
                                dispose();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Numer musi być liczbą czterocyfrową");
                            }
                        }
                    }
                    catch (SQLException ex){
                        JOptionPane.showMessageDialog(null,"Nie udało się wyrobić karty.");
                    }
                } catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
                }

            }
        });
    }
}
