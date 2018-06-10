package bundle.message;


import bundle.exceptions.ConsumerException;

/**
 * Simple interface to be extended by the classes that will actually receive messages and return the result of processing the message.
 *
 * @param <T> the result type of the message
 * @author strickt
 * @version 1.0.0
 */
public interface MessageConsumer<T> {
    /**
     * 'Consumes' the message in the sense of processing it and completing it's result.
     *
     * @param message the message to be processed.
     */
    void consume(Message<T> message) throws ConsumerException;
}
