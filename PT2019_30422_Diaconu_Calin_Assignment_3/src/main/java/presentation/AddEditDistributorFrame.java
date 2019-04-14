package presentation;

import business.ReflectionBLL;
import model.Distributor;

import javax.swing.*;
import java.io.BufferedWriter;

class AddEditDistributorFrame extends JFrame {
    AddEditDistributorFrame(final boolean add, final Distributor distributor, final BufferedWriter bufferedWriter) {
        int w = 400;
        int h = 150;

        setLayout(null);
        if (add)
            setTitle("Add New Distributor");
        else
            setTitle("Edit Distributor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(w, h);
        setLocationRelativeTo(null);

        //Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 10, 60, 25);
        add(nameLabel);

        final JTextField nameField = new JTextField();
        nameField.setBounds(80, 10, 295, 25);
        add(nameField);

        //Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 45, 60, 25);
        add(emailLabel);

        final JTextField emailField = new JTextField();
        emailField.setBounds(80, 45, 295, 25);
        add(emailField);

        //Fill textFields
        if (!add) {
            nameField.setText(distributor.getName());
            emailField.setText(distributor.getEmail());
        }

        //Buttons
        JButton addButton;
        if (add)
            addButton = new JButton("Add");
        else
            addButton = new JButton("Submit");
        addButton.setBounds(70, 80, 120, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 80, 120, 25);
        add(backButton);

        //Action listeners
        addButton.addActionListener(e -> {
            if (add) {
                ReflectionBLL.addElement(new Distributor(nameField.getText(), emailField.getText()));
            } else {
                ReflectionBLL.editElement(new Distributor(distributor.getIddistributor(), nameField.getText(), emailField.getText()));
            }
            new OperationsFrame("Distributors", bufferedWriter);
            dispose();
        });

        backButton.addActionListener(e -> {
            new OperationsFrame("Distributors", bufferedWriter);
            dispose();
        });

        setVisible(true);
    }
}
