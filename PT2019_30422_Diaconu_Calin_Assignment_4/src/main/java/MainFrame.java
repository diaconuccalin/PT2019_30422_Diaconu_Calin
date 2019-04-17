import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        int w = 260;
        int h = 150;

        setLayout(null);
        setSize(w, h);
        setTitle("Who are you?");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Administrator
        JButton administratorButton = new JButton("I am the administrator");
        administratorButton.setBounds(10, 10, 225, 40);
        add(administratorButton);

        //Waiter
        JButton waiterButton = new JButton("I am the waiter");
        waiterButton.setBounds(10, 60, 225, 40);
        add(waiterButton);

        //Action listeners
        administratorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdministratorGraphicalUserInterface();
                dispose();
            }
        });

        waiterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WaiterGraphicalUserInterface();
                dispose();
            }
        });

        setVisible(true);
    }
}
