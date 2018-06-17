package bundle.routing;


import bundle.exceptions.RoutingException;
import bundle.exceptions.ValidationException;
import bundle.factories.AbstractFactory;
import bundle.factories.FactoryProducer;
import bundle.factories.FactoryType;
import bundle.message.Message;
import bundle.message.MessageConsumer;
import bundle.message.MessageValidator;

import java.util.concurrent.CompletableFuture;

import static bundle.factories.FactoryType.MESSAGE;
import static bundle.factories.FactoryType.VALIDATION;

public abstract class HandlerAbstractImpl implements Handler {
    private AbstractFactory<Class<? extends MessageConsumer>> consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
    private AbstractFactory<MessageValidator> validationFactory = FactoryProducer.getFactory(VALIDATION);
    private AbstractFactory<Message> messageFactory = FactoryProducer.getFactory(MESSAGE);

    @Override
    public <T, K extends MessageConsumer, M extends MessageValidator> CompletableFuture<T> runAsync(String message, M messageValidator, Class<K> kClass, Object... args) throws Exception {
        if (!messageValidator.validateArguments(args)) {
            throw new ValidationException("Arguments failed validation for message: " + message + "!");
        }
        Message<T> message1 = messageFactory.retrieve(message);
        validationFactory.register(message, messageValidator);
        consumerFactory.register(message, kClass);
        message1.setTarget(kClass);
        message1.setArgs(args);
        return processAsync(message1);
    }

    @Override
    public <T, K extends MessageConsumer> CompletableFuture<T> runAsync(String message, Class<K> nClass, Object... args) throws Exception {
        MessageValidator validator = validationFactory.retrieve(message);
        if (validator != null && !validator.validateArguments(args)) {
            throw new ValidationException(("Arguments failed validation for message: " + message + "!"));
        }
        Message<T> message1 = messageFactory.retrieve(message);
        consumerFactory.register(message, nClass);
        message1.setTarget(nClass);
        message1.setArgs(args);
        return processAsync(message1);
    }

    @Override
    public <T, M extends MessageValidator> CompletableFuture<T> runAsync(String message, M validator, Object... args) throws Exception {
        Message<T> message1 = messageFactory.retrieve(message);
        if (!validator.validateArguments(args)) {
            throw new ValidationException("Arguments failed validation for message: " + message + "!");
        }
        validationFactory.register(message, validator);
        Class<? extends MessageConsumer> nClass = consumerFactory.retrieve(message);
        if (nClass == null) {
            throw new RoutingException("No target class found for message: " + message + "! Please first provide a target class for the message then use this method!");
        }
        message1.setTarget(nClass);
        message1.setArgs(args);
        return processAsync(message1);
    }

    @Override
    public <T> CompletableFuture<T> runAsync(String message, Object... args) throws Exception {
        MessageValidator validator = validationFactory.retrieve(message);
        if (validator != null && !validator.validateArguments(args)) {
            throw new ValidationException(("Arguments failed validation for message: " + message + "!"));
        }
        Class<? extends MessageConsumer> nClass = consumerFactory.retrieve(message);
        if (nClass == null) {
            throw new RoutingException("No target class found for message: " + message + "! Please first provide a target class for the message then use this method!");
        }
        Message<T> message1 = messageFactory.retrieve(message);
        message1.setTarget(nClass);
        message1.setArgs(args);
        return processAsync(message1);
    }

    @Override
    public <T, K extends MessageConsumer, M extends MessageValidator> T run(String message, M messageValidator, Class<K> nClass, Object... args) throws Exception {
        if (!messageValidator.validateArguments(args)) {
            throw new ValidationException("Arguments failed validation for message: " + message + "!");
        }
        Message<T> message1 = messageFactory.retrieve(message);
        validationFactory.register(message, messageValidator);
        consumerFactory.register(message, nClass);
        message1.setTarget(nClass);
        message1.setArgs(args);
        return process(message1);
    }

    @Override
    public <T, K extends MessageConsumer> T run(String message, Class<K> nClass, Object... args) throws Exception {
        MessageValidator validator = validationFactory.retrieve(message);
        if (validator != null && !validator.validateArguments(args)) {
            throw new ValidationException("Arguments failed validation for message: " + message + "!");
        }
        Message<T> message1 = messageFactory.retrieve(message);
        consumerFactory.register(message, nClass);
        message1.setTarget(nClass);
        message1.setArgs(args);
        return process(message1);
    }

    @Override
    public <T, M extends MessageValidator> T run(String message, M validator, Object... args) throws Exception {
        if (!validator.validateArguments(args)) {
            throw new ValidationException("Arguments failed validation for message: " + message + "!");
        }
        Class<? extends MessageConsumer> nClass = consumerFactory.retrieve(message);
        if (nClass == null) {
            throw new RoutingException("No target class found for message: " + message + "! Please first provide a target class for the message then use this method!");
        }
        Message<T> message1 = messageFactory.retrieve(message);
        message1.setTarget(nClass);
        message1.setArgs(args);
        return process(message1);
    }

    @Override
    public <T> T run(String message, Object... args) throws Exception {
        MessageValidator validator = validationFactory.retrieve(message);
        if (validator != null && !validator.validateArguments(args)) {
            throw new ValidationException(("Arguments failed validation for message: " + message + "!"));
        }
        Class<? extends MessageConsumer> nClass = consumerFactory.retrieve(message);
        if (nClass == null) {
            throw new RoutingException("No target class found for message: " + message + "! Please first provide a target class for the message then use this method!");
        }
        Message<T> message1 = messageFactory.retrieve(message);
        message1.setTarget(nClass);
        message1.setArgs(args);
        return process(message1);
    }

    protected abstract <T> CompletableFuture<T> processAsync(Message<T> message) throws Exception;

    protected abstract <T> T process(Message<T> message) throws Exception;
}