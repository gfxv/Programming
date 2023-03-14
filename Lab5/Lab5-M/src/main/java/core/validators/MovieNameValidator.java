package core.validators;

import core.exceptions.InvalidInputException;
import core.validators.base.EmptyStringValidator;
import core.validators.base.NotNullValidator;

public class MovieNameValidator {

    /**
     * Movie name validator
     * @param name
     * @throws InvalidInputException
     */
    public static void validate(String name) throws InvalidInputException {
        if (!(NotNullValidator.validate(name)) || EmptyStringValidator.validate(name)) {
            throw new InvalidInputException("Invalid name!");
        }

    }

}
