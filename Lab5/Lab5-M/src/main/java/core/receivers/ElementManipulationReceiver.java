package core.receivers;

import core.enteties.Movie;
import core.enteties.MpaaRating;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.system.Storage;

import java.util.HashSet;

public class ElementManipulationReceiver {

    public void add() throws InvalidInputException {
        InputManager input = new InputManager();
        try {
            Movie m = input.getMovie();
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {}
    }

    public void updateById(String args) throws InvalidInputException {
        InputManager input = new InputManager();
        try {
            Movie m = input.getMovie();
            m.setId(Long.parseLong(args));
            this.removeById(args);
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {}
    }

    public void removeById(String args) throws InvalidInputException {
        Movie movieToDel = null;
        for (Movie movie : Storage.getMovies()) {
            long id;
            try {
                id = Long.parseLong(args);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("ID must be Integer(Long)");
            }
            if (id == movie.getId()) {
                movieToDel = movie;
                break;
            }
        }
        Storage.getMovies().remove(movieToDel);
    }

    public void addIfMin() throws InvalidInputException {
        InputManager input = new InputManager();

        Movie lowest = null;
        HashSet<Movie> Movies = Storage.getMovies();
        if (Movies.size() == 0) {
            try {
                Movie m = input.getMovie();
                Storage.addMovie(m);
            } catch (NullPointerException ignored) {}
            return;
        }

        for (Movie movie : Movies) {
            if (lowest == null) {
                lowest = movie;
                continue;
            }

            if ((movie.getGoldenPalmCount() + movie.getOscarsCount()) <
                    (lowest.getGoldenPalmCount() + lowest.getOscarsCount())) {
                lowest = movie;
            }
        }

        try {
            Movie m = input.getMovie();

            assert lowest != null;
            if (m.getGoldenPalmCount() + m.getOscarsCount() < lowest.getOscarsCount() + lowest.getGoldenPalmCount()) {
                Storage.addMovie(m);
            } else {
                System.out.println("Can't add this element, it is not the lowest");
            }

        } catch (NullPointerException ignored) {}
    }

    public void removeLower(String args) throws InvalidInputException {
        HashSet<Movie> newMovies = new HashSet<>();
        Movie userMovie = null;

        long ID;
        try {
            ID = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ID must be Integer(Long)");
        }

        for (Movie movie : Storage.getMovies()) {
            if (ID == movie.getId()) {
                userMovie = movie;
                break;
            }
        }


        if (userMovie == null) {
            throw new InvalidInputException("No such element");
        }

        // удалить из коллекции все элементы, меньшие, чем заданный
        for (Movie movie : Storage.getMovies()) {
            if ((movie.getGoldenPalmCount() + movie.getOscarsCount()) >=
                    (userMovie.getGoldenPalmCount() + userMovie.getOscarsCount())) {
                newMovies.add(movie);
            }
        }

        Storage.setMovies(newMovies);
    }

    public void removeByTBO(String args) throws InvalidInputException {
        Movie movieToDel = null;
        for (Movie movie : Storage.getMovies()) {
            float TBO;
            try {
                TBO = Float.parseFloat(args);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("TBO must be Float");
            }
            if (TBO == movie.getTotalBoxOffice()) {
                movieToDel = movie;
                break;
            }
        }

        Storage.getMovies().remove(movieToDel);
    }

    public void countGreaterThanMPAA(String args) throws InvalidInputException {
        HashSet<Movie> movies = Storage.getMovies();

        MpaaRating userMpaa = null;

        for (MpaaRating mpaa : MpaaRating.values()) {
            if (mpaa.name().equals(args)) {
                userMpaa = mpaa;
            }
        }

        if (userMpaa == null) {
            throw new InvalidInputException("Invalid MPAA Rating");
        }

        int counter = 0;
        for (Movie movie : movies) {
            if (movie.getMpaaRating().getValue() > userMpaa.getValue()) {
                counter++;
            }
        }

        System.out.printf("There are %d movies with Mpaa Rating greater than %s", counter, userMpaa);
        System.out.println();
    }

    public void filterLessThanGPCC(String args) throws InvalidInputException {
        HashSet<Movie> movies = Storage.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Collection is empty!");
            return;
        }

        long GPC;
        try {
            GPC = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("GPC must be Integer(Long)");
        }

        for (Movie movie : movies) {
            if (movie.getGoldenPalmCount() < GPC) {
                String[] movieAsArr = movie.toArray();
                for (String movie_attr : movieAsArr) {
                    System.out.print(movie_attr + "\t");
                }
                System.out.println();
            }

        }
    }

}
