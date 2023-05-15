package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import shared.serializables.ServerRequest;

public class RemoveLowerCommand implements Command {

    private String name = "remove_lower";
    private String desc = "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный;";
    private boolean primitiveArg = false;
    private boolean complexArg = true;
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

    public boolean isPrimitiveArg() {
        return primitiveArg;
    }
    public boolean isComplexArg() {
        return complexArg;
    }

    /**
     * Removes all elements from the Storage that a lower than given
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.removeLower(args);
    }
}
