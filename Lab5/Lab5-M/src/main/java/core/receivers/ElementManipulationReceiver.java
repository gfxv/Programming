package core.receivers;

import core.enteties.Movie;
import core.enteties.MpaaRating;
import core.exceptions.InvalidInputException;
import core.exceptions.UniqueElementException;
import core.managers.InputManager;
import core.system.Storage;
import core.validators.TBOValidator;

import java.util.HashSet;

public class ElementManipulationReceiver {

    /**
     *  'add' command implementation
     */
    public void add() throws InvalidInputException {
        InputManager input = new InputManager();
        try {
            Movie m = input.getMovie();
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {}
        catch (UniqueElementException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Movie added successfully!");
    }

    /**
     *  'update_by_id' command implementation
     */
    public void updateById(String args) throws InvalidInputException {
        long id;
        try {
            id = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ID have to be Integer or Long");
        }

        boolean found = false;
        for (Movie m : Storage.getMovies()) {
            if (id == m.getId().longValue()) {
                found = true;
            }
        }

        if (!found) throw new InvalidInputException("No such ID");
        InputManager input = new InputManager();
        try {
            Movie m = input.getMovie();
            m.setId(Long.parseLong(args));
            this.removeById(args);
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {}
        catch (UniqueElementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  'remove_by_id' command implementation
     */
    public void removeById(String args) throws InvalidInputException {
        Movie movieToDel = null;
        long id;
        try {
            id = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ID have to be Integer or Long");
        }

        for (Movie movie : Storage.getMovies()) {
            if (id == movie.getId()) {
                movieToDel = movie;
                Storage.getMovies().remove(movieToDel);
                System.out.printf("Movie with id %d was deleted successfully\n", id);
                return;
            }
        }

        System.out.printf("No such element with id %s\n", args);
    }

    /**
     *  'add_if_min' command implementation
     */
    public void addIfMin() throws InvalidInputException {
        InputManager input = new InputManager();

        Movie lowest = null;
        HashSet<Movie> Movies = Storage.getMovies();
        if (Movies.size() == 0) {
            try {
                Movie m = input.getMovie();
                Storage.addMovie(m);
            } catch (NullPointerException ignored) {}
            catch (UniqueElementException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Movie added successfully!");
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
        catch (UniqueElementException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Movie added successfully!");
    }

    /**
     *  'remove_lower' command implementation
     */
    public void removeLower(String args) throws InvalidInputException {
        HashSet<Movie> newMovies = new HashSet<>();
        Movie userMovie = null;

        long ID;
        try {
            ID = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ID have to be Integer or Long");
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

    /**
     *  'remove_by_total_box_office' command implementation
     */
    public void removeByTBO(String args) throws InvalidInputException {
        Movie movieToDel = null;
        TBOValidator.validate(args);
        for (Movie movie : Storage.getMovies()) {
            float TBO;
            try {
                TBO = Float.parseFloat(args);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("TBO have to be Float");
            }
            if (TBO == movie.getTotalBoxOffice()) {
                movieToDel = movie;
                break;
            }
        }

        Storage.getMovies().remove(movieToDel);
    }

    /**
     *  'count_greater_than_mpaa' command implementation
     */
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

    /**
     *  'filter_less_than_GPCC' command implementation
     */
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
            throw new InvalidInputException("GPC have to be Integer or Long");
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
