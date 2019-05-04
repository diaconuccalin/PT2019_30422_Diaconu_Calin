package PresentationLayer;

import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChefGraphicalUserInterface extends JFrame implements Observer {
    public ChefGraphicalUserInterface() {
        int w = 400;
        int h = 650;

        setLayout(null);
        setSize(w, h);
        setTitle("Chef");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(this.getX() + 420, this.getY());

        //Content
        List<Order> orders = Restaurant.createOrderList();
        add(createJScrollPane(orders));

        setVisible(true);
    }

    private JScrollPane createJScrollPane(List<Order> orders) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        for (Order order : orders) {
            if (!order.isDone()) {
                int i = orders.indexOf(order);

                JLabel jLabel = new JLabel("Order: " + order.getOrderID());
                jLabel.setBounds(15, 10 + i * 40, 200, 25);
                jPanel.add(jLabel);

                JButton jButton = new JButton("Done");
                jButton.setBounds(255, 10 + i * 40, 80, 25);
                buttonActionListener(jButton, order);
                jPanel.add(jButton);
            }
        }

        jPanel.setPreferredSize(new Dimension(280, 15 + 40 * orders.size()));
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setBounds(10, 10, 367, 595);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return jScrollPane;
    }

    private void buttonActionListener(JButton jButton, Order order) {
        jButton.addActionListener(e -> {
            Restaurant.getOrder(order.getOrderID()).setDone(true);

            Main.getWaiterGraphicalUserInterface().getJFrame().dispose();
            Main.setWaiterGraphicalUserInterface(new WaiterGraphicalUserInterface());

            ChefGraphicalUserInterface chefGraphicalUserInterface = new ChefGraphicalUserInterface();
            Main.setChefGraphicalUserInterface(chefGraphicalUserInterface);
            Main.getWaiterGraphicalUserInterface().addObserver(chefGraphicalUserInterface);

            dispose();
        });
    }

    public void update() {
        NotificationFrame.newOrderNotify();
    }
}
