package core.system;

import core.commands.base.Command;
import shared.exceptions.InvalidInputException;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.util.Map;
import java.util.NoSuchElementException;

public class Executor {

    Map<String, Command> commands = Invoker.getCommands();

    public Executor() {
        Invoker.init();
//        Storage.loadMovies();
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(300000); // 300000 - 5 mins
//                    executeCommand(new ServerRequest("save"));
//                    System.out.println("Collection saved");
//                } catch (InterruptedException ignored) {
//                    ignored.printStackTrace();
//                }
//            }
//        }).start();
    }

    public ServerResponse executeCommand(ServerRequest request) {
        String userCommand = request.getCommand();
        if (commands.containsKey(userCommand)) {
            try {
                History.addCommandToHistory(userCommand);
                ResponseBody response = commands.get(userCommand).execute(request);
                return new ServerResponse(response);
            } catch (InvalidInputException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (Exception ignore) {
            }


        }
        return new ServerResponse(request.getCommand() + " : " + request.getPrimitiveArg());
    }
}
