package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.enteties.MpaaRating;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class CountGreaterThanMPAACommand implements Command {
    /**
     * Command name
     */
    private String name = "count_greater_than_mpaa_rating";
    /**
     * Command description
     */
    private String desc = "count_greater_than_mpaa_rating mpaaRating : вывести количество элементов, значение поля mpaaRating которых больше заданного";

    private ElementManipulationReceiver receiver;

    public CountGreaterThanMPAACommand(ElementManipulationReceiver receiver) {
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
     * Prints the number of elements with given MPAA Rating
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.countGreaterThanMPAA(args);
    }

}
