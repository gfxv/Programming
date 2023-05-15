package core.validators;


import core.exceptions.InvalidInputException;
import core.exceptions.InvalidTypeException;
import core.exceptions.ParamIsNullException;

public class CoordsValidator {

    /**
     * Coords validator
     * @param X
     * @param Y
     * @throws InvalidInputException
     */
    public static void validate(String X, String Y) throws InvalidInputException {

        if (X.strip().length() == 0 || Y.strip().length() == 0) {
            throw new ParamIsNullException("X and Y can't be NULL");
        }

        try {
            int int_x = Integer.parseInt(X);
            if (int_x < -327) {
                throw new InvalidInputException("X have to > -375");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("X is not Integer!");
        }

        try {
            long long_y = Long.parseLong(Y);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("Y is not Long!");
        }
    }

}
