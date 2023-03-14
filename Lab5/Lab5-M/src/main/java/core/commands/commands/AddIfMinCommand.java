package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.system.Storage;

import java.util.HashSet;

public class AddIfMinCommand implements Command {

    /**
     * Command name
     */
    private String name = "add_if_min";
    /**
     * Command description
     */
    private String desc = "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";

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
     * Adds new element to the Storage, if its value is the lowest in the collection
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {

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
}
