package bundle.data;

import bundle.exceptions.ValidationException;
import bundle.message.MessageValidator;

public class TestValidator implements MessageValidator {
    @Override
    public boolean validateArguments(Object... args) throws ValidationException {
        return true;
    }
}
