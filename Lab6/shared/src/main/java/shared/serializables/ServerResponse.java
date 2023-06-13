package shared.serializables;

import shared.enteties.User;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerResponse implements Serializable {
    private static final long serialVersionUID = 2;

    private boolean errors = false;
    private String errorMessage;
    private ResponseBody response;

    private User user;

    private ArrayList<CommandInfoObject> commands = null;

    public ServerResponse(ResponseBody response) {
        this.response = response;
    }

    public ServerResponse(User user, String errorMessage) {
        this.user = user;
        this.errorMessage = errorMessage;
    }

    public ServerResponse(String errorMessage) {
        this.errors = true;
        this.errorMessage = errorMessage;
    }

    public ServerResponse(ArrayList<CommandInfoObject> cmds) {
        commands = cmds;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public ResponseBody getResponse() {
        return this.response;
    }

    public boolean hasErrors() {
        return this.errors;
    }

    public ArrayList<CommandInfoObject> getCommands() {
        return commands;
    }

    public User getUser() {
        return this.user;
    }

}
