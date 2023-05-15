package core.commands.commands;


import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import shared.serializables.ServerRequest;


public class AddCommand implements Command {

    private String name = "add";
    private String desc = "add {element} : добавить новый элемент в коллекцию";
    private final boolean primitiveArg = false;
    private final boolean complexArg = true;
    private ElementManipulationReceiver receiver;

    public AddCommand(ElementManipulationReceiver receiver) {
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
     * Adds new element to the Storage
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
//        return new String[]{};
        return this.receiver.add(req);
    }
}
