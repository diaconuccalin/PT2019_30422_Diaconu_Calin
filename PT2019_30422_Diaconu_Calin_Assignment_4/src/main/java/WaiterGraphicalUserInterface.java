import javax.swing.*;
import java.util.List;

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

        setVisible(true);
    }

    public static void main(String[] args) {
        new WaiterGraphicalUserInterface();
    }
}
