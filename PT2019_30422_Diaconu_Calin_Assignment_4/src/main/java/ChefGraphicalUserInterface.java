import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class ChefGraphicalUserInterface extends JFrame {
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
        FileWriters.resetStreams();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        List<Order> orders = new ArrayList<>();
        while(true) {
            try {
                Order order = (Order) FileWriters.getOrderObjectInputStream().readObject();
                if(!order.isDone())
                    orders.add(order);
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        List<JLabel> jLabels = new ArrayList<>();
        List<JButton> jButtons = new ArrayList<>();

        for(Order order : orders) {
            int i = orders.indexOf(order);

            JLabel jLabel = new JLabel("Order: " + order.getOrderID());
            jLabel.setBounds(15, 10 + i * 40, 200, 25);
            jPanel.add(jLabel);

            JButton jButton = new JButton("Done");
            jButton.setBounds(255, 10 + i * 40, 80, 25);

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Order order1 = RestaurantSerializator.getOrder(order.getOrderID());
                    RestaurantSerializator.deleteOrder(order1);
                    order1.setDone(true);
                    RestaurantSerializator.addOrder(order1);

                    Main.setChefGraphicalUserInterface(new ChefGraphicalUserInterface());
                    dispose();
                }
            });

            jPanel.add(jButton);

            jLabels.add(jLabel);
            jButtons.add(jButton);
        }

        jPanel.setPreferredSize(new Dimension(280, 15 + 40 * orders.size()));

        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setBounds(10, 10, 367, 595);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(jScrollPane);

        setVisible(true);
    }
}
