package ua.goit.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.goit.connection.ConnectionFactory;
import ua.goit.connection.JDBCConnectionFactory;
import ua.goit.exception.NameLengthException;
import ua.goit.test_database.TestDBInitializer;
import ua.goit.test_database.TestDbCleaner;
import ua.goit.test_settings.TestProps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    ConnectionFactory factory;
    ClientService service;

    @BeforeEach
    void before() {
        factory = new JDBCConnectionFactory(TestProps.TEST_DB_URL_CONNECTION);
        new TestDBInitializer(factory).initializeDB();
        new TestDbCleaner(factory).cleanDB();
        service = new ClientService(factory);
    }

    @Test
    void createTest() {
        String name = "Ivan Lukash";
        long id = service.create(name);

        assertTrue(id > 0);

        try (Connection con = factory.createConnection();
             PreparedStatement st = con.prepareStatement("SELECT name FROM client WHERE id = ?")) {

            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                assertTrue(rs.next());
                assertEquals(name, rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testThatNameLengthInCreateHandledCorrectly() {
        List<String> names = List.of("", "G", "George".repeat(300));
        for (String name : names) {
            assertThrows(NameLengthException.class, () -> service.create(name));
        }
    }

    @Test
    void getByIdTest() {
        String expectedName = "Robert Malkolm";
        long id = service.create(expectedName);
        String actualName = service.getById(id);

        assertEquals(expectedName, actualName);
    }

    @Test
    void setNameTest() {
        String name = "Ivan Lukash";
        long id = service.create(name);
        String newName = "Petro Savchenko";
        service.setName(id, newName);

        assertEquals(newName, service.getById(id));
    }

    @Test
    void testThatNameLengthInSetNameHandledCorrectly() {
        List<String> names = List.of("", "G", "George".repeat(300));
        String testName = "Ivan Lukash";
        long id = service.create(testName);
        for (String name : names) {
            assertThrows(NameLengthException.class, () -> service.setName(id, name));
        }
    }

    @Test
    void deleteByIdTest() {
        String name = "Ivan Lukash";
        long id = service.create(name);
        service.deleteById(id);

        assertEquals("Record not found", service.getById(id));
    }

    @Test
    void listAllTest() {
        List<Client> clients = new ArrayList<>();
        List<String> names = List.of("Ivan Lukash", "Petro Savchenko", "Roman Orest");
        for (String name : names) {
            Client client = new Client();
            long id = service.create(name);
            client.setId(id);
            client.setName(name);
            clients.add(client);
        }

        assertEquals(clients, service.listAll());
    }
}