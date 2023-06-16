package core.receivers;

import core.db.crud.MovieCRUD;
import core.system.Config;
import shared.enteties.Movie;
import shared.enteties.MpaaRating;
import shared.exceptions.InvalidInputException;
import shared.validators.TBOValidator;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ElementManipulationReceiver {

    private static final Object lock = new Object();

    /**
     * 'add' command implementation
     */
    public ResponseBody add(ServerRequest req) {
        try {
            MovieCRUD.addMovie(Config.getConnection(), req.getUser(), req.getComplexArg());
        } catch (NullPointerException ignored) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseBody("Error while adding new movie!");
        }
        return new ResponseBody(new String[]{"Movie added successfully"});
    }

    /**
     * 'update_by_id' command implementation
     */
    public ResponseBody updateById(ServerRequest req) {
        long id = 0;
        try {
            id = Long.parseLong(req.getPrimitiveArg());
        } catch (NumberFormatException e) {
            return new ResponseBody("ID have to be Integer or Long");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long finalId = id;
        boolean found = false;
        try {
            synchronized (lock) {
                found = MovieCRUD.getAllMoviesByUser(Config.getConnection(), req.getUser())
                        .stream()
                        .anyMatch(movie -> finalId == movie.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!found) return new ResponseBody("No such ID");
        try {
            MovieCRUD.updateById(Config.getConnection(), req.getUser(), req.getComplexArg(), id);
        } catch (NullPointerException ignored) {
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ResponseBody(new String[]{"Movie updated."});
    }

    /**
     * 'remove_by_id' command implementation
     */
    public ResponseBody removeById(ServerRequest req) {
        long id;
        try {
            id = Long.parseLong(req.getPrimitiveArg());
        } catch (NumberFormatException e) {
            return new ResponseBody("ID have to be Integer or Long");
        }

        try {
            synchronized (lock) {
                for (Movie movie : MovieCRUD.getAllMoviesByUser(Config.getConnection(), req.getUser())) {
                    if (id == movie.getId()) {
                        MovieCRUD.removeById(Config.getConnection(), req.getUser(), id);
                        return new ResponseBody(new String[]{"Movie with id " + id + " was deleted successfully"});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ResponseBody(new String[]{"No such element with id " + req.getPrimitiveArg()});

    }

    /**
     * 'add_if_min' command implementation
     */
    public ResponseBody addIfMin(ServerRequest req) {
        Movie lowest = null;
        HashSet<Movie> Movies;
        try {
            Movies = MovieCRUD.getAllMoviesByUser(Config.getConnection(), req.getUser());
            if (Movies.size() == 0) {
                MovieCRUD.addMovie(Config.getConnection(), req.getUser(), req.getComplexArg());
                return new ResponseBody(new String[]{"Movie added successfully!"});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        synchronized (lock) {
            for (Movie movie : Movies) {
                if (lowest == null) {
                    lowest = movie;
                    continue;
                }
                if (movie.compareTo(lowest) < 0) lowest = movie;
            }
        }

        try {
            synchronized (lock) {
                Movie m = req.getComplexArg();
                assert lowest != null;
                if (m.compareTo(lowest) < 0) { // add if less
                    MovieCRUD.addMovie(Config.getConnection(), req.getUser(), m);
                } else {
                    return new ResponseBody("Can't add this element, it is not the lowest");
                }
            }

        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseBody(new String[]{"Movie added successfully!"});
    }

    /**
     * 'remove_lower' command implementation
     */
    public ResponseBody removeLower(ServerRequest req) {

        Movie userMovie = req.getComplexArg();
        try {
            synchronized (lock) {
                HashSet<Movie> moviesToDel = (HashSet<Movie>) MovieCRUD.getAllMoviesByUser(Config.getConnection(), req.getUser())
                        .stream()
                        .filter(movie -> movie.compareTo(userMovie) < 0)
                        .collect(Collectors.toSet());
                moviesToDel.forEach(movie -> {
                    try {
                        MovieCRUD.removeById(Config.getConnection(), req.getUser(), movie.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
            return new ResponseBody(MovieCRUD.getAllMoviesByUser(Config.getConnection(), req.getUser()));
        } catch (NoSuchElementException | SQLException e) {
            return new ResponseBody("AHTUNG AHTUNG!");
        }
    }

    /**
     * 'remove_by_total_box_office' command implementation
     */
    public ResponseBody removeByTBO(ServerRequest req) {
        float TBO;
        Movie movieToDel;
        try {
            synchronized (lock) {
                TBOValidator.validate(req.getPrimitiveArg());
                TBO = Float.parseFloat(req.getPrimitiveArg());
                movieToDel = MovieCRUD.getAllMoviesByUser(Config.getConnection(), req.getUser())
                        .stream()
                        .filter(movie -> TBO == movie.getTotalBoxOffice())
                        .findAny()
                        .get();
            }
        } catch (NumberFormatException e) {
            return new ResponseBody("TBO have to be Float");
        } catch (InvalidInputException e) {
            return new ResponseBody(e.getMessage());
        } catch (NoSuchElementException e) {
            return new ResponseBody("No element with given TBO");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            MovieCRUD.removeById(Config.getConnection(), req.getUser(), movieToDel.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseBody(new String[]{"Movie removed."});
    }

    /**
     * 'count_greater_than_mpaa' command implementation
     */
    public ResponseBody countGreaterThanMPAA(ServerRequest request) {
        HashSet<Movie> movies = null;
        try {
            movies = MovieCRUD.getAllMovies(Config.getConnection(), request.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MpaaRating userMpaa = null;

        synchronized (lock) {
            for (MpaaRating mpaa : MpaaRating.values()) {
                if (mpaa.name().equals(request.getPrimitiveArg())) {
                    userMpaa = mpaa;
                }
            }
        }

        if (userMpaa == null) return new ResponseBody("Invalid MPAA Rating");

        synchronized (lock) {
            MpaaRating finalUserMpaa = userMpaa;
            int counter = movies.stream()
                    .filter(e -> e.getMpaaRating().getValue() > finalUserMpaa.getValue())
                    .toList()
                    .size();
            String result = "There are " + counter + " movie(s) with Mpaa Rating greater than " + userMpaa;
            return new ResponseBody(new String[]{result});
        }
    }

    /**
     * 'filter_less_than_GPCC' command implementation
     */
    public ResponseBody filterLessThanGPCC(ServerRequest req) {
        HashSet<Movie> movies;
        try {
            movies = MovieCRUD.getAllMovies(Config.getConnection(), req.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (movies.isEmpty()) return new ResponseBody(new String[]{"Collection is empty!"});
        long GPC;
        try {
            GPC = Long.parseLong(req.getPrimitiveArg());
        } catch (NumberFormatException e) {
            return new ResponseBody("GPC have to be Integer or Long");
        }

        synchronized (lock) {
            HashSet<Movie> resultMovies = (HashSet<Movie>) movies.stream()
                    .filter(movie -> movie.getGoldenPalmCount() < GPC)
                    .collect(Collectors.toSet());
            return new ResponseBody(resultMovies);

        }
    }

}
