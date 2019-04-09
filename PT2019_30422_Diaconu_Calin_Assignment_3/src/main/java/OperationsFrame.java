import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsFrame extends JFrame {
    public OperationsFrame(String title) {
        int w = 800;
        int h = 600;

        setLayout(null);
        setSize(w, h);
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }
}
