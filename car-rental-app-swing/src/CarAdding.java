import javax.swing.*;
import java.sql.SQLException;

public class CarAdding extends JFrame {
    private JPanel mainPanel;
    private JButton OKButton;
    private JTextField textFieldModel;
    private JTextField textFieldMarka;
    public CarAdding(){
        super("Dodawanie Samochodu");
        this.setContentPane(mainPanel);
        this.setSize(400,300);
        OKButton.addActionListener(e -> {
            try {
                DatabaseQuery db = new DatabaseQuery(Configuration.host, Configuration.username, Configuration.password);
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
        });
    }


}
