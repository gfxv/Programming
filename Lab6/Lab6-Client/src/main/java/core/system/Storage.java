package core.system;

import shared.enteties.Movie;
import shared.exceptions.InvalidInputException;
import shared.exceptions.UniqueElementException;
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



    /**
     * Getter for Movies Collection
     * @return movies
     */
    public static HashSet<Movie> getMovies() {
        return movies;
    }



    private static boolean has(Movie movie) {
        for (Movie m : movies) {
            if (m.getId().equals(movie.getId())) {
                return true;
            }
        }
        return false;
    }

}
