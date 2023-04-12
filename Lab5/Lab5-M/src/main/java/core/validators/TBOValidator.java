package core.validators;

import core.exceptions.InvalidInputException;
import core.exceptions.InvalidTypeException;

public class TBOValidator {
    public static void validate(String tbo) throws InvalidInputException {
        // totalBoxOffice validation
        try {
            float totalBoxOffice = Float.parseFloat(tbo);
            if (totalBoxOffice <= 0) {
                throw new InvalidInputException("Total box office can't be < 0!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("Total box office have to be Integer or Float)");
        }
    }
}
