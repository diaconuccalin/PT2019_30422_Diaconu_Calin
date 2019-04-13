package business;

import dataAccess.HelpingMethodsDAO;
import dataAccess.ReflectionDAO;

import javax.swing.*;
import java.util.List;

public class HelpingMethodsBLL {
    public static int extractId(String string) {
        int i = 0;
        int id = 0;

        while(string.charAt(i) != '.') {
            id = id * 10 + string.charAt(i) - '0';
            i++;
        }

        return id;
    }

    public static List<String> whatToList(String title) {
        return HelpingMethodsDAO.whatToList(title);
    }

    public static JTable createTable(String table) {
        return HelpingMethodsDAO.createTable(table);
    }

    public static void deleteElement(Object object) {
        ReflectionDAO.deleteElement(object);
    }
}
