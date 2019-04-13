import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Product(int idProduct, String name, int stock, String distributor, int price) {
        this.idProduct = idProduct;
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

    public static void editProduct(Product product) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "UPDATE `ordermanagement`.`product` SET `name`='" +
                product.name +
                "', `stock`='" +
                product.stock +
                "', `distributor`='" +
                product.distributor +
                "', `price`='" +
                product.price +
                "' WHERE `idproduct`='" +
                product.idProduct +
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

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public String getDistributor() {
        return distributor;
    }

    public int getPrice() {
        return price;
    }
}
