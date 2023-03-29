package core.commands.commands;

import core.commands.base.Command;
import core.receivers.SystemCommandReceiver;


public class HelpCommand implements Command {

    private String name = "help";
    private String desc = "help : вывести справку по доступным командам";
    private SystemCommandReceiver receiver;

    public HelpCommand(SystemCommandReceiver receiver) {
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
     * Shows info about all available commands
     */
    @Override
    public void execute(String args) {
        this.receiver.help();
    }
}
