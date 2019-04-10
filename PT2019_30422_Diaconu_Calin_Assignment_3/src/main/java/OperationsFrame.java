import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        final JTable jTable = Main.createTable(title);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.setRowSelectionInterval(0, 0);
        jTable.setDefaultEditor(Object.class, null);
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
                AddEditClientFrame addEditClientFrame;
                AddEditProductFrame addEditProductFrame;

                if(title.compareTo("Clients") == 0)
                    addEditClientFrame = new AddEditClientFrame(true, null);
                else if(title.compareTo("Products") == 0)
                    addEditProductFrame = new AddEditProductFrame(true, null);

                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(title.compareTo("Clients") == 0) {
                    int id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                    String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                    String address = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
                    String email = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();

                    AddEditClientFrame addEditClientFrame = new AddEditClientFrame(false, new Client(id, name, address, email));
                    dispose();
                } else if(title.compareTo("Products") == 0) {
                    int id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                    String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                    int stock = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
                    String distributor = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
                    int price = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 4).toString());

                    AddEditProductFrame addEditProductFrame = new AddEditProductFrame(false, new Product(id, name, stock, distributor, price));
                    dispose();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.deleteElement(title, Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
                OperationsFrame operationsFrame = new OperationsFrame(title);
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
