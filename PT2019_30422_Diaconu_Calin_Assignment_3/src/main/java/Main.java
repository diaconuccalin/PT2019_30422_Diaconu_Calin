import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    public static JTable createTable() {
        String[] columnNames = {"ID", "Name", "Address", "Email"};
        Object[][] data = {
                {1, "George Georgescu", "Str. Java, nr. 13", "george@email.com"},
                {2, "Ioana Ionescu", "Str. Algoritmilor, nr. 4", "ioana@email.com"}
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setRowSelectionInterval(0, 0);

        return jTable;
    }
}
