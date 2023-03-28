package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import core.system.Config;
import core.system.Storage;


public class RemoveByIdCommand implements Command {
    /**
     * Command name
     */
    private String name = "remove_by_id";
    /**
     * Command description
     */
    private String desc = "remove_by_id id : удалить элемент из коллекции по его id";

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

    /**
     * Removes element from Storage with given ID
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.removeById(args);
    }
}
