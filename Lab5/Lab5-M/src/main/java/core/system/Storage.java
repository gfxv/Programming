package core.system;

import core.enteties.Movie;

import java.util.HashSet;

public class Storage {

    /**
     * Collection where all movies are stored
     */
    private static HashSet<Movie> movies = new HashSet<>();

    /**
     * Method to add new Movie to Collection
     * @param movie
     */
    public static void addMovie(Movie movie) {
        if (movies.contains(movie)) {
            System.out.println("[E] This movie is already there!");
            return;
        }

        movies.add(movie);
        System.out.println("Movie added successfully!");
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

}
