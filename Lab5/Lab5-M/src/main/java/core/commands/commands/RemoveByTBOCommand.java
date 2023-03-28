package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class RemoveByTBOCommand  implements Command {
    /**
     * Command name
     */
    private String name = "remove_any_by_total_box_office";
    /**
     * Command description
     */
    private String desc = "remove_any_by_total_box_office totalBoxOffice : удалить из коллекции один элемент, значение поля totalBoxOffice которого эквивалентно заданному";

    private ElementManipulationReceiver receiver;

    public RemoveByTBOCommand(ElementManipulationReceiver receiver) {
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
     * Removes any element with given Total Box Office value
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.removeByTBO(args);
    }

}
