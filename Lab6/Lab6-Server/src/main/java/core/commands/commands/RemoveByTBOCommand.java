package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;
import shared.serializables.ServerRequest;


public class RemoveByTBOCommand  implements Command {

    private String name = "remove_any_by_total_box_office";
    private String desc = "remove_any_by_total_box_office totalBoxOffice : удалить из коллекции один элемент, значение поля totalBoxOffice которого эквивалентно заданному";
    private boolean primitiveArg = true;
    private boolean complexArg = false;
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

    public boolean isPrimitiveArg() {
        return primitiveArg;
    }
    public boolean isComplexArg() {
        return complexArg;
    }

    /**
     * Removes any element with given Total Box Office value
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.removeByTBO(args);
    }

}
