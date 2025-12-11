package ua.goit.connection;

import ua.goit.settings.ProjectProps;

public class ConnectionFactoryProvider {
    private static ConnectionFactory factory;

    private ConnectionFactoryProvider() {}

    public static ConnectionFactory getFactory() {
        if (factory == null) {
            factory = new JDBCConnectionFactory(ProjectProps.DB_URL_CONNECTION);
        }
        return factory;
    }
}
