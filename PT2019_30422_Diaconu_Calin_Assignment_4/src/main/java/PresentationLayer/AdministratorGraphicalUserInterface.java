package PresentationLayer;

import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdministratorGraphicalUserInterface extends JFrame {
    private JTable jTable;

    public AdministratorGraphicalUserInterface() {
        int w = 400;
        int h = 650;

        setLayout(null);
        setSize(w, h);
        setTitle("Administrator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(this.getX() - 420, this.getY());

        //Add Base Product button
        JButton addBaseButton = new JButton("Add Base Menu Item");
        addBaseButton.setBounds(5, 5, 376, 25);
        add(addBaseButton);

        //Add Composite Product button
        JButton addCompositeButton = new JButton("Add Composite Menu Item");
        addCompositeButton.setBounds(5, 35, 376, 25);
        add(addCompositeButton);

        //Edit
        JButton editButton = new JButton("Edit Menu Item");
        editButton.setBounds(5, 65, 376, 25);
        add(editButton);

        //Delete
        JButton deleteButton = new JButton("Delete Menu Item");
        deleteButton.setBounds(5, 95, 376, 25);
        add(deleteButton);

        //Content table
        add(createJScrollPane());

        //Action Listeners
        addBaseButtonActionListener(addBaseButton);
        addCompositeButtonActionListener(addCompositeButton);
        editButtonActionListener(editButton);
        deleteButtonActionListener(deleteButton);

        setVisible(true);
    }

    private JScrollPane createJScrollPane() {
        jTable = Restaurant.createTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (jTable.getRowCount() > 0)
            jTable.setRowSelectionInterval(0, 0);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(5, 125, 376, 484);

        return jScrollPane;
    }

    private void addBaseButtonActionListener(JButton addBaseButton) {
        addBaseButton.addActionListener(e -> {
            AdminAddEditBaseFrame adminAddEditBaseFrame = new AdminAddEditBaseFrame(false, null);
            adminAddEditBaseFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    new AdministratorGraphicalUserInterface();
                    dispose();
                }
            });
        });
    }

    private void addCompositeButtonActionListener(JButton addCompositeButton) {
        addCompositeButton.addActionListener(e -> {
            AdminAddEditCompositeFrame adminAddEditCompositeFrame = new AdminAddEditCompositeFrame(false, null);
            adminAddEditCompositeFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    new AdministratorGraphicalUserInterface();
                    dispose();
                }
            });
        });
    }

    private void editButtonActionListener(JButton editButton) {
        editButton.addActionListener(e -> {
            MenuItem menuItem = Restaurant.getItem(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());

            if (menuItem.getClass().getSimpleName().compareTo("BaseProduct") == 0) {
                AdminAddEditBaseFrame adminAddEditBaseFrame = new AdminAddEditBaseFrame(true, menuItem);
                adminAddEditBaseFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        new AdministratorGraphicalUserInterface();
                        dispose();
                    }
                });
            } else {
                AdminAddEditCompositeFrame adminAddEditCompositeFrame = new AdminAddEditCompositeFrame(true, menuItem);
                adminAddEditCompositeFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        new AdministratorGraphicalUserInterface();
                        dispose();
                    }
                });
            }
        });
    }

    private void deleteButtonActionListener(JButton deleteButton) {
        deleteButton.addActionListener(e -> {
            if (Restaurant.canBeDeleted(jTable.getValueAt(jTable.getSelectedRow(), 0).toString())) {
                Restaurant.deleteItem(new CompositeProduct(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
                new AdministratorGraphicalUserInterface();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Can not delete (part of a composite product)");
            }
        });
    }
}
