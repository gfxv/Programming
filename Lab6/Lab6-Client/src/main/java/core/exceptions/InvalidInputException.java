package core.exceptions;

public class InvalidInputException extends Exception{

    public InvalidInputException() {
        super("Invalid Input");
    }

    public InvalidInputException(String msg) {
        super(msg);
    }

}
