package dataAccess;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelpingMethodsDAO {
    public static List<String> whatToList(String table) {
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
        if (table.compareTo("Clients") == 0)
            table = "client";
        else if (table.compareTo("Products") == 0)
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
        } catch (SQLIntegrityConstraintViolationException e) {
            if (table.compareTo("client") == 0) {
                JOptionPane.showMessageDialog(null, "Can not delete. Client has active order");
            } else {
                JOptionPane.showMessageDialog(null, "Can not delete. Product part of an order");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
