package dataAccess;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public static Product getProduct(int id) {
        Product product = null;

        Connection connection = ConnectionFactory.getConnection();

        String statement = "SELECT * FROM `ordermanagement`.`product` WHERE `idProduct`='" +
                id +
                "';";

        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(statement);
            rs = findStatement.executeQuery();

            rs.next();

            product = new Product(rs.getInt("idProduct"), rs.getString("name"), rs.getInt("stock"), rs.getString("distributor"), rs.getInt("price"));
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }
}
