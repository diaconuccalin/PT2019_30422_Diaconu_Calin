package presentation;

import business.ReflectionBLL;
import model.Product;

import javax.swing.*;
import java.io.BufferedWriter;

class AddEditProductFrame extends JFrame {
    AddEditProductFrame(final boolean add, final Product product, final BufferedWriter bufferedWriter) {
        int w = 400;
        int h = 220;

        setLayout(null);
        if (add)
            setTitle("Add New model.Product");
        else
            setTitle("Edit model.Product");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(w, h);
        setLocationRelativeTo(null);

        //Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 10, 65, 25);
        add(nameLabel);

        final JTextField nameField = new JTextField();
        nameField.setBounds(80, 10, 295, 25);
        add(nameField);

        //Stock
        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(10, 45, 65, 25);
        add(stockLabel);

        final JTextField stockField = new JTextField();
        stockField.setBounds(80, 45, 295, 25);
        add(stockField);

        //Distributor
        JLabel distributorLabel = new JLabel("Distributor:");
        distributorLabel.setBounds(10, 80, 65, 25);
        add(distributorLabel);

        final JTextField distributorField = new JTextField();
        distributorField.setBounds(80, 80, 295, 25);
        add(distributorField);

        //Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 115, 65, 25);
        add(priceLabel);

        final JTextField priceField = new JTextField();
        priceField.setBounds(80, 115, 295, 25);
        add(priceField);

        //Fill textFields
        if (!add) {
            nameField.setText(product.getName());
            stockField.setText(product.getStock() + "");
            distributorField.setText(product.getDistributorid() + "");
            priceField.setText(product.getPrice() + "");
        }

        //Buttons
        JButton addButton;
        if (add)
            addButton = new JButton("Add");
        else
            addButton = new JButton("Submit");
        addButton.setBounds(70, 150, 120, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 150, 120, 25);
        add(backButton);

        //Action Listeners
        addButton.addActionListener(e -> {
            try {
                if (add) {
                    ReflectionBLL.addElement(new Product(nameField.getText(), Integer.parseInt(stockField.getText()), Integer.parseInt(distributorField.getText()), Integer.parseInt(priceField.getText())));
                } else {
                    ReflectionBLL.editElement(new Product(product.getIdproduct(), nameField.getText(), Integer.parseInt(stockField.getText()), Integer.parseInt(distributorField.getText()), Integer.parseInt(priceField.getText())));
                }
                new OperationsFrame("Products", bufferedWriter);
                dispose();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Incorrect input");
            }
        });

        backButton.addActionListener(e -> {
            new OperationsFrame("Products", bufferedWriter);
            dispose();
        });

        setVisible(true);
    }
}
