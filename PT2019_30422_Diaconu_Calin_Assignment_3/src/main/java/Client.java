import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
    private int idclient;
    private String name;
    private String address;
    private String email;

    public Client(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Client(int idclient, String name, String address, String email) {
        this.idclient = idclient;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public static void addClient(Client client) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "INSERT INTO `ordermanagement`.`client` (`name`, `address`, `email`) VALUES ('" +
                client.name +
                "', '" +
                client.address +
                "', '" +
                client.email +
                "');";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void editClient(Client client) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "UPDATE `ordermanagement`.`client` SET `name`='" +
                client.name +
                "', `address`='" +
                client.address +
                "', `email`='" +
                client.email +
                "' WHERE `idclient`='" +
                client.idclient +
                "';";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Client getClient(int id) {
        Client client = null;

        Connection connection = ConnectionFactory.getConnection();

        String statement = "SELECT * FROM `ordermanagement`.`client` WHERE `idclient`='" +
                id +
                "';";

        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(statement);
            rs = findStatement.executeQuery();

            rs.next();

            client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        } catch (SQLException e) {
            System.out.println(e);
        }

        return client;
    }

    public int getIdclient() {
        return idclient;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
