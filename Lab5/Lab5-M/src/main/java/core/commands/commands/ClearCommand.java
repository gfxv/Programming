package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.CollectionManipulationReceiver;
import core.system.Storage;

public class ClearCommand implements Command {
    /**
     * Command name
     */
    private String name = "clear";
    /**
     * Command description
     */
    private String desc = "clear : очистить коллекцию";

    private CollectionManipulationReceiver receiver;

    public ClearCommand(CollectionManipulationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Getter for name field
     * @return command name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Getter for description field
     * @return command description
     */
    @Override
    public String getDesc() {
        return this.desc;
    }

    /**
     * Clears the Storage (Collection)
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.clear();
    }
}
