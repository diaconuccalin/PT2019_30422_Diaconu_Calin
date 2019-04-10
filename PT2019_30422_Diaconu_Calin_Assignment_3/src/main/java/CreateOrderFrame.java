import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateOrderFrame extends JFrame {
    public CreateOrderFrame() {
        int w = 400;
        int h = 175;

        setLayout(null);
        setSize(w, h);
        setTitle("Create Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Client Selection
        JLabel clientLabel = new JLabel("Client:");
        clientLabel.setBounds(10, 10, 50, 25);
        add(clientLabel);

        JComboBox clientComboBox = new JComboBox();
        clientComboBox.setBounds(70, 10, 305, 25);
        add(clientComboBox);

        //Product Selection
        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(10, 40, 50, 25);
        add(productLabel);

        JComboBox productComboBox = new JComboBox();
        productComboBox.setBounds(70, 40, 305, 25);
        add(productComboBox);

        //Amount Selection
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 70, 50, 25);
        add(amountLabel);

        JTextField amountField = new JTextField();
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
