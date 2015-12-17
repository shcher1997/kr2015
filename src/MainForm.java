import javax.swing.*;

class MainForm extends JFrame {

    private JPanel panel1;

    public MainForm(){
        setContentPane(panel1);
        setTitle("Media");
        setSize(700,300);
        setVisible(true);
    }

    public static void main (String[] args) {
        new MainForm();
    }
}
