package core.commands.commands;

import core.commands.base.Command;
import core.receivers.SystemCommandReceiver;


public class ExitCommand implements Command {

    private String name = "exit";
    private String desc = "exit : завершить программу (без сохранения в файл)";
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

    /**
     * Terminates the program (without saving to a file)
     */
    @Override
    public void execute(String args) {
        this.receiver.exit();
    }
}
