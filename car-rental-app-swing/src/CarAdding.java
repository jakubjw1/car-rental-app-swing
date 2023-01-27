import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CarAdding extends JFrame {
    private JPanel mainPanel;
    private JButton OKButton;
    private JTextField textFieldModel;
    private JTextField textFieldMarka;
    Configuration config = new Configuration("jdbc:mysql://localhost/car_rental","root", "");
    public CarAdding(){
        super("Dodawanie Samochodu");
        this.setContentPane(mainPanel);
        this.setSize(400,300);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseQuery db = new DatabaseQuery(config.host,config.username,config.password);
                    try {
                        String marka = textFieldMarka.getText();
                        String model = textFieldModel.getText();
                        if (marka.equals("") || model.equals("")){
                            JOptionPane.showMessageDialog(null,"Podaj pełne dane samochodu.");
                        } else {
                            db.addCar(marka,model);
                            dispose();
                        }
                    }
                    catch (SQLException ex){
                        JOptionPane.showMessageDialog(null,"Nie udało się dodać samochodu.");
                    }
                } catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Nie udało się połączyć z bazą danych.");
                }
            }
        });
    }


}
