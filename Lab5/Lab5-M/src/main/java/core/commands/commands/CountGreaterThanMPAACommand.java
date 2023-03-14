package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.enteties.MpaaRating;
import core.exceptions.InvalidInputException;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class CountGreaterThanMPAACommand implements Command {
    /**
     * Command name
     */
    private String name = "count_greater_than_mpaa_rating";
    /**
     * Command description
     */
    private String desc = "count_greater_than_mpaa_rating mpaaRating : вывести количество элементов, значение поля mpaaRating которых больше заданного";

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
     * Prints the number of elements with given MPAA Rating
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {

        HashSet<Movie> movies = Storage.getMovies();

        MpaaRating userMpaa = null;

        for (MpaaRating mpaa : MpaaRating.values()) {
            if (mpaa.name().equals(Config.getCmdParam())) {
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

}
