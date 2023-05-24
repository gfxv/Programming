package core.receivers;

import core.commands.base.Command;
import core.system.History;
import core.system.Invoker;
import core.system.Storage;
import shared.serializables.ResponseBody;

import java.util.List;


public class SystemCommandReceiver {

    /**
     * 'help' command implementation
     */
    public ResponseBody help() {
        String[] result = new String[Invoker.getCommands().size() - 1];
        int i = 0;
        for (Command command : Invoker.getCommands().values()) {
            if (command.getDesc().contains("help")) continue;
            result[i] = command.getDesc();
            i++;
        }

        return new ResponseBody(result);
    }

    /**
     * 'info' command implementation
     */
    public ResponseBody info() {

        return new ResponseBody(new String[]{
                "Storage size: " + Storage.getMovies().size(),
                "Storage type: " + Storage.getMovies().getClass().getSimpleName()
        });
    }

    /**
     * 'exit' command implementation
     */
    public ResponseBody exit() {
        return new ResponseBody(new String[]{"Closing connection..."});
    }

    /**
     * 'history' command implementation
     */
    public ResponseBody history() {
        List<String> commands = History.getHistory();

        if (commands.size() == 0) {
//            System.out.println("History is empty");
            return new ResponseBody(new String[]{"History is empty"});
        }

        System.out.println("Last 5 commands:");
        for (String command : commands) {
            System.out.println(command);
        }
        System.out.println();
        return new ResponseBody(new String[]{"IN DEVELOPMENT"});
    }

    /**
     * 'execute_script' command implementation
     */
    public ResponseBody executeScript(String args) {
        return new ResponseBody(new String[]{"Script executed"});
    }

}
