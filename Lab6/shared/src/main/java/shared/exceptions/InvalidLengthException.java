package shared.exceptions;

public class InvalidLengthException extends InvalidInputException {

    public InvalidLengthException() {
        super("Invalid length!");
    }

    public InvalidLengthException(String msg) {
        super(msg);
    }
}
