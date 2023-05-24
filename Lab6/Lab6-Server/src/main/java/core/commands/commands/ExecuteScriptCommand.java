package core.commands.commands;

import core.commands.base.Command;
import shared.exceptions.InvalidInputException;
import core.receivers.SystemCommandReceiver;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;


public class ExecuteScriptCommand implements Command {

    private String name = "execute_script";
    private String desc = "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    private boolean primitiveArg = true;
    private boolean complexArg = false;
    private SystemCommandReceiver receiver;

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

    public boolean isPrimitiveArg() {
        return primitiveArg;
    }
    public boolean isComplexArg() {
        return complexArg;
    }

    /**
     * Reads and executes script from given file
     * @throws InvalidInputException
     */
    @Override
    public ResponseBody execute(ServerRequest req) throws InvalidInputException {
        return this.receiver.executeScript("args");
    }
}
