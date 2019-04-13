package dataAccess;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
    public static void addClient(Client client) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "INSERT INTO `ordermanagement`.`client` (`name`, `address`, `email`) VALUES ('" +
                client.getName() +
                "', '" +
                client.getAddress() +
                "', '" +
                client.getEmail() +
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
                client.getName() +
                "', `address`='" +
                client.getAddress() +
                "', `email`='" +
                client.getEmail() +
                "' WHERE `idclient`='" +
                client.getIdclient() +
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
}
