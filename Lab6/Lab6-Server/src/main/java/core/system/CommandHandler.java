package core.system;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandHandler {

    public CommandHandler() {
        Storage.loadMovies();
    }

    public ServerResponse run(ServerRequest req) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = Invoker.getCommands();

        String userCommand = req.getCommand();
        String userArgs = req.getPrimitiveArg();
        try {
            History.addCommandToHistory(userCommand);
            String[] res = commands.get(userCommand).execute(userArgs);
            ServerResponse serverResponse = new ServerResponse(res);
            return serverResponse;
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Bye!");
            System.exit(0);
        } catch (Exception ignore) {
        }

        System.out.println();
        return null;
    }

}
