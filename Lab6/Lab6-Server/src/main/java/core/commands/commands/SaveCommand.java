package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.CollectionManipulationReceiver;
import shared.serializables.ServerRequest;


public class SaveCommand implements Command {

    private String name = "save";
    private String desc = "save : сохранить коллекцию в файл";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
    private CollectionManipulationReceiver receiver;

    public SaveCommand(CollectionManipulationReceiver receiver) {
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
     * Saves Collection to file
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.save();
    }

}
