package core.commands.commands;

import core.commands.base.Command;
import core.system.Commands;


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
    public void execute() {
        System.out.println("Информация по всем командам:");
        for (Command command : Commands.getCommands().values()) {
            System.out.println(command.getDesc());
        }
    }
}
