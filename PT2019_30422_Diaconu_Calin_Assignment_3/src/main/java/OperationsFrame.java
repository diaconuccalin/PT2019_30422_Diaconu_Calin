import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OperationsFrame extends JFrame {
    public OperationsFrame(final String title) {
        int w = 800;
        int h = 600;

        setLayout(null);
        setSize(w, h);
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Content table
        JTable jTable = Main.createTable(title);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10, 10, 600, 545);

        //Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(615, 5, 165, 552);
        controlPanel.setBorder(UIElements.etchedTitleBorder("Operations"));
        controlPanel.setLayout(null);

        //Control panel buttons
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 515, 143, 25);
        controlPanel.add(backButton);

        JButton addButton = new JButton("Add");
        addButton.setBounds(10, 15, 143, 25);
        controlPanel.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(10, 45, 143, 25);
        controlPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(10, 75, 143, 25);
        controlPanel.add(deleteButton);

        //Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClientFrame addClientFrame;
                AddProductFrame addProductFrame;

                if(title.compareTo("Clients") == 0)
                    addClientFrame = new AddClientFrame();
                else if(title.compareTo("Products") == 0)
                    addProductFrame = new AddProductFrame();

                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                dispose();
            }
        });

        add(controlPanel);
        add(jScrollPane);
        setVisible(true);
    }
}
