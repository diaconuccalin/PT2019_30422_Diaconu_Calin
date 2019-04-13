package dataAccess;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public static void editProduct(Product product) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "UPDATE `ordermanagement`.`product` SET `name`='" +
                product.getName() +
                "', `stock`='" +
                product.getStock() +
                "', `distributor`='" +
                product.getDistributor() +
                "', `price`='" +
                product.getPrice() +
                "' WHERE `idproduct`='" +
                product.getIdProduct() +
                "';";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

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
