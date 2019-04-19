import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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
        jTable = RestaurantSerializator.createTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(jTable.getRowCount() > 0)
            jTable.setRowSelectionInterval(0, 0);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(5, 125, 376, 484);
        add(jScrollPane);

        //Action Listeners
        addBaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAddBaseFrame adminAddBaseFrame = new AdminAddBaseFrame();
                adminAddBaseFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        new FileWriter();
                        new AdministratorGraphicalUserInterface();
                        dispose();
                    }
                });
            }
        });

        addCompositeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileWriter();
                AdminAddCompositeFrame adminAddCompositeFrame = new AdminAddCompositeFrame();
                adminAddCompositeFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        new FileWriter();
                        new AdministratorGraphicalUserInterface();
                        dispose();
                    }
                });
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileWriter();
                RestaurantSerializator.deleteItem(new CompositeProduct(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
                new AdministratorGraphicalUserInterface();
                dispose();
            }
        });

        setVisible(true);
    }
}
