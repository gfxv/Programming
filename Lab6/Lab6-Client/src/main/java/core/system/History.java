package core.system;

import core.commands.base.Command;

import java.util.ArrayList;
import java.util.List;

public class History {

    /**
     * Array that stores last 5 commands
     */
    private static List<String> history = new ArrayList<>();

    /**
     * Adds new command to history
     * @param command
     */
    public static void addCommandToHistory(String command) {
        int length = history.size();

        if (length == 5) {
            shift();
        }

        history.add(command);
    }

    /**
     * Shifts all elements of History
     */
    private static void shift() {
        for (int i = 0; i < history.size() - 1; i++) {
            history.set(i, history.get(i + 1));
        }

        history.remove(4);
    }

    /**
     * Getter for history
     * @return history
     */
    public static List<String> getHistory() {
        return history;
    }

}
