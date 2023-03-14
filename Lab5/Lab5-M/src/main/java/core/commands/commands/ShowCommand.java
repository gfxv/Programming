package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.system.Storage;

import java.util.HashSet;

public class ShowCommand implements Command {
    /**
     * Command name
     */
    private String name = "show";
    /**
     * Command description
     */
    private String desc = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";

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
     * Prints all elements from the Collection
     */
    @Override
    public void execute() {

        HashSet<Movie> movies = Storage.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Collection is empty!");
            return;
        }

        for (Movie movie : movies) {
            String[] movieAsArr = movie.toArray();
            for (String movie_attr : movieAsArr) {
                System.out.print(movie_attr + "\t");
            }
            System.out.println();
        }

    }
}
