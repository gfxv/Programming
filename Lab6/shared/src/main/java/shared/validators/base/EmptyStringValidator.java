package shared.validators.base;

public class EmptyStringValidator {

    /**
     * Checks if string is empty or not
     * @param str
     * @return true if string is empty, else - false
     */
    public static boolean validate(String str) {
        str = str.strip();
        return str.length() == 0;
    }

}
