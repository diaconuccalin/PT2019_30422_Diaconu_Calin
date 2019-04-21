package PresentationLayer;

import BusinessLayer.Observable;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WaiterGraphicalUserInterface extends Observable {
    private JTable jTable;
    private JFrame jFrame = new JFrame();

    public WaiterGraphicalUserInterface() {
        int w = 400;
        int h = 650;

        jFrame.setLayout(null);
        jFrame.setSize(w, h);
        jFrame.setTitle("Waiter");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add button
        JButton addButton = new JButton("New Order");
        addButton.setBounds(5, 5, 376, 25);
        jFrame.add(addButton);

        //Bill button
        JButton billButton = new JButton("Bill Order");
        billButton.setBounds(5, 35, 376, 25);
        jFrame.add(billButton);

        //Content table
        jFrame.add(createJScrollPane());

        //Action listeners
        addButtonActionListener(addButton, jFrame);
        billButtonActionListener(billButton, jFrame);

        jFrame.setVisible(true);
    }

    private JScrollPane createJScrollPane() {
        jTable = Restaurant.createOrdersTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (jTable.getRowCount() > 0)
            jTable.setRowSelectionInterval(0, 0);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(5, 65, 376, 544);

        return jScrollPane;
    }

    private void addButtonActionListener(JButton addButton, JFrame jFrame) {
        addButton.addActionListener(e -> {
            AddOrderFrame addOrderFrame = new AddOrderFrame();
            addOrderFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    Main.getChefGraphicalUserInterface().dispose();

                    ChefGraphicalUserInterface chefGraphicalUserInterface = new ChefGraphicalUserInterface();
                    Main.setChefGraphicalUserInterface(chefGraphicalUserInterface);
                    Main.getWaiterGraphicalUserInterface().addObserver(chefGraphicalUserInterface);

                    notifyAllObservers();

                    Main.setWaiterGraphicalUserInterface(new WaiterGraphicalUserInterface());
                    jFrame.dispose();
                }
            });
        });
    }

    private void billButtonActionListener(JButton billButton, JFrame jFrame) {
        billButton.addActionListener(e -> {
            Order order = Restaurant.getOrder(Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(null, Restaurant.generateBill(order));
            Restaurant.deleteOrder(order);

            Main.getChefGraphicalUserInterface().dispose();

            ChefGraphicalUserInterface chefGraphicalUserInterface = new ChefGraphicalUserInterface();
            Main.setChefGraphicalUserInterface(chefGraphicalUserInterface);
            Main.getWaiterGraphicalUserInterface().addObserver(chefGraphicalUserInterface);

            Main.setWaiterGraphicalUserInterface(new WaiterGraphicalUserInterface());
            jFrame.dispose();
        });
    }

    JFrame getjFrame() {
        return jFrame;
    }
}
