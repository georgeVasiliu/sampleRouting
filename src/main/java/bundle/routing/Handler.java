package bundle.routing;

import bundle.message.MessageConsumer;
import bundle.message.MessageValidator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple interface responsible with handling incoming message requests.
 * There are two types of running messages:
 * -> async, where the result is wrapped in a {@link CompletableFuture} so further operations can be performed on it;
 * -> normal, where the result is not wrapped and returned directly
 * <p>
 * Each method call has two variants:
 * 1) With a {@link MessageValidator} provided.
 * 2) Without a {@link MessageValidator} provided.
 * <p>
 * Note that the {@link ExecutorService} that handles the asynchronous requests is defined in the abstract implementation of this interface,
 * and may be modified as needed (maybe make it public so anyone can provide their own thread pool for the asynchronous computation?).
 * Current {@link ExecutorService} used is a {@link Executors#newWorkStealingPool()}.
 *
 * @author strickt
 * @version 1.0.0
 */
public interface Handler {
    /**
     * @param message          the unique String key associated to the message
     * @param messageValidator a validator that checks against the arguments provided in the message prior to handling the message
     * @param kClass           the class which is responsible with handling that message
     * @param args             the arguments supplied to the message
     * @return the result of the processing of the message wrapped in a completable future
     * @throws Exception multiple exception types
     */

    <T, K extends MessageConsumer, M extends MessageValidator> CompletableFuture<T> runAsync(String message, M messageValidator, Class<K> kClass, Object... args) throws Exception;

    /**
     * @param message the unique String key associated to the message
     * @param kClass  the class which is responsible with handling that message
     * @param args    the arguments supplied to the message
     * @return the result of the processing of the message wrapped in a completable future
     * @throws Exception multiple exception types
     */
    <T, K extends MessageConsumer> CompletableFuture<T> runAsync(String message, Class<K> kClass, Object... args) throws Exception;

    /**
     * @param message   the unique String key associated to the message
     * @param validator a validator that checks against the arguments provided in the message prior to handling the message
     * @param args      the arguments supplied to the message
     * @return the result of the processing of the message wrapped in a completable future
     * @throws Exception multiple exception types
     */
    <T, M extends MessageValidator> CompletableFuture<T> runAsync(String message, M validator, Object... args) throws Exception;

    /**
     * @param message the unique String key associated to the message
     * @param args    the arguments supplied to the message
     * @return the result of the processing of the message
     * @throws Exception multiple exception types
     */
    <T> CompletableFuture<T> runAsync(String message, Object... args) throws Exception;


    /**
     * @param message          the unique String key associated to the message
     * @param messageValidator a validator that checks against the arguments provided in the message prior to handling the message
     * @param kClass           the class which is responsible with handling that message
     * @param args             the arguments supplied to the message
     * @return a message that that contains the result wrapped in a completable future
     * @throws Exception multiple exception types
     */
    <T, K extends MessageConsumer, M extends MessageValidator> T run(String message, M messageValidator, Class<K> kClass, Object... args) throws Exception;

    /**
     * @param message the unique String key associated to the message
     * @param kClass  the class which is responsible with handling that message
     * @param args    the arguments supplied to the message
     * @return a message that that contains the result
     * @throws Exception multiple exception types
     */
    <T, K extends MessageConsumer> T run(String message, Class<K> kClass, Object... args) throws Exception;

    /**
     * @param message   the unique String key associated to the message
     * @param validator a validator that checks against the arguments provided in the message prior to handling the message
     * @param args      the arguments supplied to the message
     * @return the result of the processing of the message
     * @throws Exception multiple exception types
     */
    <T, M extends MessageValidator> T run(String message, M validator, Object... args) throws Exception;

    /**
     * @param message the unique String key associated to the message
     * @param args    the arguments supplied to the message
     * @return the result of the processing of the message
     * @throws Exception multiple exception types
     */
    <T> T run(String message, Object... args) throws Exception;

}
