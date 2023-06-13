package core.managers;

import shared.enteties.Movie;
import shared.exceptions.InvalidInputException;
import shared.serializables.CommandInfoObject;
import shared.serializables.ServerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager {

    private List<ServerRequest> requests = new ArrayList<>();

    public InputManager(Scanner scanner, List<CommandInfoObject> commands) throws InvalidInputException {
        System.out.println("Type \"help\" to list all available commands");
        System.out.print(">>> ");
        CommandInfoObject command = null;
        String[] input = scanner.nextLine().split(" ");
        for (CommandInfoObject cmd : commands) {
            if (input[0].equals(cmd.getCommand())) {
                command = cmd;
            }
        }

        if (command == null) throw new InvalidInputException("Invalid Command");

        ServerRequest request = null;

        if (command.hasPrimitiveArg() && command.hasComplexArg()) {
            Movie movie = new MovieInputManager(scanner).getMovie();
            request = new ServerRequest(command.getCommand(), input[1], movie);
            requests.add(request);
            return;
        }
        if (command.hasPrimitiveArg()) {
            request = new ServerRequest(command.getCommand(), input[1]);
            requests.add(request);
            return;
        }
        if (command.hasComplexArg()) {
            Movie movie = new MovieInputManager(scanner).getMovie();
            request = new ServerRequest(command.getCommand(), movie);
            requests.add(request);
            return;
        }
        if (!command.hasPrimitiveArg() && !command.hasComplexArg()) {
            request = new ServerRequest(command.getCommand());
            requests.add(request);
        }


    }

    public InputManager(Scanner scanner, List<CommandInfoObject> commands, boolean scriptMode) throws InvalidInputException {
        CommandInfoObject command = null;
        MovieInputManager.setScriptMode(scriptMode);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.length() == 0) continue;
            String[] input = line.split(" ");
            for (CommandInfoObject cmd : commands) {
                if (input[0].equals(cmd.getCommand())) {
                    command = cmd;
                }
            }
            if (command == null) throw new InvalidInputException("Invalid Command");
            if (command.getCommand().equals("execute_script")) continue;

            ServerRequest request = null;

            if (command.hasPrimitiveArg() && command.hasComplexArg()) {
                Movie movie = new MovieInputManager(scanner).getMovie();
                request = new ServerRequest(command.getCommand(), input[1], movie);
                requests.add(request);
                continue;
            }
            if (command.hasPrimitiveArg()) {
                request = new ServerRequest(command.getCommand(), input[1]);
                requests.add(request);
                continue;
            }
            if (command.hasComplexArg()) {
                Movie movie = new MovieInputManager(scanner).getMovie();
                request = new ServerRequest(command.getCommand(), movie);
                requests.add(request);
                continue;
            } if (!command.hasPrimitiveArg() && !command.hasComplexArg()) {
                request = new ServerRequest(command.getCommand());
                requests.add(request);
            }
        }
        MovieInputManager.setScriptMode(!scriptMode);
    }

    public List<ServerRequest> getRequest() {
        return requests;
    }
}
