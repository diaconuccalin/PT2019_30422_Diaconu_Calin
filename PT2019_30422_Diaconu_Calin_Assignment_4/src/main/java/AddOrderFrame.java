import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class AddOrderFrame extends JFrame {
    public AddOrderFrame() {
        int w = 350;
        int h = 450;

        setLayout(null);
        setSize(w, h);
        setTitle("New Order");
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
        new FileWriter();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        List<MenuItem> menuItems = new ArrayList<>();
        while(true) {
            try {
                menuItems.add((MenuItem) FileWriter.getObjectInputStream().readObject());
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        List<JTextField> jTextFields = new ArrayList<>();
        List<JLabel> jLabels = new ArrayList<>();

        for(MenuItem menuItem : menuItems) {
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
        add(jScrollPane);

        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(80, 380, 80, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 380, 80, 25);
        add(backButton);

        //Action listeners

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddOrderFrame();
    }
}
