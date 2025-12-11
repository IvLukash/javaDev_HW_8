package ua.goit.client;

import ua.goit.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private ConnectionFactory factory;

    public ClientService(ConnectionFactory factory) {
        this.factory = factory;
    }

    public long create(String name) {
        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(
                     ClientConstants.INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, name);
            st.executeUpdate();
            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
            throw new RuntimeException("No generated key returned");
        } catch (SQLException e) {
            throw new RuntimeException("Error executing insert for client: " + name, e);
        }
    }

    public String getById(long id) {
        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(ClientConstants.SELECT_BY_ID_SQL)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing get for id: " + id, e);
        }
        return ClientConstants.NO_SUCH_ELEMENT;
    }

    public void setName(long id, String name) {
        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(ClientConstants.UPDATE_BY_ID_SQL)) {
            st.setString(1, name);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing update for id: " + id, e);
        }
    }

    public void deleteById(long id) {
        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(ClientConstants.DELETE_BY_ID_SQL)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing delete for id: " + id, e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(ClientConstants.SELECT_ALL_SQL);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing get all clients", e);
        }
        return clients;
    }
}
