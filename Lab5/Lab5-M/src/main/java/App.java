import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.system.Commands;
import core.system.Config;
import core.system.History;
import core.system.Init;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        if (args.length > 1) {
            System.out.println("Кол-во аргументов > 1");
            System.exit(0);
        } if (args.length == 0) {
            System.out.println("Вы не передали имя файла");
            System.exit(0);
        } else {
            String newValue = "src/main/java/" + args[0];
            Config.setFilepath(newValue);
        }

        Init.setCommands();
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = Commands.getCommands();

        while(true) {
            System.out.println("Type \"help\" to list all available commands");
            System.out.print(">>> ");
            String[] input;
            try {
                input = scanner.nextLine().split(" ");
            } catch (NoSuchElementException ignored) {
                break;
            }
            String user_command = input[0];
            if (input.length == 2) Config.setCmdParam(input[1]);
            if (commands.containsKey(user_command)) {
                try {
                    History.addCommandToHistory(user_command);
                    commands.get(user_command).execute();
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                } catch (NoSuchElementException e) {
                    System.out.println("Bye!");
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("Oops...Something went wrong");
                }
            }
            Config.setCmdParam("");
            System.out.println();
        }
    }
}
