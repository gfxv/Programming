package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.CollectionManipulationReceiver;


public class SaveCommand implements Command {

    private String name = "save";
    private String desc = "save : сохранить коллекцию в файл";
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

    /**
     * Saves Collection to file
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.save();
    }

}
