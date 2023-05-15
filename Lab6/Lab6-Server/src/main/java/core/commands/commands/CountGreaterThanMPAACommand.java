package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import shared.serializables.ServerRequest;


public class CountGreaterThanMPAACommand implements Command {

    private String name = "count_greater_than_mpaa_rating";
    private String desc = "count_greater_than_mpaa_rating mpaaRating : вывести количество элементов, значение поля mpaaRating которых больше заданного";
    private boolean primitiveArg = true;
    private boolean complexArg = false;
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

    public boolean isPrimitiveArg() {
        return primitiveArg;
    }
    public boolean isComplexArg() {
        return complexArg;
    }

    /**
     * Prints the number of elements with given MPAA Rating
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.countGreaterThanMPAA(args);
    }

}
