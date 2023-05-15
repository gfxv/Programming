package shared.serializables;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandsInfoResponse implements Serializable {

    private ArrayList<CommandInfoObject> commands = new ArrayList<>();

    public void setInfo(ArrayList<CommandInfoObject> commands) {
        this.commands = commands;
    }

    public ArrayList<CommandInfoObject> getCommands() {
        return commands;
    }

}


