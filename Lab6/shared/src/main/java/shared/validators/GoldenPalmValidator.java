package shared.validators;

import shared.exceptions.InvalidInputException;
import shared.exceptions.InvalidTypeException;

public class GoldenPalmValidator {
    public static void validate(String goldenPalm) throws InvalidInputException {

        // goldenPalmCount validator
        try {
            long goldenPalmCount = Long.parseLong(goldenPalm);
            if (goldenPalmCount <= 0) {
                throw new InvalidInputException("Amount of golden palms can't be < 0!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("Amount of golden palms have to be Integer");
        }

    }
}
