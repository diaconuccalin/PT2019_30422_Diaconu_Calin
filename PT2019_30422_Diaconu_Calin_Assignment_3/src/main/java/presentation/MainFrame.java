package presentation;

import javax.swing.*;
import java.io.BufferedWriter;

public class MainFrame extends JFrame {
    public MainFrame(final BufferedWriter bufferedWriter) {
        int w = 250;
        int h = 250;

        setLayout(null);
        setSize(w, h);
        setTitle("DB Management");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //model.Client operations button
        JButton clientButton = new JButton("Client Operations");
        clientButton.setBounds(10, 10, 215, 40);
        add(clientButton);

        //model.Product operations button
        JButton productButton = new JButton("Product Operations");
        productButton.setBounds(10, 60, 215, 40);
        add(productButton);

        //Order operations button
        JButton createButton = new JButton("Order Operations");
        createButton.setBounds(10, 110, 215, 40);
        add(createButton);

        //Distributor operations button
        JButton distributorButton = new JButton("Distributor Operations");
        distributorButton.setBounds(10, 160, 215, 40);
        add(distributorButton);

        //Action listeners
        clientButton.addActionListener(e -> {
            new OperationsFrame("Clients", bufferedWriter);
            dispose();
        });

        productButton.addActionListener(e -> {
            new OperationsFrame("Products", bufferedWriter);
            dispose();
        });

        createButton.addActionListener(e -> {
            new OperationsFrame("Orders", bufferedWriter);
            dispose();
        });

        distributorButton.addActionListener(e -> {
            new OperationsFrame("Distributors", bufferedWriter);
            dispose();
        });

        setVisible(true);
    }
}
