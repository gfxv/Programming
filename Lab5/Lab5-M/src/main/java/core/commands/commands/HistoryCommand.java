package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
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

    /**
     * Prints five last commands (without args)
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {

        List<String> commands = History.getHistory();

        if (commands.size() == 0) {
            System.out.println("History is empty");
            return;
        }

        System.out.println("Last 5 commands:");
        for (String command: commands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}
