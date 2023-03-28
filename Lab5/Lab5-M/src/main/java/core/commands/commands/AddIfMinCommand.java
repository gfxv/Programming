package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.receivers.ElementManipulationReceiver;
import core.system.Storage;

import java.util.HashSet;

public class AddIfMinCommand implements Command {

    /**
     * Command name
     */
    private String name = "add_if_min";
    /**
     * Command description
     */
    private String desc = "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";

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

    /**
     * Adds new element to the Storage, if its value is the lowest in the collection
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.addIfMin();
    }
}
