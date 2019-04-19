import javax.swing.*;

public class WaiterGraphicalUserInterface extends JFrame {
    public WaiterGraphicalUserInterface() {
        int w = 400;
        int h = 650;

        setLayout(null);
        setSize(w, h);
        setTitle("Waiter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(this.getX(), this.getY());

        setVisible(true);
    }
}
