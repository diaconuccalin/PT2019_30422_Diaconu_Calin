import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        ArrayList<ArrayList<Object>> objects = new ArrayList<ArrayList<Object>>();

        ArrayList<Object> l1 = new ArrayList<Object>();
        String test1 = "test01";
        String test2 = "test02";
        l1.add(test1);
        l1.add(test2);

        ArrayList<Object> l2 = new ArrayList<Object>();
        String test3 = "test03";
        String test4 = "test04";
        l2.add(test3);
        l2.add(test4);

        objects.add(l1);
        objects.add(l2);
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
}
