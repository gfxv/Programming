package core.exceptions;

public class UniqueElementException extends Exception{
    public UniqueElementException() {
        super("Element have to be unique!");
    }

    public UniqueElementException(String msg) {
        super(msg);
    }
}
