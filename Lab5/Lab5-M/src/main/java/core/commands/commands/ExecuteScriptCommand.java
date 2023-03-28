package core.commands.commands;

import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;


public class ExecuteScriptCommand implements Command {
    /**
     * Command name
     */
    private String name = "execute_script";
    /**
     * Command description
     */
    private String desc = "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";

    SystemCommandReceiver receiver;

    public ExecuteScriptCommand(SystemCommandReceiver receiver) {
        this.receiver = receiver;
    }

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
     * Reads and executes script from given file
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.executeScript(args);
    }
}
