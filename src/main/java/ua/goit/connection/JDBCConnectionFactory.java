package ua.goit.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory implements ConnectionFactory {
    private String dbUrl;
    public JDBCConnectionFactory(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl);
    }
}
