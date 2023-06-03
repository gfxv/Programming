package core.commands.commands;

import core.commands.base.Command;
import shared.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;


public class InfoCommand implements Command {

    private String name = "info";
    private String desc = "info : вывести в стандартный поток вывода информацию о коллекции";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
    private SystemCommandReceiver receiver;

    public InfoCommand(SystemCommandReceiver receiver) {
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
     * Prints info about Collection
     * @throws InvalidInputException
     */
    @Override
    public ResponseBody execute(ServerRequest req) throws InvalidInputException {
        return this.receiver.info();
    }
}
