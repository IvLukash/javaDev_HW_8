package ua.goit.test_settings;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestProps {
    private static final String TEST_CONFIG_FILE_PATH = "src/test/resources/test_config.properties";
    public static final String TEST_DB_INIT_FILE_PATH;
    public static final String TEST_DB_CLEAN_FILE_PATH;
    public static final String TEST_DB_URL_CONNECTION;

    static {
        try (FileReader reader = new FileReader(TEST_CONFIG_FILE_PATH)) {
            Properties testProps = new Properties();
            testProps.load(reader);

            TEST_DB_INIT_FILE_PATH = testProps.getProperty("db.init.sql");
            TEST_DB_CLEAN_FILE_PATH = testProps.getProperty("db.clean.sql");
            TEST_DB_URL_CONNECTION = testProps.getProperty("test.db.URL");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load project config", ex);
        }
    }
    private TestProps() {
    }
}
