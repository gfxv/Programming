package core.receivers;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.system.History;
import core.system.Invoker;
import core.system.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SystemCommandReceiver {

    /**
     *  'help' command implementation
     */
    public void help() {
        System.out.println("Информация по всем командам:");
        for (Command command : Invoker.getCommands().values()) {
            System.out.println(command.getDesc());
        }
    }

    /**
     *  'info' command implementation
     */
    public void info() {
        System.out.println("Storage size: " + Storage.getMovies().size());
        System.out.println("Storage type: " + Storage.getMovies().getClass().getSimpleName());
    }

    /**
     *  'exit' command implementation
     */
    public void exit() {
        System.exit(0);
    }

    /**
     *  'history' command implementation
     */
    public void history() {
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

    /**
     *  'execute_script' command implementation
     */
    public void executeScript(String args) {
        File file = new File(args);
        Map<String, Command> commands = Invoker.getCommands();

        try {
            Scanner sc = new Scanner(file);
            InputManager.setScanner(sc);
            InputManager.setScriptMode(true);
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(" ");
                String userCommand = input[0];
                String userArgs = "";
                if (userCommand.equals("execute_script")) {
                    System.out.println("Nice try :)");
                    continue;
                }

                if (input.length == 2) userArgs = input[1];
                if (commands.containsKey(userCommand)) {
                    try {
                        History.addCommandToHistory(userCommand);
                        commands.get(userCommand).execute(userArgs); // input[1] - args
                    } catch (InvalidInputException ignored) {
                    }
                }
            }
            sc.close();
        }
        catch (FileNotFoundException ignored) {
            System.out.println("No such file");
        }

        InputManager.setScanner(new Scanner(System.in));
        InputManager.setScriptMode(false);

    }

}
