package business;

import dataAccess.ReflectionDAO;

public class ReflectionBLL {
    public static void addElement(Object object) {
        ReflectionDAO.addElement(object);
    }

    public static void editElement(Object object) {
        ReflectionDAO.editElement(object);
    }

    public static void deleteElement(Object object) {
        ReflectionDAO.deleteElement(object);
    }

    public static Object findElement(Object object) {
        return ReflectionDAO.findElement(object);
    }
}
