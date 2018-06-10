package bundle.routing;


import bundle.message.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * * The purpose of this interface is to route incoming to their target class.
 * * This means this class must be responsible with instantiating new instances of the target class so that they handle the message.
 * * This means we have complete control over the target classes instantiation behaviour:
 * * - we can make them singletons;
 * * - we can make produce them in an object pool and reuse them;
 * * - we can hold them in the memory using a map;
 * * - we can do w/e we want with them;
 * * <p>
 * * As such, this class might be the most difficult to design and how it is designed might impact performance in various ways.
 * * For now, the concrete implementation is based on a map which holds instances of the message consumer target class - meaning all instances that must process messages
 * * are simple singleton instances held in a map.
 * *
 * * @author strict
 * * @version 1.0.0
 */
interface MessageRouter {
    /**
     * @param message the message to be processed
     * @return the result of processing the message
     * @throws ReflectiveOperationException if any kind of reflection operation occurs
     */
    <T> T routeMessage(Message<T> message) throws Exception;

    <T> CompletableFuture<T> routeMessageAsync(Message<T> message) throws Exception;

    void setExecutorService(ExecutorService executorService);
}
