package PresentationLayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NotificationFrame extends JFrame {
    private NotificationFrame() {
        int w = 160;
        int h = 120;

        setLayout(null);
        setSize(w, h);
        setTitle("New BusinessLayer.Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(this.getX() + 420, this.getY());

        //Message
        JLabel message = new JLabel("NEW ORDER!");
        message.setBounds(37, 10, 180, 30);
        add(message);

        //OK Button
        JButton okButton = new JButton("OK");
        okButton.setBounds(37, 45, 80, 25);
        add(okButton);

        //Action Listener
        okButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    static void newOrderNotify() {
        NotificationFrame notificationFrame = new NotificationFrame();
        Timer timer = new Timer(2000, e -> {
            notificationFrame.setVisible(false);
            notificationFrame.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }
}
