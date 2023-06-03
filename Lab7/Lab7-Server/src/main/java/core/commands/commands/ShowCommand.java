package core.commands.commands;

import core.commands.base.Command;
import core.receivers.CollectionManipulationReceiver;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;


public class ShowCommand implements Command {

    private String name = "show";
    private String desc = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
    private CollectionManipulationReceiver receiver;

    public ShowCommand(CollectionManipulationReceiver receiver) {
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
     * Prints all elements from the Collection
     */
    @Override
    public ResponseBody execute(ServerRequest req) {
        return this.receiver.show(req);
    }
}
