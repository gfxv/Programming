package core.receivers;

import shared.enteties.Movie;
import core.managers.FileManager;
import core.system.Config;
import core.system.Storage;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

import java.io.IOException;
import java.util.HashSet;

public class CollectionManipulationReceiver {


    /**
     *  'show' command implementation
     */

    public ResponseBody show(ServerRequest request) {
        HashSet<Movie> movies = Storage.getMovies();

        if (movies.isEmpty()) {
            System.out.println();
            return new ResponseBody(new String[]{"Collection is empty!"});
        }
        return new ResponseBody(movies);
    }

    /**
     *  'clear' command implementation
     */
    public ResponseBody clear() {
        Storage.dropStorage();
        return new ResponseBody(new String[]{"Collection cleared."});
    }


    /**
     *  'save' command implementation
     */

    // TODO: REMOVE SAVE COMMAND FROM CLIENT SIDE
    public ResponseBody save() {
        FileManager fm = null;
        try {
            fm = new FileManager(Config.getFilepath());
        } catch (Exception ignored) {}
        try {
            assert fm != null;
            fm.clearFile();
        } catch (IOException e) {
            System.out.println("Oops, something went wrong");
        }
        for (Movie movie : Storage.getMovies()) {
            try {
                fm.append(movie.toArray());
            } catch (Exception ignored) {
            }
        }
        return new ResponseBody(new String[]{"Collection saved to " + Config.getFilepath()});
    }
}
