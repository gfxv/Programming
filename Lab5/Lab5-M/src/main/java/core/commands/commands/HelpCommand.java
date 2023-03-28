package core.commands.commands;

import core.commands.base.Command;
import core.receivers.SystemCommandReceiver;
import core.system.Invoker;


public class HelpCommand implements Command {
    /**
     * Command name
     */
    private String name = "help";
    /**
     * Command description
     */
    private String desc = "help : вывести справку по доступным командам";

    /**
     * Getter for name field
     * @return command name
     */

    private SystemCommandReceiver receiver;

    public HelpCommand(SystemCommandReceiver receiver) {
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
     * Shows info about all available commands
     */
    @Override
    public void execute(String args) {
        this.receiver.help();
    }
}
