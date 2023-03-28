package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.FileManager;
import core.receivers.CollectionManipulationReceiver;
import core.system.Config;
import core.system.Storage;

public class SaveCommand implements Command {
    /**
     * Command name
     */
    private String name = "save";
    /**
     * Command description
     */
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
