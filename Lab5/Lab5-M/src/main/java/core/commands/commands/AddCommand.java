package core.commands.commands;


import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.system.Storage;


public class AddCommand implements Command {

    /**
     * Command name
     */
    private String name = "add";

    /**
     * Command description
     */
    private String desc = "add {element} : добавить новый элемент в коллекцию";

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
     * Adds new element to the Storage
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {

        InputManager input = new InputManager();
        try {
            Movie m = input.getMovie();
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {}
    }
}
