package shared.sort;

import shared.enteties.Movie;

import java.util.Comparator;

public class SortByName implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        return m1.getName().compareTo(m2.getName());
    }
}
