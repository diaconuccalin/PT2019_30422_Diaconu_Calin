import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        int w = 250;
        int h = 150;

        setLayout(null);
        setSize(w, h);
        setTitle("Order Management");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Client operations button
        JButton clientButton = new JButton("Client Operations");
        clientButton.setBounds(10, 10, 215, 40);
        add(clientButton);

        //Product operations button
        JButton productButton = new JButton("Product Operations");
        productButton.setBounds(10, 60, 215, 40);
        add(productButton);

        //Action listeners
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsFrame operationsFrame = new OperationsFrame("Clients");
                dispose();
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsFrame operationsFrame = new OperationsFrame("Products");
                dispose();
            }
        });

        setVisible(true);
    }
}
