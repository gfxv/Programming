package core.validators;

import core.exceptions.InvalidInputException;
import core.exceptions.InvalidTypeException;

public class OscarValidator {

    public static void validate(String oscar) throws InvalidInputException {

        // oscarCount validation
        try {
            int oscarCount = Integer.parseInt(oscar);
            if (oscarCount <= 0) {
                throw new InvalidInputException("Amount of oscars can't be < 0!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("Amount of oscars have to be Integer");
        }

    }
}
