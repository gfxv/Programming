package core.system;

import shared.serializables.CommandInfoObject;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandInfoCollection implements Serializable {

    private ArrayList<CommandInfoObject> commandInfoObjects;

    public CommandInfoCollection() {
        commandInfoObjects = new ArrayList<>();
        init();
    }

    private void init() {
        for (var entry : Invoker.getCommands().entrySet()) {
            if (entry.getKey().equals("save")) continue;
            commandInfoObjects.add(new CommandInfoObject(entry.getKey(), entry.getValue().isPrimitiveArg(), entry.getValue().isComplexArg()));
        }
    }

    public ArrayList<CommandInfoObject> getCommandInfoObjects() {
        return commandInfoObjects;
    }

    public void print() {
        for (CommandInfoObject cio : commandInfoObjects) {
            System.out.println(cio.getCommand() + " " + cio.hasPrimitiveArg() + " " + cio.hasComplexArg());

        }
    }
}
