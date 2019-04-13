import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderFrame extends JFrame {

    public CreateOrderFrame(final BufferedWriter bufferedWriter) {
        int w = 400;
        int h = 175;

        setLayout(null);
        setSize(w, h);
        setTitle("Create Order");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Client Selection
        JLabel clientLabel = new JLabel("Client:");
        clientLabel.setBounds(10, 10, 50, 25);
        add(clientLabel);

        final JComboBox clientComboBox = new JComboBox();
        clientComboBox.setBounds(70, 10, 305, 25);
        add(clientComboBox);

        List<String> clientList = whatToList("client");
        for(String string : clientList) {
            clientComboBox.addItem(string);
        }

        //Product Selection
        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(10, 40, 50, 25);
        add(productLabel);

        final JComboBox productComboBox = new JComboBox();
        productComboBox.setBounds(70, 40, 305, 25);
        add(productComboBox);

        List<String> productList = whatToList("product");
        for(String string : productList) {
            productComboBox.addItem(string);
        }

        //Amount Selection
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 70, 50, 25);
        add(amountLabel);

        final JTextField amountField = new JTextField();
        amountField.setBounds(70, 70, 305, 25);
        add(amountField);

        //Buttons
        JButton createButton = new JButton("Create");
        createButton.setBounds(70, 105, 120, 25);
        add(createButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 105, 120, 25);
        add(backButton);

        //Action listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = -1;
                try {
                    String selectedClient = clientComboBox.getItemAt(clientComboBox.getSelectedIndex()).toString();
                    int i = 0;
                    int clientId = 0;
                    while(selectedClient.charAt(i) != '.') {
                        clientId = clientId * 10 + selectedClient.charAt(i) - '0';
                        i++;
                    }

                    String selectedProduct = productComboBox.getItemAt(productComboBox.getSelectedIndex()).toString();
                    i = 0;
                    int productId = 0;
                    while(selectedProduct.charAt(i) != '.') {
                        productId = productId * 10 + selectedProduct.charAt(i) - '0';
                        i++;
                    }

                    amount = Integer.parseInt(amountField.getText());

                    Connection connection = ConnectionFactory.getConnection();

                    String statement = "SELECT * FROM ordermanagement.product where idProduct = " +
                            productId +
                            ";";

                    PreparedStatement findStatement = null;
                    ResultSet rs = null;

                    findStatement = connection.prepareStatement(statement);
                    rs = findStatement.executeQuery();

                    rs.next();
                    int stock = rs.getInt("stock");

                    if(stock < amount) {
                        JOptionPane.showMessageDialog(null, "Not enough products in stock");
                    } else {
                        statement = "UPDATE `ordermanagement`.`product` SET `name`='" +
                                rs.getString("name") +
                                "', `stock`='" +
                                        (rs.getInt("stock") - amount) +
                                "', `distributor`='" +
                                rs.getString("distributor") +
                                "', `price`='" +
                                rs.getInt("price") +
                                "' WHERE `idproduct`='" +
                                rs.getInt("idproduct") +
                                "';";
                        findStatement = connection.prepareStatement(statement);
                        findStatement.executeUpdate();

                        statement = "INSERT INTO `ordermanagement`.`order` (`clientid`, `productid`, `productamount`, `totalprice`) VALUES ('" +
                                clientId +
                                "', '" +
                                productId +
                                "', '" +
                                amount +
                                "', '" +
                                (amount * rs.getInt("price")) +
                                "');";
                        findStatement = connection.prepareStatement(statement);
                        findStatement.executeUpdate();

                        Client client = Client.getClient(clientId);
                        Product product = Product.getProduct(productId);

                        String toPrint = "";
                        toPrint = toPrint.concat("CLIENT: " +
                                client.getName() +
                                " - " +
                                client.getAddress() +
                                " - " +
                                client.getEmail() +
                                "\nPRODUCT: " +
                                product.getName() +
                                " - " +
                                product.getDistributor() +
                                " - " +
                                product.getPrice() +
                                "\nAmount: " +
                                amount +
                                "    Total price: " +
                                (amount * rs.getInt("price")) +
                                "\n-------------------------------------------------------------\n");

                        bufferedWriter.append(toPrint);

                        MainFrame mainFrame = new MainFrame(bufferedWriter);
                        dispose();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Incorrect input");
                } catch (SQLException e1) {
                    System.out.println(e1);
                } catch (IOException e1) {
                    System.out.println(e1);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame(bufferedWriter);
                dispose();
            }
        });

        setVisible(true);
    }

    List<String> whatToList(String table) {
        List<String> toReturn = new ArrayList<String>();

        Connection connection = ConnectionFactory.getConnection();

        String findStatementString = "SELECT * FROM " + table;
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                String aux = null;
                if(table.compareTo("client") == 0)
                    aux = rs.getObject(1).toString() + ". " + rs.getObject(2).toString() + " - " + rs.getObject(4).toString();
                else if(table.compareTo("product") == 0)
                    aux = rs.getObject(1).toString() + ". " + rs.getObject(2).toString() + " - " + rs.getObject(5).toString() + " RON";
                toReturn.add(aux);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return toReturn;
    }
}
