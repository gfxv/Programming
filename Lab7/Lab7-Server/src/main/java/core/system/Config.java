package core.system;

import java.sql.Connection;

public class Config {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection conn) {
        connection = conn;
    }


}
