package dataAccess;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
    public static void addOrder(Order order) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "INSERT INTO `ordermanagement`.`order` (`clientid`, `productid`, `productamount`, `totalprice`) VALUES ('" +
                order.getClientid() +
                "', '" +
                order.getProductid() +
                "', '" +
                order.getProductamount() +
                "', '" +
                order.getTotalprice() +
                "');";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
