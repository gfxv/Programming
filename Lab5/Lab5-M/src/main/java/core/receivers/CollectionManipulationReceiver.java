package core.receivers;

import core.enteties.Movie;
import core.managers.FileManager;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class CollectionManipulationReceiver {

    public void show() {
        HashSet<Movie> movies = Storage.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Collection is empty!");
            return;
        }

        for (Movie movie : movies) {
            String[] movieAsArr = movie.toArray();
            for (String movie_attr : movieAsArr) {
                System.out.print(movie_attr + "\t");
            }
            System.out.println();
        }
    }

    public void clear() {
        Storage.dropStorage();
    }

    public void save() {
        FileManager fm = null;
        try {
            fm = new FileManager(Config.getFilepath());
        } catch (Exception ignored) {}
        for (Movie movie : Storage.getMovies()) {
            try {
                if (fm != null) {
                    fm.append(movie.toArray());
                }
            } catch (Exception ignored) {
                return;
            }
        }
        System.out.println("Collection saved to " + Config.getFilepath());
    }
}
