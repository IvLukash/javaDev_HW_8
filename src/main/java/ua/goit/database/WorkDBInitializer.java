package ua.goit.database;

import org.flywaydb.core.Flyway;
import ua.goit.settings.ProjectProps;

public class WorkDBInitializer implements DBInitializer {
    @Override
    public void initializeDB() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(ProjectProps.DB_URL_CONNECTION, null, null)
                .load();

        flyway.migrate();
    }
}
