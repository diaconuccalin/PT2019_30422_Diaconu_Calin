package presentation;

import business.*;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class CreateOrderFrame extends JFrame {

    public CreateOrderFrame(final BufferedWriter bufferedWriter) {
        int w = 400;
        int h = 175;

        setLayout(null);
        setSize(w, h);
        setTitle("Create Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //model.Client Selection
        JLabel clientLabel = new JLabel("Client:");
        clientLabel.setBounds(10, 10, 50, 25);
        add(clientLabel);

        final JComboBox clientComboBox = new JComboBox();
        clientComboBox.setBounds(70, 10, 305, 25);
        add(clientComboBox);

        List<String> clientList = HelpingMethodsBLL.whatToList("client");
        for(String string : clientList) {
            clientComboBox.addItem(string);
        }

        //model.Product Selection
        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(10, 40, 50, 25);
        add(productLabel);

        final JComboBox productComboBox = new JComboBox();
        productComboBox.setBounds(70, 40, 305, 25);
        add(productComboBox);

        List<String> productList = HelpingMethodsBLL.whatToList("product");
        for(String string : productList) {
            productComboBox.addItem(string);
        }

        //Amount Selection
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 70, 50, 25);
        add(amountLabel);

        final JTextField amountField = new JTextField();
        amountField.setBounds(70, 70, 305, 25);
        add(amountField);

        //Buttons
        JButton createButton = new JButton("Create");
        createButton.setBounds(70, 105, 120, 25);
        add(createButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 105, 120, 25);
        add(backButton);

        //Action listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int clientId = HelpingMethodsBLL.extractId(clientComboBox.getItemAt(clientComboBox.getSelectedIndex()).toString());
                    int productId = HelpingMethodsBLL.extractId(productComboBox.getItemAt(productComboBox.getSelectedIndex()).toString());

                    int amount;
                    amount = Integer.parseInt(amountField.getText());

                    Client client = (Client) ReflectionBLL.findElement(new Client(clientId));
                    Product product = (Product) ReflectionBLL.findElement(new Product(productId));
                    int stock = product.getStock();

                    if(stock < amount) {
                        JOptionPane.showMessageDialog(null, "Not enough products in stock");
                    } else {
                        product.setStock(stock - amount);
                        ReflectionBLL.editElement(product);
                        Order order = new Order(clientId, productId, amount, product.getPrice() * amount);
                        ReflectionBLL.addElement(order);
                        printBill(client, product, order, bufferedWriter);

                        MainFrame mainFrame = new MainFrame(bufferedWriter);
                        dispose();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Incorrect input");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame(bufferedWriter);
                dispose();
            }
        });

        setVisible(true);
    }

    private void printBill(Client client, Product product, Order order, BufferedWriter bufferedWriter) {
        try {
            String toPrint = "CLIENT: " +
                    client.getName() +
                    " - " +
                    client.getAddress() +
                    " - " +
                    client.getEmail() +
                    "\nPRODUCT: " +
                    product.getName() +
                    " - " +
                    product.getDistributorid() +
                    " - " +
                    product.getPrice() +
                    "\nAmount: " +
                    order.getProductamount() +
                    "    Total price: " +
                    order.getTotalprice() +
                    " RON\n-------------------------------------------------------------\n";

            bufferedWriter.append(toPrint);
        } catch (IOException e) {
            System.out.println();
        }
    }
}
