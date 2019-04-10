import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {
    private int idProduct;
    private String name;
    private int stock;
    private String distributor;
    private int price;

    public Product(String name, int stock, String distributor, int price) {
        this.name = name;
        this.stock = stock;
        this.distributor = distributor;
        this.price = price;
    }

    public static void addProduct(Product product) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "INSERT INTO `ordermanagement`.`product` (`name`, `stock`, `distributor`, `price`) VALUES ('" +
                product.name +
                "', '" +
                product.stock +
                "', '" +
                product.distributor +
                "', '" +
                product.price +
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
