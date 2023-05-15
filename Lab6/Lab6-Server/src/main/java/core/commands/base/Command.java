package core.commands.base;

import core.exceptions.InvalidInputException;

public interface Command {
    String getName();
    String getDesc();
    void execute(String args) throws InvalidInputException;

}
