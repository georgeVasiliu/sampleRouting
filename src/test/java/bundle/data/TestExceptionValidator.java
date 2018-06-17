package bundle.data;

import bundle.exceptions.ValidationException;
import bundle.message.MessageValidator;

public class TestExceptionValidator implements MessageValidator {
    @Override
    public boolean validateArguments(Object... args) throws ValidationException {
        if (args.length == 0) {
            throw new ValidationException("Expected 2 arguments, got 0!");
        }
        return true;
    }
}
