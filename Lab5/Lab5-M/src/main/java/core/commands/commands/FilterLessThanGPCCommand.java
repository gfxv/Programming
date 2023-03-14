package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.system.Config;
import core.system.Storage;

import java.util.HashSet;

public class FilterLessThanGPCCommand implements Command {
    /**
     * Command name
     */
    private String name = "filter_less_than_golden_palm_count";
    /**
     * Command description
     */
    private String desc = "filter_less_than_golden_palm_count goldenPalmCount : вывести элементы, значение поля goldenPalmCount которых меньше заданного";

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
     * Prints element if its goldenPalmCount value is less than given
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {

        HashSet<Movie> movies = Storage.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Collection is empty!");
            return;
        }

        long GPC;
        try {
            GPC = Long.parseLong(Config.getCmdParam());
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
