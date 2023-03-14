package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class RemoveLowerCommand implements Command {
    /**
     * Command name
     */
    private String name = "remove_lower";
    /**
     * Command description
     */
    private String desc = "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный;";

    /**
     * Getter for name field
     * @return command name
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * Getter for description field
     * @return command description
     */
    @Override
    public String getDesc() {
        return this.desc;
    }

    /**
     * Removes all elements from the Storage that a lower than given
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {
        HashSet<Movie> newMovies = new HashSet<>();
        Movie userMovie = null;

        long ID;
        try {
            ID = Long.parseLong(Config.getCmdParam());
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
}
