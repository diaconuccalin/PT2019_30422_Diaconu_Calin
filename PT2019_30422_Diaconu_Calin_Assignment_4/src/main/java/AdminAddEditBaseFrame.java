import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAddEditBaseFrame extends JFrame {
    public AdminAddEditBaseFrame(boolean edit, MenuItem menuItem) {
        int w = 350;
        int h = 150;

        setLayout(null);
        setSize(w, h);
        setTitle("Add Base Menu Item");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(15, 10, 50, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(70, 10, 250, 25);
        add(nameField);

        //Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(15, 40, 50, 25);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(70, 40, 250, 25);
        add(priceField);

        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(80, 75, 80, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 75, 80, 25);
        add(backButton);

        //ActionListeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int price = Integer.parseInt(priceField.getText());
                    if(edit)
                        RestaurantSerializator.deleteItem(menuItem);
                    RestaurantSerializator.addBaseItem(new BaseProduct(nameField.getText(), price));
                    dispose();
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Incorrect input");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //set fields
        if(edit) {
            nameField.setText(menuItem.getName());
            priceField.setText(menuItem.computePrice() + "");
            addButton.setText("OK");
            setTitle("Edit Base Menu Item");
        }

        setVisible(true);
    }
}
