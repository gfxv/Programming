package core.system;

import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.exceptions.InvalidLengthException;
import core.exceptions.UniqueElementException;
import core.managers.FileManager;

import java.util.HashSet;
import java.util.List;

public class Storage {

    /**
     * Collection where all movies are stored
     */
    private static HashSet<Movie> movies = new HashSet<>();

    /**
     * Method to add new Movie to Collection
     * @param movie
     */
    public static void addMovie(Movie movie) throws UniqueElementException {
        if (movies.contains(movie)) {
            System.out.println("[E] This movie is already there!");
            throw new UniqueElementException();
        }
        movies.add(movie);

    }

    /**
     * Getter for Movies Collection
     * @return movies
     */
    public static HashSet<Movie> getMovies() {
        return movies;
    }

    /**
     * Clears collection
     */
    public static void dropStorage() {
        movies.clear();
        System.out.println("Collection cleared");
    }

    /**
     * Replaces old collection with new one
     * @param _movies
     */
    public static void setMovies(HashSet<Movie> _movies) {
        movies = _movies;
    }

    public static void loadMovies() {
        FileManager fm = null;
        try {
            fm = new FileManager(Config.getFilepath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<String[]> records = null;
        try {
            records =  fm.getAll();
        } catch (Exception ignored) {}

        if (records.isEmpty()) {
            return;
        }

        for (int i = 0; i < records.size(); i++) {
            if (i == 0) continue;
            Movie m;
            try {
                m = Movie.arrayToMovie(records.get(i));
                Storage.addMovie(m);
            } catch (InvalidInputException ignored) {}
            catch (UniqueElementException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }
}
