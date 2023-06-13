package core.managers;

import core.db.crud.UsersCRUD;
import shared.enteties.User;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthManager {

    private User user;
    private String type;
    private Connection  connection;

    public AuthManager(ServerRequest request, Connection connection) {
        user = request.getUser();
        type = request.getType();
        this.connection = connection;
    }

    public ServerResponse auth() {
        if (type.equals("l")) {
            return loginUser();
        }

        if (type.equals("r")) {
            return registerUser();
        }

        return new ServerResponse(new ResponseBody("SOMETHING WENT HORRIBLY WRONG"));
    }

    public ServerResponse loginUser() {
        try {
            User user = UsersCRUD.getUserIdByName(connection, this.user);
            if (user == null) return new ServerResponse(new ResponseBody("Wrong login or password"));
            return new ServerResponse(user, "no errors");
        } catch (SQLException e) {
            return new ServerResponse(new ResponseBody("Wrong login or password"));
        }
    }

    public ServerResponse registerUser() {
        try {
            UsersCRUD.addUser(connection, this.user);
            User user = UsersCRUD.getUserIdByName(connection, this.user);
            return new ServerResponse(user, "no errors");
        } catch (SQLException e) {
            e.printStackTrace();
            return new ServerResponse(new ResponseBody("User already exists"));
        }
    }

}
