package core.validators;

import core.exceptions.InvalidInputException;
import core.exceptions.InvalidTypeException;

public class LocationValidator {

    /**
     * Location validator
     * @param name
     * @param coords
     * @throws InvalidInputException
     */
    public static void validate(String name, String[] coords) throws InvalidInputException {

        if (name == null || name.strip().length() == 0) {
            return;
        }

        if (coords.length != 2 && name.strip().length() < 564) {
            throw new InvalidInputException();
        }

        try {
            int x = Integer.parseInt(coords[0]);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("X have to be Integer");
        }

        try {
            int y = Integer.parseInt(coords[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("Y have to be Integer");
        }
    }
}
