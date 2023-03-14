package core.commands.commands;

import core.commands.base.Command;
import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.FileManager;
import core.system.Config;
import core.system.Storage;

public class SaveCommand implements Command {
    /**
     * Command name
     */
    private String name = "save";
    /**
     * Command description
     */
    private String desc = "save : сохранить коллекцию в файл";
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
     * Saves Collection to file
     * @throws InvalidInputException
     */
    @Override
    public void execute() throws InvalidInputException {
        FileManager fm = null;
        try {
            fm = new FileManager(Config.getFilepath());
        } catch (Exception e) {
            //
        }
        for (Movie movie : Storage.getMovies()) {
            try {
                if (fm != null) {
                    fm.append(movie.toArray());
                }
            } catch (Exception e) {
                //
            }
        }
    }

}
