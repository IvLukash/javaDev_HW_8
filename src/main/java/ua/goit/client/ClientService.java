package ua.goit.client;

import ua.goit.connection.ConnectionFactory;
import ua.goit.exception.InvalidIdException;
import ua.goit.exception.NameLengthException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public static final String INSERT_SQL = "INSERT INTO client (name) VALUES (?)";
    public static final String SELECT_BY_ID_SQL = "SELECT name FROM client WHERE id = ?";
    public static final String UPDATE_BY_ID_SQL = "UPDATE client SET name = ? WHERE id = ?";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM client WHERE id = ?";
    public static final String SELECT_ALL_SQL = "SELECT id, name FROM client";
    public static final String NO_SUCH_ELEMENT = "Record not found";
    private ConnectionFactory factory;

    public ClientService(ConnectionFactory factory) {
        this.factory = factory;
    }

    public long create(String name) throws NameLengthException {
        checkName(name);

        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(
                     INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
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

    public String getById(long id) throws InvalidIdException {
        checkId(id);

        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing get for id: " + id, e);
        }
        return NO_SUCH_ELEMENT;
    }

    public void setName(long id, String name) throws InvalidIdException, NameLengthException {
        checkId(id);
        checkName(name);

        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
            st.setString(1, name);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing update for id: " + id, e);
        }
    }

    public void deleteById(long id) throws InvalidIdException {
        checkId(id);

        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing delete for id: " + id, e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = factory.createConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_ALL_SQL);
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

    private void checkName(String name) throws NameLengthException {
        if (name.length() < 2 || name.length() > 1000) {
            throw new NameLengthException("Name must be between 2 and 1000 characters");
        }
    }

    private void checkId(long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException("ID must be greater than 0");
        }
    }
}
