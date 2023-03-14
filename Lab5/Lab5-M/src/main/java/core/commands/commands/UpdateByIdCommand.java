package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import core.system.Commands;
import core.system.Config;
import core.system.Storage;

public class UpdateByIdCommand implements Command {
    /**
     * Command name
     */
    private String name = "update_by_id";
    /**
     * Command description
     */
    private String desc = "update_by_id {element} : обновить значение элемента коллекции, id которого равен заданному";
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
     * Updates the value of the collection element whose id is equal to the given one
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {
        InputManager input = new InputManager();
        try {
            Movie m = input.getMovie();
            m.setId(Long.parseLong(Config.getCmdParam()));
            Commands.getCommands().get(new RemoveByIdCommand().getName()).execute();
            Storage.addMovie(m);
        } catch (NullPointerException ignored) {}

    }
}
