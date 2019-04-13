import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;

public class AddEditClientFrame extends JFrame {
    public AddEditClientFrame(final boolean add, final Client client, final BufferedWriter bufferedWriter) {
        int w = 400;
        int h = 185;

        setLayout(null);
        if(add)
            setTitle("Add New Client");
        else
            setTitle("Edit Client");
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

        //Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 45, 60, 25);
        add(addressLabel);

        final JTextField addressField = new JTextField();
        addressField.setBounds(80, 45, 295, 25);
        add(addressField);

        //Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 80, 60, 25);
        add(emailLabel);

        final JTextField emailField = new JTextField();
        emailField.setBounds(80, 80, 295, 25);
        add(emailField);

        //Fill textFields
        if(!add) {
            nameField.setText(client.getName());
            addressField.setText(client.getAddress());
            emailField.setText(client.getEmail());
        }

        //Buttons
        JButton addButton = null;
        if(add)
            addButton = new JButton("Add");
        else
            addButton = new JButton("Submit");
        addButton.setBounds(70, 115, 120, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 115, 120, 25);
        add(backButton);

        //Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(add) {
                    Client.addClient(new Client(nameField.getText(), addressField.getText(), emailField.getText()));
                } else {
                    Client.editClient(new Client(client.getIdclient(), nameField.getText(), addressField.getText(), emailField.getText()));
                }
                OperationsFrame operationsFrame = new OperationsFrame("Clients", bufferedWriter);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsFrame operationsFrame = new OperationsFrame("Clients", bufferedWriter);
                dispose();
            }
        });

        setVisible(true);
    }
}
