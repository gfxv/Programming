package core.commands.base;

import core.exceptions.InvalidInputException;
import shared.serializables.ServerRequest;

public interface Command {
    String getName();
    String getDesc();
    boolean isPrimitiveArg();
    boolean isComplexArg();
    String[] execute(ServerRequest req) throws InvalidInputException;

}
