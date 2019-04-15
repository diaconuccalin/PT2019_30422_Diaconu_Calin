package presentation;

import business.HelpingMethodsBLL;
import business.ReflectionBLL;
import model.Client;
import model.Distributor;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.io.BufferedWriter;

class OperationsFrame extends JFrame {
    OperationsFrame(final String title, final BufferedWriter bufferedWriter) {
        int w = 800;
        int h = 600;

        setLayout(null);
        setSize(w, h);
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Content table
        final JTable jTable = HelpingMethodsBLL.createTable(title);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.setRowSelectionInterval(0, 0);
        jTable.setDefaultEditor(Object.class, null);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10, 10, 600, 545);

        //Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(615, 5, 165, 552);
        controlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Operations"));
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
        addButton.addActionListener(e -> {
            if (title.compareTo("Clients") == 0)
                new AddEditClientFrame(true, null, bufferedWriter);
            else if (title.compareTo("Products") == 0)
                new AddEditProductFrame(true, null, bufferedWriter);
            else if (title.compareTo("Orders") == 0)
                new AddEditOrderFrame(true, null, bufferedWriter);
            else if (title.compareTo("Distributors") == 0)
                new AddEditDistributorFrame(true, null, bufferedWriter);

            dispose();
        });

        editButton.addActionListener(e -> {
            if (title.compareTo("Clients") == 0) {
                int id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                String address = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
                String email = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();

                new AddEditClientFrame(false, new Client(id, name, address, email), bufferedWriter);
                dispose();
            } else if (title.compareTo("Products") == 0) {
                int id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                int stock = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
                int distributor = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());
                int price = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 4).toString());

                new AddEditProductFrame(false, new Product(id, name, stock, distributor, price), bufferedWriter);
                dispose();
            } else if (title.compareTo("Orders") == 0) {
                int idorder = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                int clientid = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
                int productid = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
                int productamount = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());
                int totalprice = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 4).toString());

                new AddEditOrderFrame(false, new Order(idorder, clientid, productid, productamount, totalprice), bufferedWriter);
                dispose();
            } else if (title.compareTo("Distributors") == 0) {
                int iddistributor = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                String email = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();

                new AddEditDistributorFrame(false, new Distributor(iddistributor, name, email), bufferedWriter);
                dispose();
            }
        });

        deleteButton.addActionListener(e -> {
            Object object = null;
            if (title.compareTo("Clients") == 0)
                object = new Client(Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
            else if (title.compareTo("Products") == 0)
                object = new Product(Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
            else if (title.compareTo("Orders") == 0)
                object = new Order(Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));
            else if (title.compareTo("Distributors") == 0)
                object = new Distributor(Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString()));

            ReflectionBLL.deleteElement(object);
            new OperationsFrame(title, bufferedWriter);
            dispose();
        });

        backButton.addActionListener(e -> {
            new MainFrame(bufferedWriter);
            dispose();
        });

        add(controlPanel);
        add(jScrollPane);
        setVisible(true);
    }
}
