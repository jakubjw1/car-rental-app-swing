import javax.swing.*;

public class CarAdding extends JFrame {
    private JPanel mainPanel;
    private JButton OKButton;
    private JTextField textFieldModel;
    private JTextField textFieldMarka;

    public CarAdding(){
        super("Dodawanie Samochodu");
        this.setContentPane(mainPanel);
        this.setSize(400,300);
    }
}
