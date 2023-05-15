package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import shared.serializables.ServerRequest;


public class AddIfMinCommand implements Command {

    private String name = "add_if_min";
    private String desc = "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    private boolean primitiveArg = false;
    private boolean complexArg = true;
    private ElementManipulationReceiver receiver;

    public AddIfMinCommand(ElementManipulationReceiver receiver) {
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
     * Adds new element to the Storage, if its value is the lowest in the collection
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.addIfMin();
    }
}
