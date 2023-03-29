package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;


public class HistoryCommand implements Command {
    private String name = "history";
    private String desc = "history : вывести последние 5 команд (без их аргументов)";
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
    /**
     * Prints five last commands (without args)
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.history();
    }
}
