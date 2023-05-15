package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;


public class UpdateByIdCommand implements Command {

    private String name = "update_by_id";
    private String desc = "update_by_id {element} : обновить значение элемента коллекции, id которого равен заданному";
    private ElementManipulationReceiver receiver;

    public UpdateByIdCommand(ElementManipulationReceiver receiver) {
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
     * Updates the value of the collection element whose id is equal to the given one
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.updateById(args);
    }
}
