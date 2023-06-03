package core.commands.commands;

import core.commands.base.Command;
import core.receivers.SystemCommandReceiver;
import shared.serializables.ResponseBody;
import shared.serializables.ServerRequest;


public class HelpCommand implements Command {

    private String name = "help";
    private String desc = "help : вывести справку по доступным командам";
    private boolean primitiveArg = false;
    private boolean complexArg = false;
    private SystemCommandReceiver receiver;

    public HelpCommand(SystemCommandReceiver receiver) {
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
     * Shows info about all available commands
     */
    @Override
    public ResponseBody execute(ServerRequest req) {
        return this.receiver.help();
    }
}
