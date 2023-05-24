package shared.validators;

import shared.serializables.CommandInfoObject;

import java.util.List;

public class CommandValidator {
    public static boolean validate(String userCommand, List<CommandInfoObject> commands) {
        for (CommandInfoObject command : commands) {
            if (userCommand.equals(command.getCommand())) {
                return true;
            }
        }
        return false;
    }
}
