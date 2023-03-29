package core.commands.commands;

import core.commands.base.Command;
import core.receivers.CollectionManipulationReceiver;


public class ShowCommand implements Command {

    private String name = "show";
    private String desc = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    private CollectionManipulationReceiver receiver;

    public ShowCommand(CollectionManipulationReceiver receiver) {
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
     * Prints all elements from the Collection
     */
    @Override
    public void execute(String args) {
        this.receiver.show();
    }
}
