package core.system;


import core.commands.base.Command;
import core.commands.commands.*;

import java.util.HashMap;
import java.util.Map;

public class Init {

    /**
     * Method that sets up all commands
     */
    public static void setCommands() {
        Map<String, Command> commands = new HashMap<>();

        Command AddCommand = new AddCommand();
        commands.put(AddCommand.getName(), AddCommand);

        Command HelpCommand = new HelpCommand();
        commands.put(HelpCommand.getName(), HelpCommand);

        Command ShowCommand = new ShowCommand();
        commands.put(ShowCommand.getName(), ShowCommand);

        Command ClearCommand = new ClearCommand();
        commands.put(ClearCommand.getName(), ClearCommand);

        Command ExitCommand = new ExitCommand();
        commands.put(ExitCommand.getName(), ExitCommand);

        Command RemoveByIdCommand = new RemoveByIdCommand();
        commands.put(RemoveByIdCommand.getName(), RemoveByIdCommand);

        Command UpdateByIdCommand = new UpdateByIdCommand();
        commands.put(UpdateByIdCommand.getName(), UpdateByIdCommand);

        Command SaveCommand = new SaveCommand();
        commands.put(SaveCommand.getName(), SaveCommand);

        Command HistoryCommand = new HistoryCommand();
        commands.put(HistoryCommand.getName(), HistoryCommand);

        Command ExecuteScriptCommand = new ExecuteScriptCommand();
        commands.put(ExecuteScriptCommand.getName(), ExecuteScriptCommand);

        Command AddIfMinCommand = new AddIfMinCommand();
        commands.put(AddIfMinCommand.getName(), AddIfMinCommand);

        Command RemoveByTBOCommand = new RemoveByTBOCommand();
        commands.put(RemoveByTBOCommand.getName(), RemoveByTBOCommand);

        Command FilterLessThanGPCCommand = new FilterLessThanGPCCommand();
        commands.put(FilterLessThanGPCCommand.getName(), FilterLessThanGPCCommand);

        Command CountGreaterThanMPAACommand = new CountGreaterThanMPAACommand();
        commands.put(CountGreaterThanMPAACommand.getName(), CountGreaterThanMPAACommand);

        Command RemoveLowerCommand = new RemoveLowerCommand();
        commands.put(RemoveLowerCommand.getName(), RemoveLowerCommand);

        Command InfoCommand = new InfoCommand();
        commands.put(InfoCommand.getName(), InfoCommand);

        Commands.setCommands(commands);

    }

}
