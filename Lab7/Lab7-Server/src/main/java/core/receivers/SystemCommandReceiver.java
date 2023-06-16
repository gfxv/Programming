package core.receivers;

import core.commands.base.Command;
import core.db.crud.MovieCRUD;
import core.system.Config;
import core.system.History;
import core.system.Invoker;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

import java.sql.SQLException;
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
    public ResponseBody info(ServerRequest req) {

        try {
            return new ResponseBody(new String[]{
                    "Storage size: " + MovieCRUD.getAllMovies(Config.getConnection(), req.getUser()).size(),
                    "Storage type: " + MovieCRUD.getAllMovies(Config.getConnection(), req.getUser()).getClass().getSimpleName()
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            return new ResponseBody(new String[]{"History is empty"});
        }

        System.out.println();
        return new ResponseBody(commands.toArray(String[]::new));
    }

    /**
     * 'execute_script' command implementation
     */
    public ResponseBody executeScript(String args) {
        return new ResponseBody(new String[]{"Script executed"});
    }

}
