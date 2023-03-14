package core.validators;

import core.enteties.MpaaRating;
import core.exceptions.InvalidInputException;

public class MpaaValidator {

    /**
     * MPAA validator
     * @param input_mpaa
     * @throws InvalidInputException
     */
    public static void validate(String input_mpaa) throws InvalidInputException {

        if (input_mpaa.strip().length() == 0) {
            return;
        }

        for (MpaaRating mpaa : MpaaRating.values()) {
            if (mpaa.name().equals(input_mpaa)) {
                return;
            }
        }
        throw new InvalidInputException("Invalid Mpaa Rating!");


    }
}
