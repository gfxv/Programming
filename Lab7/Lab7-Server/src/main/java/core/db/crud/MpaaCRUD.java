package core.db.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaaCRUD {

    public static int getMpaaIdByName(Connection connection, String mpaaName) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("""
            select id from mpaa where name = ?
""");

        statement.setString(1, mpaaName);
        ResultSet rs = statement.executeQuery();

        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        statement.close();
        if (id == -1) throw new SQLException("Something went wrong while selecting mpaa");
        return id;
    }

}
