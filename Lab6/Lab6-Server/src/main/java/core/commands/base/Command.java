package core.commands.base;

import shared.exceptions.InvalidInputException;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;

public interface Command {
    String getName();
    String getDesc();
    boolean isPrimitiveArg();
    boolean isComplexArg();
    ResponseBody execute(ServerRequest req) throws InvalidInputException;

}
