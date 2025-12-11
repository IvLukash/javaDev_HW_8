package ua.goit.settings;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProps {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    public static final String DB_URL_CONNECTION;

    static {
        try (FileReader reader = new FileReader(CONFIG_FILE_PATH)) {
            Properties props = new Properties();
            props.load(reader);

            DB_URL_CONNECTION = props.getProperty("db.URL");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load project config", ex);
        }
    }
    private ProjectProps() {
    }
}
