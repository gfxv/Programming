package core.commands.commands;


import core.commands.base.Command;
import core.exceptions.InvalidInputException;
import core.receivers.ElementManipulationReceiver;



public class AddCommand implements Command {

    private String name = "add";
    private String desc = "add {element} : добавить новый элемент в коллекцию";
    private ElementManipulationReceiver receiver;

    public AddCommand(ElementManipulationReceiver receiver) {
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
     * Adds new element to the Storage
     * @throws InvalidInputException
     */
    @Override
    public void execute(String args) throws InvalidInputException {
        this.receiver.add();
    }
}
