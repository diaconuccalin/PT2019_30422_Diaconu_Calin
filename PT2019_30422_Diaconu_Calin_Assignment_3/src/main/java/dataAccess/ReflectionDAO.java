package dataAccess;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            } catch (NoSuchMethodException e) {
                System.out.println(e);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            } catch (InvocationTargetException e) {
                System.out.println(e);
            }
        }
        statement = statement.concat("');");

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
