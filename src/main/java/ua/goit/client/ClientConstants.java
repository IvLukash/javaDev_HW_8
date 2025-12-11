package ua.goit.client;

public final class ClientConstants {
    public static final String INSERT_SQL = "INSERT INTO client (name) VALUES (?)";
    public static final String SELECT_BY_ID_SQL = "SELECT name FROM client WHERE id = ?";
    public static final String UPDATE_BY_ID_SQL = "UPDATE client SET name = ? WHERE id = ?";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM client WHERE id = ?";
    public static final String SELECT_ALL_SQL = "SELECT id, name FROM client";
    public static final String NO_SUCH_ELEMENT = "Record not found";

    private ClientConstants() {
    }
}
