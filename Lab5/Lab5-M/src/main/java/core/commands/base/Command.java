package core.commands.base;

import core.exceptions.InvalidInputException;

public interface Command {
    String getName();
    String getDesc();
    void execute() throws InvalidInputException;

}
