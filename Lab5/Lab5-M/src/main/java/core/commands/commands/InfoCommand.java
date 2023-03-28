package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;
import core.system.Storage;


public class InfoCommand implements Command {
    /**
     * Command name
     */
    private String name = "info";
    /**
     * Command description
     */
    private String desc = "info : вывести в стандартный поток вывода информацию о коллекции";

    /**
     * Getter for name field
     * @return command name
     */

    private SystemCommandReceiver receiver;

    public InfoCommand(SystemCommandReceiver receiver) {
        this.receiver = receiver;
    }
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
     * Prints info about Collection
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.info();
    }
}
