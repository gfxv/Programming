package shared.serializables;

import shared.enteties.Movie;
import shared.enteties.User;

import java.io.Serializable;

public class ServerRequest implements Serializable {
    private static final long serialVersionUID = 1;

    private User user;
    private String type;
    private String command;
    private String primitiveArg;
    private Movie complexArg;

    public ServerRequest(String command) {
        this.command = command;
    }

    public ServerRequest(String command, User user, String type) {
        this.command = command;
        this.user = user;
        this.type = type;
    }

    public ServerRequest(String command, String arg) {
        this.command = command;
        this.primitiveArg = arg;
    }

    public ServerRequest(String command, Movie arg) {
        this.command = command;
        this.complexArg = arg;
    }

    public ServerRequest(String command, String arg, Movie mArg) {
        this.command = command;
        this.primitiveArg = arg;
        this.complexArg = mArg;
    }

    public String getCommand() {
        return this.command;
    }

    public String getPrimitiveArg() {
        return this.primitiveArg;
    }

    public Movie getComplexArg() {
        return this.complexArg;
    }

    public User getUser() {
        return this.user;
    }

    public String getType() {
        return this.type;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
