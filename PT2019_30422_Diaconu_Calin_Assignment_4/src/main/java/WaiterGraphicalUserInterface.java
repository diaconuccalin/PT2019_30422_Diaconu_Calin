import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WaiterGraphicalUserInterface extends JFrame {
    private JTable jTable;

    public WaiterGraphicalUserInterface() {
        int w = 400;
        int h = 650;

        setLayout(null);
        setSize(w, h);
        setTitle("Waiter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Add button
        JButton addButton = new JButton("New Order");
        addButton.setBounds(5, 5, 376, 25);
        add(addButton);

        //Bill button
        JButton billButton = new JButton("Bill Order");
        billButton.setBounds(5, 35, 376, 25);
        add(billButton);

        //Content table
        jTable = RestaurantSerializator.createOrdersTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(jTable.getRowCount() > 0)
            jTable.setRowSelectionInterval(0, 0);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(5, 65, 376, 544);
        add(jScrollPane);

        //Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrderFrame addOrderFrame = new AddOrderFrame();
                addOrderFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        FileWriters.resetStreams();
                        new WaiterGraphicalUserInterface();
                        dispose();
                    }
                });
            }
        });

        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = RestaurantSerializator.getOrder(Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
                JOptionPane.showMessageDialog(null, FileWriters.printBill(order));
                RestaurantSerializator.deleteOrder(order);

                new WaiterGraphicalUserInterface();
                dispose();
            }
        });

        setVisible(true);
    }
}
