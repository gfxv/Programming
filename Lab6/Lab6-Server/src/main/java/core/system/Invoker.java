package core.system;


import core.commands.base.Command;
import core.commands.commands.*;
import core.receivers.CollectionManipulationReceiver;
import core.receivers.ElementManipulationReceiver;
import core.receivers.SystemCommandReceiver;

import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private static Map<String, Command> commands = new HashMap<>();

    public static void init() {

        SystemCommandReceiver systemCommandReceiver = new SystemCommandReceiver();
        CollectionManipulationReceiver collectionManipulationReceiver = new CollectionManipulationReceiver();
        ElementManipulationReceiver elementManipulationReceiver = new ElementManipulationReceiver();

        Command AddCommand = new AddCommand(elementManipulationReceiver);
        commands.put(AddCommand.getName(), AddCommand);

        Command HelpCommand = new HelpCommand(systemCommandReceiver);
        commands.put(HelpCommand.getName(), HelpCommand);

        Command ShowCommand = new ShowCommand(collectionManipulationReceiver);
        commands.put(ShowCommand.getName(), ShowCommand);

        Command ClearCommand = new ClearCommand(collectionManipulationReceiver);
        commands.put(ClearCommand.getName(), ClearCommand);

        Command ExitCommand = new ExitCommand(systemCommandReceiver);
        commands.put(ExitCommand.getName(), ExitCommand);

        Command RemoveByIdCommand = new RemoveByIdCommand(elementManipulationReceiver);
        commands.put(RemoveByIdCommand.getName(), RemoveByIdCommand);

        Command UpdateByIdCommand = new UpdateByIdCommand(elementManipulationReceiver);
        commands.put(UpdateByIdCommand.getName(), UpdateByIdCommand);

        Command SaveCommand = new SaveCommand(collectionManipulationReceiver);
        commands.put(SaveCommand.getName(), SaveCommand);

        Command HistoryCommand = new HistoryCommand(systemCommandReceiver);
        commands.put(HistoryCommand.getName(), HistoryCommand);

        Command ExecuteScriptCommand = new ExecuteScriptCommand(systemCommandReceiver);
        commands.put(ExecuteScriptCommand.getName(), ExecuteScriptCommand);

        Command AddIfMinCommand = new AddIfMinCommand(elementManipulationReceiver);
        commands.put(AddIfMinCommand.getName(), AddIfMinCommand);

        Command RemoveByTBOCommand = new RemoveByTBOCommand(elementManipulationReceiver);
        commands.put(RemoveByTBOCommand.getName(), RemoveByTBOCommand);

        Command FilterLessThanGPCCommand = new FilterLessThanGPCCommand(elementManipulationReceiver);
        commands.put(FilterLessThanGPCCommand.getName(), FilterLessThanGPCCommand);

        Command CountGreaterThanMPAACommand = new CountGreaterThanMPAACommand(elementManipulationReceiver);
        commands.put(CountGreaterThanMPAACommand.getName(), CountGreaterThanMPAACommand);

        Command RemoveLowerCommand = new RemoveLowerCommand(elementManipulationReceiver);
        commands.put(RemoveLowerCommand.getName(), RemoveLowerCommand);

        Command InfoCommand = new InfoCommand(systemCommandReceiver);
        commands.put(InfoCommand.getName(), InfoCommand);

    }

    public static Map<String, Command> getCommands() {
        return commands;
    }


}
