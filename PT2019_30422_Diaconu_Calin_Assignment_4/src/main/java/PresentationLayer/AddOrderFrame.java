package PresentationLayer;

import BusinessLayer.Order;
import BusinessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class AddOrderFrame extends JFrame {
    private List<JTextField> jTextFields;
    private List<JLabel> jLabels;

    AddOrderFrame() {
        int w = 350;
        int h = 450;

        setLayout(null);
        setSize(w, h);
        setTitle("New BusinessLayer.Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Table
        JLabel tableLabel = new JLabel("Table");
        tableLabel.setBounds(15, 10, 50, 25);
        add(tableLabel);

        JTextField tableField = new JTextField();
        tableField.setBounds(70, 10, 250, 25);
        add(tableField);

        //Menu items
        add(createJScrollPane());

        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(80, 380, 80, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 380, 80, 25);
        add(backButton);

        //Action listeners
        addButtonActionListener(addButton, tableField, jTextFields, jLabels);
        backButtonActionListener(backButton);

        setVisible(true);
    }

    private void addButtonActionListener(JButton addButton, JTextField tableField, List<JTextField> jTextFields, List<JLabel> jLabels) {
        addButton.addActionListener(e -> {
            try {
                int table = Integer.parseInt(tableField.getText());

                List<String> orderItems = new ArrayList<>();
                for (JTextField jTextField : jTextFields) {
                    int quantity = Integer.parseInt(jTextField.getText());
                    while (quantity != 0) {
                        orderItems.add(jLabels.get(jTextFields.indexOf(jTextField)).getText());
                        quantity--;
                    }
                }
                Restaurant.createOrder(new Order(table, orderItems));
                dispose();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Incorrect input");
            }
        });
    }

    private void backButtonActionListener(JButton backButton) {
        backButton.addActionListener(e -> dispose());
    }

    private JScrollPane createJScrollPane() {
        Restaurant.resetStreams();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        List<BusinessLayer.MenuItem> menuItems = Restaurant.getMenuItems();

        jTextFields = new ArrayList<>();
        jLabels = new ArrayList<>();

        for (BusinessLayer.MenuItem menuItem : menuItems) {
            int i = menuItems.indexOf(menuItem);

            JTextField jTextField = new JTextField();
            jTextField.setBounds(5, 5 + i * 30, 40, 25);
            jTextField.setText("0");
            jPanel.add(jTextField);

            JLabel jLabel = new JLabel(menuItem.getName());
            jLabel.setBounds(55, 5 + i * 30, 200, 25);
            jPanel.add(jLabel);

            jTextFields.add(jTextField);
            jLabels.add(jLabel);
        }

        jPanel.setPreferredSize(new Dimension(280, 5 + 30 * menuItems.size()));
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setBounds(15, 40, 305, 330);

        return jScrollPane;
    }
}
