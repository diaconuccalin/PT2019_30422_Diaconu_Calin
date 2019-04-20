import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class AdminAddEditCompositeFrame extends JFrame {
    public AdminAddEditCompositeFrame(boolean edit, MenuItem menuItem) {
        int w = 350;
        int h = 450;

        setLayout(null);
        setSize(w, h);
        setTitle("Add Composite Menu Item");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(15, 10, 50, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(70, 10, 250, 25);
        add(nameField);

        //Ingredients
        FileWriters.resetStreams();
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(15, 45, 100, 25);
        add(ingredientsLabel);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        while (true) {
            try {
                MenuItem menuItem1 = (MenuItem) FileWriters.getObjectInputStream().readObject();
                if (menuItem1.getClass().getSimpleName().compareTo("BaseProduct") == 0)
                    menuItems.add(menuItem1);
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        List<JCheckBox> jCheckBoxes = new ArrayList<JCheckBox>();
        List<JLabel> jLabels = new ArrayList<JLabel>();

        for (MenuItem menuItem1 : menuItems) {
            int i = menuItems.indexOf(menuItem1);

            JCheckBox jCheckBox = new JCheckBox();
            jCheckBox.setBounds(5, 5 + i * 30, 25, 25);
            jPanel.add(jCheckBox);

            JLabel jLabel = new JLabel(menuItem1.getName() + " - " + menuItem1.computePrice() + " RON");
            jLabel.setBounds(40, 5 + i * 30, 200, 25);

            jPanel.add(jLabel);

            jCheckBoxes.add(jCheckBox);
            jLabels.add(jLabel);
        }

        jPanel.setPreferredSize(new Dimension(280, 5 + 30 * menuItems.size()));
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setBounds(15, 70, 305, 300);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(jScrollPane);

        setVisible(true);

        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(80, 380, 80, 25);
        add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 380, 80, 25);
        add(backButton);

        //Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompositeProduct compositeProduct = new CompositeProduct(nameField.getText());

                for (JCheckBox jCheckBox : jCheckBoxes) {
                    if (jCheckBox.isSelected()) {
                        String string = jLabels.get(jCheckBoxes.indexOf(jCheckBox)).getText();
                        String ingredientName = "";
                        int ingredientPrice = 0;
                        int p = 1;
                        int i = 0;

                        for (i = string.length() - 5; string.charAt(i) >= '0' && string.charAt(i) <= '9'; i--) {
                            ingredientPrice = ingredientPrice * p + string.charAt(i) - '0';
                            p = p * 10;
                        }

                        i = i - 3;
                        while (i >= 0) {
                            ingredientName = (string.charAt(i) + "").concat(ingredientName);
                            i--;
                        }

                        compositeProduct.addIngredient(new BaseProduct(ingredientName, ingredientPrice));
                    }
                }
                if(edit)
                    RestaurantSerializator.deleteItem(menuItem);
                RestaurantSerializator.addCompositeItem(compositeProduct);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        if(edit) {
            nameField.setText(menuItem.getName());
            addButton.setText("OK");
            setTitle("Edit Base Menu Item");

            CompositeProduct compositeProduct = (CompositeProduct) menuItem;
            List<MenuItem> menuItemList = compositeProduct.getIngredients();

            for(MenuItem menuItem1 : menuItemList) {
                for(JLabel jLabel : jLabels) {
                    if(jLabel.getText().compareTo(menuItem1.getName() + " - " + menuItem1.computePrice() + " RON") == 0) {
                        jCheckBoxes.get(jLabels.indexOf(jLabel)).setSelected(true);
                    }
                }
            }
        }
    }
}
