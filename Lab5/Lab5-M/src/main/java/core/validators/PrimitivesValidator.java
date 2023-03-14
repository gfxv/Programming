package core.validators;


import core.exceptions.InvalidInputException;

public class PrimitivesValidator {

    /**
     * Validator for oscarCounter, goldenPalmCounter and totalBoxOffice
     * @param primitives
     * @return
     * @throws InvalidInputException
     */
    public static void validate(String[] primitives) throws InvalidInputException {

        if (primitives.length != 3) {
            throw new InvalidInputException("You missed some params");
        }

        // oscarCount validation
        try {
            int oscarCount = Integer.parseInt(primitives[0]);
            if (oscarCount <= 0) {
                throw new InvalidInputException("Invalid amount of oscars!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Amount of oscars have to be Integer");
        }

        // goldenPalmCount validator
        try {
            long goldenPalmCount = Long.parseLong(primitives[1]);
            if (goldenPalmCount <= 0) {
                throw new InvalidInputException("Invalid amount of golden palms!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Amount of golden palms have to be Integer");
        }

        // totalBoxOffice validation
        try {
            float totalBoxOffice = Float.parseFloat(primitives[2]);
            if (totalBoxOffice <= 0) {
                throw new InvalidInputException("Invalid total box office!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Total box office have to be Integer(Float)");
        }
    }
}
