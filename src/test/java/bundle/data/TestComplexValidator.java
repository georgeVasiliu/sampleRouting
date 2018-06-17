package bundle.data;

import bundle.exceptions.ValidationException;
import bundle.message.MessageValidator;

public class TestComplexValidator implements MessageValidator {
    @Override
    public boolean validateArguments(Object... args) throws ValidationException {
        if (args.length == 0) {
            throw new ValidationException("Expected 2 arguments, got 0!");
        }
        int x = (int) args[0];
        if (x < 10) {
            return false;
        }
        String expectedString = (String) args[1];
        return expectedString.equalsIgnoreCase("expectedstring");
    }
}
