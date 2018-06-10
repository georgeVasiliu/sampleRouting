package holl;

import bundle.exceptions.ValidationException;
import bundle.message.MessageValidator;

public class ExpressionChecker implements MessageValidator {
    @Override
    public boolean validateArguments(Object... args) throws ValidationException {

        String[] elements = new String[args.length];

        for (int i = 0; i < args.length; i++) {
            if (args[i].getClass().getSimpleName().equals("String"))
                elements[i] = args[i].toString();
            else
                throw new ValidationException("All elements must be strings!");
        }

        if (elements.length < 3)
            throw new ValidationException("Not enough elements!");

        if (elements.length % 2 == 0)
            throw new ValidationException("Expression needs to have odd number of elements");

        if (!elements[0].matches("[A-Z]*_[0-9]*")) {
            throw new ValidationException("Invalid element format.");
        }

        for (int i = 1; i < elements.length; i++) {
            if (i % 2 == 0) {
                try {
                    Integer.parseInt(elements[i]);
                } catch (NumberFormatException e) {
                    throw new ValidationException("Invalid element format. expected number on " + i + "'th element");
                }
            } else {
                if (elements[i].matches(".*\\d+.*")) {
                    throw new ValidationException("Invalid element format. Expected only alpha characters on " + i + "'th element");
                }
            }
        }
        System.out.println("Validated!");
        return true;
    }
}
