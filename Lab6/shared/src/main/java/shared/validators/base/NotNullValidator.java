package shared.validators.base;

import java.util.Objects;

public class NotNullValidator {

    /**
     * Checks if object is NULL
     * @param obj
     * @return true if object is not null, else - false
     */
    public static boolean validate(Object obj) {
        return !(Objects.isNull(obj));
    }
}
