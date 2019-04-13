import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.EventListener;

public class Main {
    static BufferedWriter bufferedWriter;

    public static void main(String[] args) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("orders.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new BeforeShutdown(bufferedWriter));

        MainFrame mainFrame = new MainFrame(bufferedWriter);
    }

    public static JTable createTable(String table) {
        Connection connection = ConnectionFactory.getConnection();

        if(table.compareTo("Clients") == 0)
            table = "client";
        else if(table.compareTo("Products") == 0)
            table = "product";

        String findStatementString = "SELECT * FROM " + table;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        JTable jTable = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();
            rsmd = rs.getMetaData();

            String[] columnNames = new String[rsmd.getColumnCount()];

            for(int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }

            int row = 0;

            while(rs.next())
                row++;

            rs.first();
            Object[][] data = new Object[row][rsmd.getColumnCount()];
            row = 0;

            do {
                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
                    data[row][i - 1] = rs.getObject(i);
                }
                row++;
            } while(rs.next());

            jTable = new JTable(data, columnNames);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return jTable;
    }

    public static void deleteElement(String table, int id) {
        if(table.compareTo("Clients") == 0)
            table = "client";
        else if(table.compareTo("Products") == 0)
            table = "product";

        Connection connection = ConnectionFactory.getConnection();

        String statement = "DELETE FROM `ordermanagement`.`" +
                table +
                "` WHERE `id" +
                table +
                "`='" +
                id +
                "';";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
