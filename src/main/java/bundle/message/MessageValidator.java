package bundle.message;

import bundle.exceptions.ValidationException;

/**
 * Simple interface that validates the arguments provided to a message.
 *
 * @author strickt
 * @version 1.0.0
 */
public interface MessageValidator {
    /**
     * @param args the arguments to be validated
     * @return if the arguments are valid or not
     */
    boolean validateArguments(Object... args) throws ValidationException;
}
