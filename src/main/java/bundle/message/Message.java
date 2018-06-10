package bundle.message;

import javafx.util.Callback;

import java.util.List;

/**
 * Simple interface to act as a container.
 *
 * @param <T> the expected result type
 * @author strickt
 * @version 1.0.0
 */
public interface Message<T> {

    List<Callback> getCallbackList();

    void registerCallback(Callback callback);

    /**
     * @return a the result of processing the message.
     */
    T getResult();

    /**
     * @param t the result of processing the message.
     */
    void setResult(T t);

    /**
     * @return the arguments provided in the message.
     */
    Object[] getArgs();

    /**
     * @param args the arguments provided in the message.
     */
    void setArgs(Object... args);

    /**
     * Returns the target class that must handle the message.
     *
     * @return the class
     */
    Class getTarget();

    /**
     * Sets the target class for handling a message.
     *
     * @param k   the class
     * @param <K> generified type
     */
    <K extends MessageConsumer> void setTarget(Class<K> k);
}
