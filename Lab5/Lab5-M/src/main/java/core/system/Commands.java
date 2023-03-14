package core.system;

import core.commands.base.Command;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    /**
     * HashMap for storing commands
     */
    private static Map<String, Command> commands = new HashMap<>();

    /**
     * Setter for commands
     * @param cmd
     */
    public static void setCommands(Map<String, Command> cmd) {
        commands = cmd;
    }

    /**
     * Getter for command
     * @return commands
     */
    public static Map<String, Command> getCommands() {
        return commands;
    }

}
