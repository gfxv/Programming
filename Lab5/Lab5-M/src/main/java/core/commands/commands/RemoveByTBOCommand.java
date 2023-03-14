package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class RemoveByTBOCommand  implements Command {
    /**
     * Command name
     */
    private String name = "remove_any_by_total_box_office";
    /**
     * Command description
     */
    private String desc = "remove_any_by_total_box_office totalBoxOffice : удалить из коллекции один элемент, значение поля totalBoxOffice которого эквивалентно заданному";
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
     * Removes any element with given Total Box Office value
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {
        Movie movieToDel = null;
        for (Movie movie : Storage.getMovies()) {
            float TBO;
            try {
                TBO = Float.parseFloat(Config.getCmdParam());
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

}
