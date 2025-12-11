package ua.goit.test_database;

import ua.goit.connection.ConnectionFactory;
import ua.goit.database.DBInitializer;
import ua.goit.test_settings.TestProps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBInitializer implements DBInitializer {
    ConnectionFactory factory;

    public TestDBInitializer(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void initializeDB() {
        String sql;
        try {
            sql = Files.readString(Path.of(TestProps.TEST_DB_INIT_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        try (Connection connection = factory.createConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query", e);
        }
    }
}
