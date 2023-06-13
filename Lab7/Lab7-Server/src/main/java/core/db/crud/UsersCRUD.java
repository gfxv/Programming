package core.db.crud;

import core.security.HashPassword;
import core.security.Salt;
import shared.enteties.User;

import java.sql.*;

public class UsersCRUD {

    private static final Object lock = new Object();

    // Добавление нового пользователя в базу
    public static synchronized void addUser(Connection connection, User user) throws SQLException {
        long id = addPassword(connection, user.getPassword());
        PreparedStatement statement = connection.prepareStatement("""
                    insert into Users (id, name, password)
                    values (default, ?, ?)
                """);
        statement.setString(1, user.getName());
        statement.setInt(2, (int) id);

        synchronized (lock) {
            statement.execute();
            statement.close();
        }

    }

    // Сохранение пароля
    private static long addPassword(Connection connection, String password) throws SQLException {

        char[] char_salt = Salt.generateSalt();
        String salt = new String(char_salt);
        String hashed_password = HashPassword.hash(password, salt);

        PreparedStatement statement = connection.prepareStatement("""
                    insert into password (id, salt, hashed_password)
                    values (default, ?, ?)
                """);
        statement.setString(1, salt);
        statement.setString(2, hashed_password);

        synchronized (lock) {
            statement.execute();
        }

        ResultSet rs;
        synchronized (lock) {
             rs = connection.createStatement().executeQuery("select currval('password_id_seq');");
        }

        long id = -1;
        if (rs.next()) {
            id = rs.getLong(1);
        }
        rs.close();
        statement.close();

        if (id == -1) throw new SQLException("Something went wrong while adding new user");
        return id;
    }

    // Для аутентификации пользователя при входе
    public static User getUserIdByName(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("""
                        select id, password from Users where name = ?;
                """);
        statement.setString(1, user.getName());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int userId = rs.getInt(1);
            int passwordId = rs.getInt(2);
            if (!validatePassword(connection, passwordId, user.getPassword())) return null;
            User validatedUser = new User(user.getName(), user.getPassword());
            validatedUser.setId(userId);
            return validatedUser;
        }
        return null;
    }

    private static boolean validatePassword(Connection connection, int userPasswordId, String userInputId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("""
                        select salt, hashed_password from password where id = ?;
                """);
        statement.setInt(1, userPasswordId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            String salt = rs.getString(1);
            String hp = rs.getString(2);
            statement.close();
            rs.close();
            return HashPassword.hash(userInputId, salt).equals(hp);
        }
        return false;
    }

}
