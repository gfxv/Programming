package core.commands.commands;

import core.commands.base.Command;
import core.receivers.SystemCommandReceiver;


public class ExitCommand implements Command {
    /**
     * Command name
     */
    private String name = "exit";
    /**
     * Command description
     */
    private String desc = "exit : завершить программу (без сохранения в файл)";

    /**
     * Getter for name field
     * @return command name
     */

    private SystemCommandReceiver receiver;

    public ExitCommand(SystemCommandReceiver receiver) {
        this.receiver = receiver;
    }

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
     * Terminates the program (without saving to a file)
     */
    @Override
    public void execute(String args) {
        this.receiver.exit();
    }
}
