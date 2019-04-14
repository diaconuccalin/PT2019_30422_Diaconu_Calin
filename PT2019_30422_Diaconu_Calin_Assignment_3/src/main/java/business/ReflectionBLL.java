package business;

import dataAccess.ReflectionDAO;
import model.Client;

public class ReflectionBLL {
    public static void addElement(Object object) {
        ReflectionDAO.addElement(object);
    }

    public static void editElement(Object object) {
        ReflectionDAO.editElement(object);
    }

    public static Object findElement(Object object) {
        return ReflectionDAO.findElement(object);
    }
}
