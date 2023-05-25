package shared.validators;

import shared.exceptions.InvalidInputException;
import shared.exceptions.InvalidNameException;
import shared.validators.base.EmptyStringValidator;
import shared.validators.base.NotNullValidator;

public class MovieNameValidator {

    /**
     * Movie name validator
     * @param name
     * @throws InvalidInputException
     */
    public static void validate(String name) throws InvalidInputException {
        if (!(NotNullValidator.validate(name)) || EmptyStringValidator.validate(name)) {
            throw new InvalidNameException("Invalid name!");
        }

    }

}
