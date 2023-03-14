package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.system.Commands;
import core.system.Config;
import core.system.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    /**
     * Command name
     */
    private String name = "execute_script";
    /**
     * Command description
     */
    private String desc = "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";

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
     * Reads and executes script from given file
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {

        File file = new File(Config.getCmdParam());
        Map<String, Command> commands = Commands.getCommands();

        try {
            Scanner sc = new Scanner(file);
            InputManager.setScanner(sc);
            InputManager.setScriptMode(true);
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(" ");
                String user_command = input[0];
                if (user_command.equals(this.getName())) {
                    System.out.println("Nice try :)");
                    continue;
                }
                if (input.length == 2) Config.setCmdParam(input[1]);
                if (commands.containsKey(user_command)) {
                    try {
                        History.addCommandToHistory(user_command);
                        commands.get(user_command).execute();
                    } catch (InvalidInputException ignored) {
                    }
                }
                Config.setCmdParam("");
            }
            sc.close();
        }
        catch (FileNotFoundException ignored) {
        }

        InputManager.setScanner(new Scanner(System.in));
        InputManager.setScriptMode(false);

    }
}
