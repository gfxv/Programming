package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import shared.serializables.ServerRequest;


public class RemoveByIdCommand implements Command {

    private String name = "remove_by_id";
    private String desc = "remove_by_id id : удалить элемент из коллекции по его id";
    private boolean primitiveArg = true;
    private boolean complexArg = false;
    private ElementManipulationReceiver receiver;

    public RemoveByIdCommand(ElementManipulationReceiver receiver) {
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
     * Removes element from Storage with given ID
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.removeById(args);
    }
}
