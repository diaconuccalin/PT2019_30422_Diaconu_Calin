package dataAccess;

import model.Product;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class ReflectionDAO {
    public static void addElement(Object object) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "INSERT INTO `ordermanagement`.`" +
                object.getClass().getSimpleName().toLowerCase() +
                "` (";

        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            statement = statement.concat("`" + fields[i].getName() + "`");

            if (i != fields.length - 1)
                statement = statement.concat(", ");
        }

        statement = statement.concat(") VALUES ('");

        for (int i = 0; i < fields.length; i++) {
            try {
                String methodName = fields[i].getName();
                methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

                statement = statement.concat(object.getClass().getDeclaredMethod("get" + methodName).invoke(object).toString());

                if (i != fields.length - 1)
                    statement = statement.concat("', '");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.toString());
            }
        }
        statement = statement.concat("');");

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Incorrect input");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void editElement(Object object) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `ordermanagement`.`" +
                object.getClass().getSimpleName().toLowerCase() +
                "` SET ";

        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {

                String methodName = fields[i].getName();
                methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

                statement = statement.concat("`" + fields[i].getName() + "`='" + object.getClass().getDeclaredMethod("get" + methodName).invoke(object).toString());

                if (i != fields.length - 1) {
                    statement = statement.concat("', ");
                }

            }


            statement = statement.concat("' WHERE `id" + object.getClass().getSimpleName().toLowerCase() + "`='");
            statement = statement.concat(object.getClass().getDeclaredMethod("getId" + object.getClass().getSimpleName().toLowerCase()).invoke(object) + "';");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.toString());
        }

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(statement);
            JOptionPane.showMessageDialog(null, "Incorrect input");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void deleteElement(Object object) {
        Connection connection = ConnectionFactory.getConnection();
        if (object.getClass().getSimpleName().toLowerCase().compareTo("order") == 0) {
            object = findElement(object);
            int productid;
            int productamount;

            try {
                productid = Integer.parseInt(object.getClass().getMethod("getProductid").invoke(object).toString());
                productamount = Integer.parseInt(object.getClass().getMethod("getProductamount").invoke(object).toString());
                Product product = (Product) findElement(new Product(productid));
                product.setStock(product.getStock() + productamount);
                editElement(product);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(e.toString());
            }
        }
        String statement = "DELETE FROM `ordermanagement`.`" +
                object.getClass().getSimpleName().toLowerCase() +
                "` WHERE `id" +
                object.getClass().getSimpleName().toLowerCase() +
                "`='";
        String methodName = "getId" + object.getClass().getSimpleName().toLowerCase();

        try {
            statement = statement.concat(object.getClass().getDeclaredMethod(methodName).invoke(object).toString());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
        statement = statement.concat("';");
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            if (object.getClass().getSimpleName().toLowerCase().compareTo("client") == 0) {
                JOptionPane.showMessageDialog(null, "Can not delete. Client has active order");
            } else if (object.getClass().getSimpleName().toLowerCase().compareTo("product") == 0){
                JOptionPane.showMessageDialog(null, "Can not delete. Product part of an order");
            } else {
                JOptionPane.showMessageDialog(null, "Can not delete. Distributor of a product that is part of an order");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static Object findElement(Object object) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "SELECT * FROM `ordermanagement`.`" +
                object.getClass().getSimpleName().toLowerCase() +
                "` WHERE `id" +
                object.getClass().getSimpleName().toLowerCase() +
                "`='";
        String methodName = "getId" + object.getClass().getSimpleName().toLowerCase();
        try {
            statement = statement.concat(object.getClass().getDeclaredMethod(methodName).invoke(object).toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.toString());
        }
        statement = statement.concat("';");
        PreparedStatement findStatement;
        ResultSet rs;
        try {
            findStatement = connection.prepareStatement(statement);
            rs = findStatement.executeQuery();
            rs.next();
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 1; i < fields.length; i++) {
                String field = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
                object.getClass().getDeclaredMethod("set" + field, rs.getObject(i + 1).getClass()).invoke(object, rs.getObject(i + 1));
            }
        } catch (SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println(e.toString());
        }
        return object;
    }
}
