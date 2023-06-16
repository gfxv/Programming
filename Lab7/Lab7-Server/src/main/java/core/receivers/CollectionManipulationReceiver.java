package core.receivers;

import core.db.crud.MovieCRUD;
import shared.enteties.Movie;
import core.system.Config;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

import java.sql.SQLException;
import java.util.*;

public class CollectionManipulationReceiver {


    /**
     *  'show' command implementation
     */

    public ResponseBody show(ServerRequest request) {

        HashSet<Movie> movies = null;
        try {
            movies = MovieCRUD.getAllMovies(Config.getConnection(), request.getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (movies == null) return new ResponseBody(new String[]{"Failed to load collection"});
        if (movies.isEmpty()) return new ResponseBody(new String[]{"Collection is empty!"});
        return new ResponseBody(movies);
    }

    /**
     *  'clear' command implementation
     */
    public ResponseBody clear(ServerRequest req) {
        try {
            MovieCRUD.deleteAll(Config.getConnection(), req.getUser());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ResponseBody(new String[]{"Collection cleared."});
    }

//    public ResponseBody save() {
//        FileManager fm = null;
//        try {
//            fm = new FileManager(Config.getFilepath());
//        } catch (Exception ignored) {}
//        try {
//            assert fm != null;
//            fm.clearFile();
//        } catch (IOException e) {
//            System.out.println("Oops, something went wrong");
//        }
//        for (Movie movie : Storage.getMovies()) {
//            try {
//                fm.append(movie.toArray());
//            } catch (Exception ignored) {
//            }
//        }
//        return new ResponseBody(new String[]{"Collection saved to " + Config.getFilepath()});
//    }
}