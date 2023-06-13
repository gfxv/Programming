package core.commands.commands;

import core.commands.base.Command;
import shared.exceptions.InvalidInputException;
import core.receivers.CollectionManipulationReceiver;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

public class ClearCommand implements Command {

    private String name = "clear";
    private String desc = "clear : очистить коллекцию";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
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

    public boolean isPrimitiveArg() {
        return primitiveArg;
    }
    public boolean isComplexArg() {
        return complexArg;
    }

    /**
     * Clears the Storage (Collection)
     * @throws InvalidInputException
     */
    @Override
    public ResponseBody execute(ServerRequest req) throws InvalidInputException {
        return this.receiver.clear(req);
    }
}
