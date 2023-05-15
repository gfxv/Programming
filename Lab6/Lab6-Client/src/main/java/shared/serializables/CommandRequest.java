package shared.serializables;

import java.io.Serializable;

public class CommandRequest implements Serializable {

    private String command;

    public CommandRequest(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

}
