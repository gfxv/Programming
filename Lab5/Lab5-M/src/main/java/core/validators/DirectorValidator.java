package core.validators;

import core.exceptions.InvalidInputException;
import core.validators.base.EmptyStringValidator;
import core.validators.base.NotNullValidator;

public class DirectorValidator {

    /**
     * Director validator
     * @param name
     * @param height
     * @throws InvalidInputException
     */
    public static void validate (String name, String height) throws InvalidInputException {


        // name validation
        if (!(NotNullValidator.validate(name)) || EmptyStringValidator.validate(name)) {
            throw new InvalidInputException("Directors name can't be NULL or empty");
        }

        // height validation
        try {
            int h = Integer.parseInt(height);
            if (h <= 0) {
                throw new InvalidInputException("Invalid height!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Height have to be Integer");
        }

    }
}
