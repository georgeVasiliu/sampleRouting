package bundle.factories;

import bundle.data.TestComplexValidator;
import bundle.data.TestExceptionValidator;
import bundle.data.TestValidator;
import bundle.exceptions.ValidationException;
import bundle.message.MessageValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationFactoryTest {

    @Test
    void testValidationFactory() throws Exception {
        AbstractFactory<MessageValidator> validationFactory = FactoryProducer.getFactory(FactoryType.VALIDATION);
        MessageValidator validator = validationFactory.retrieve("expectedNull");
        assertNull(validator);
        validationFactory.register("expectedMessage", new TestValidator());
        MessageValidator validValidator = validationFactory.retrieve("expectedMessage");
        assertNotNull(validValidator);
        assertTrue(validValidator.validateArguments(null));
    }

    @Test
    void testValidationException() throws Exception {
        AbstractFactory<MessageValidator> validationFactory = FactoryProducer.getFactory(FactoryType.VALIDATION);
        validationFactory.register("expectedMessage", new TestExceptionValidator());
        MessageValidator validator = validationFactory.retrieve("expectedMessage");
        try {
            validator.validateArguments(new Object[]{});
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Expected 2 arguments, got 0"));
        }
    }

    @Test
    void testValidationScenarios() throws Exception {
        AbstractFactory<MessageValidator> validationFactory = FactoryProducer.getFactory(FactoryType.VALIDATION);
        validationFactory.register("expectedMessage", new TestComplexValidator());
        MessageValidator validator = validationFactory.retrieve("expectedMessage");
        try {
            validator.validateArguments(new Object[]{});
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Expected 2 arguments, got 0"));
        }
        assertFalse(validator.validateArguments(1, "Alabala"));
        assertTrue(validator.validateArguments(12, "expectedstring"));
    }
}
