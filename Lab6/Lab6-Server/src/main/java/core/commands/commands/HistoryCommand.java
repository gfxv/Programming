package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;
import shared.serializables.ServerRequest;


public class HistoryCommand implements Command {
    private String name = "history";
    private String desc = "history : вывести последние 5 команд (без их аргументов)";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
    private SystemCommandReceiver receiver;

    public HistoryCommand(SystemCommandReceiver receiver) {
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
     * Prints five last commands (without args)
     * @throws InvalidInputException
     */
    @Override
    public String[] execute(ServerRequest req) throws InvalidInputException {
        return new String[]{};
//        this.receiver.history();
    }
}
