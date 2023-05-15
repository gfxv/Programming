package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;

public class RemoveLowerCommand implements Command {

    private String name = "remove_lower";
    private String desc = "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный;";
    private ElementManipulationReceiver receiver;

    public RemoveLowerCommand(ElementManipulationReceiver receiver) {
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
     * Removes all elements from the Storage that a lower than given
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.removeLower(args);
    }
}
