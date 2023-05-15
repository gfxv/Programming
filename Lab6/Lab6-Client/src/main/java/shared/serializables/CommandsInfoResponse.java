package shared.serializables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandsInfoResponse implements Serializable {

    private List<CommandInfoObject> commands = new ArrayList<>();

    public void addCommand(CommandInfoObject command) {
        commands.add(command);
    }

    public List<CommandInfoObject> getCommands() {
        return commands;
    }

}


