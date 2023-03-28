package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;
import core.system.History;

import java.util.List;

public class HistoryCommand implements Command {
    /**
     * Command name
     */
    private String name = "history";
    /**
     * Command description
     */
    private String desc = "history : вывести последние 5 команд (без их аргументов)";

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

    private SystemCommandReceiver receiver;

    public HistoryCommand(SystemCommandReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Prints five last commands (without args)
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.history();
    }
}
