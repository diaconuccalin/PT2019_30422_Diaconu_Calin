import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;

public class MainFrame extends JFrame {
    public MainFrame(final BufferedWriter bufferedWriter) {
        int w = 250;
        int h = 200;

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

        //Create order button
        JButton createButton = new JButton("Create Order");
        createButton.setBounds(10, 110, 215, 40);
        add(createButton);

        //Action listeners
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsFrame operationsFrame = new OperationsFrame("Clients", bufferedWriter);
                dispose();
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsFrame operationsFrame = new OperationsFrame("Products", bufferedWriter);
                dispose();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateOrderFrame createOrderFrame = new CreateOrderFrame(bufferedWriter);
                dispose();
            }
        });

        setVisible(true);
    }
}
