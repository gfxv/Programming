package shared.validators;

import shared.exceptions.InvalidInputException;
import shared.exceptions.InvalidTypeException;
import shared.exceptions.ParamIsNullException;
import shared.validators.base.EmptyStringValidator;
import shared.validators.base.NotNullValidator;

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
            throw new ParamIsNullException("Directors name can't be NULL or empty");
        }

        // height validation
        try {
            int h = Integer.parseInt(height);
            if (h <= 0) {
                throw new InvalidInputException("Invalid height!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("Height have to be Integer");
        }

    }
}
