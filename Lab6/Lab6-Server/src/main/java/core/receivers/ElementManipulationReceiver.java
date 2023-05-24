package core.receivers;

import shared.enteties.Movie;
import shared.enteties.MpaaRating;
import shared.exceptions.InvalidInputException;
import shared.exceptions.UniqueElementException;
import core.system.Storage;
import shared.validators.TBOValidator;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ElementManipulationReceiver {

    /**
     * 'add' command implementation
     */
    public ResponseBody add(ServerRequest req) {
        try {
            Movie m = req.getComplexArg();
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        } catch (UniqueElementException e) {
            return new ResponseBody(e.getMessage());
        }
        return new ResponseBody(new String[]{"Movie added successfully"});
    }

    /**
     * 'update_by_id' command implementation
     */
    public ResponseBody updateById(ServerRequest req) {
        long id;
        try {
            id = Long.parseLong(req.getPrimitiveArg());
        } catch (NumberFormatException e) {
            return new ResponseBody("ID have to be Integer or Long");
        }


        boolean found = Storage.getMovies().stream().anyMatch(movie -> id == movie.getId());

        if (!found) return new ResponseBody("No such ID");
        try {
            Movie m = req.getComplexArg();
            m.setId(id);
            this.removeById(req);
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {
        } catch (UniqueElementException e) {
            throw new RuntimeException(e);
        }
        return new ResponseBody(new String[]{"Movie updated."});
    }

    /**
     * 'remove_by_id' command implementation
     */
    public ResponseBody removeById(ServerRequest req) {
        Movie movieToDel;
        long id;
        try {
            id = Long.parseLong(req.getPrimitiveArg());
        } catch (NumberFormatException e) {
            return new ResponseBody("ID have to be Integer or Long");
        }

        for (Movie movie : Storage.getMovies()) {
            if (id == movie.getId()) {
                movieToDel = movie;
                Storage.getMovies().remove(movieToDel);
                return new ResponseBody(new String[]{"Movie with id" + id + "d was deleted successfully"});
            }
        }
        return new ResponseBody(new String[]{"No such element with id %s\n", req.getPrimitiveArg()});
    }

    /**
     * 'add_if_min' command implementation
     */
    public ResponseBody addIfMin(ServerRequest req) {
        Movie lowest = null;
        HashSet<Movie> Movies = Storage.getMovies();
        if (Movies.size() == 0) {
            try {
                Movie m = req.getComplexArg();
                Storage.addMovie(m);
            } catch (NullPointerException ignored) {
            } catch (UniqueElementException e) {
                System.out.println(e.getMessage());
                return new ResponseBody("Element isn't unique");
            }
            return new ResponseBody(new String[]{"Movie added successfully!"});
        }

        for (Movie movie : Movies) {
            if (lowest == null) {
                lowest = movie;
                continue;
            }

            if (movie.compareTo(lowest) < 0) lowest = movie;
        }

        try {
            Movie m = req.getComplexArg();
            assert lowest != null;
            if (m.compareTo(lowest) < 0) {
                Storage.addMovie(m);
            } else {
                return new ResponseBody("Can't add this element, it is not the lowest");
            }
        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        } catch (UniqueElementException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseBody(new String[]{"Movie added successfully!"});
    }

    /**
     * 'remove_lower' command implementation
     */
    public ResponseBody removeLower(String args) throws InvalidInputException {

        long ID;
        try {
            ID = Long.parseLong(args);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ID have to be Integer or Long");
        }

        try {
            Movie userMovie = Storage.getMovies()
                    .stream()
                    .filter(movie -> ID == movie.getId())
                    .findFirst()
                    .get();

            HashSet<Movie> newMovies = (HashSet<Movie>) Storage.getMovies()
                    .stream()
                    .filter(movie -> movie.compareTo(userMovie) >= 0)
                    .collect(Collectors.toSet());

            Storage.setMovies(newMovies);
            return new ResponseBody(newMovies);
        } catch (NoSuchElementException e) {
            return new ResponseBody("No element with given ID");
        }
    }

    /**
     * 'remove_by_total_box_office' command implementation
     */
    public ResponseBody removeByTBO(ServerRequest req) throws InvalidInputException {

        TBOValidator.validate(req.getPrimitiveArg());
        float TBO;
        try {
            TBO = Float.parseFloat(req.getPrimitiveArg());
        } catch (NumberFormatException e) {
            return new ResponseBody("TBO have to be Float");
        }

        Movie movieToDel = Storage.getMovies()
                .stream()
                .filter(movie -> TBO == movie.getTotalBoxOffice())
                .findAny()
                .get();

        Storage.getMovies().remove(movieToDel);
        return new ResponseBody(new String[]{"Movie removed."});
    }

    /**
     * 'count_greater_than_mpaa' command implementation
     */
    public ResponseBody countGreaterThanMPAA(String args) {
        HashSet<Movie> movies = Storage.getMovies();

        MpaaRating userMpaa = null;

        for (MpaaRating mpaa : MpaaRating.values()) {
            if (mpaa.name().equals(args)) {
                userMpaa = mpaa;
            }
        }
        if (userMpaa == null) return new ResponseBody("Invalid MPAA Rating");

        MpaaRating finalUserMpaa = userMpaa;
        int counter = movies.stream()
                .filter(e -> e.getMpaaRating().getValue() > finalUserMpaa.getValue())
                .toList()
                .size();

        String result = "There are " + counter + " movies with Mpaa Rating greater than " + userMpaa;
        return new ResponseBody(new String[]{result});
    }

    /**
     * 'filter_less_than_GPCC' command implementation
     */
    public ResponseBody filterLessThanGPCC(String args) {
        HashSet<Movie> movies = Storage.getMovies();
        if (movies.isEmpty()) return new ResponseBody(new String[]{"Collection is empty!"});
        long GPC;
        try {
            GPC = Long.parseLong(args);
        } catch (NumberFormatException e) {
            return new ResponseBody("GPC have to be Integer or Long");
        }

        HashSet<Movie> resultMovies = (HashSet<Movie>) movies.stream()
                .filter(movie -> movie.getGoldenPalmCount() < GPC)
                .collect(Collectors.toSet());

        return new ResponseBody(resultMovies);
    }

}
