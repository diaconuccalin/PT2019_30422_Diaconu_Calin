import javax.swing.*;

public class ChefGraphicalUserInterface extends JFrame {
    public ChefGraphicalUserInterface() {
        int w = 400;
        int h = 650;

        setLayout(null);
        setSize(w, h);
        setTitle("Chef");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(this.getX() + 420, this.getY());

        setVisible(true);
    }

    public void newOrderNotify() {

    }
}
