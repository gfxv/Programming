package core.commands.commands;

import core.commands.base.Command;
import core.receivers.SystemCommandReceiver;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;


public class ExitCommand implements Command {

    private String name = "exit";
    private String desc = "exit : завершить программу (без сохранения в файл)";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
    private SystemCommandReceiver receiver;

    public ExitCommand(SystemCommandReceiver receiver) {
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
     * Terminates the program (without saving to a file)
     */
    @Override
    public ResponseBody execute(ServerRequest req) {
        return this.receiver.exit();
    }
}
