package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class FilterLessThanGPCCommand implements Command {
    /**
     * Command name
     */
    private String name = "filter_less_than_golden_palm_count";
    /**
     * Command description
     */
    private String desc = "filter_less_than_golden_palm_count goldenPalmCount : вывести элементы, значение поля goldenPalmCount которых меньше заданного";

    private ElementManipulationReceiver receiver;

    public FilterLessThanGPCCommand(ElementManipulationReceiver receiver) {
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
     * Prints element if its goldenPalmCount value is less than given
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.filterLessThanGPCC(args);
    }
}