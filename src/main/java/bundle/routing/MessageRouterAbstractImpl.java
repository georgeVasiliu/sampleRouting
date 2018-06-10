package bundle.routing;


import bundle.message.Message;
import bundle.message.MessageConsumer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class MessageRouterAbstractImpl implements MessageRouter {

    private ExecutorService executorService = Executors.newWorkStealingPool();

    @Override
    public <T> T routeMessage(Message<T> message) throws Exception {
        MessageConsumer<T> targetClass = requestTargetInstance(message.getTarget());
        targetClass.consume(message);
        return message.getResult();
    }


    @Override
    public <T> CompletableFuture<T> routeMessageAsync(Message<T> message) throws Exception {
        MessageConsumer<T> targetClass = requestTargetInstance(message.getTarget());
        CompletableFuture<T> toCompletableFuture = new CompletableFuture<>();
        executorService.submit(() -> {
            try {
                targetClass.consume(message);
            } catch (Exception e) {
                toCompletableFuture.completeExceptionally(e);
            }
            toCompletableFuture.complete(message.getResult());
        });
        return toCompletableFuture;
    }

    @Override
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    protected abstract <T> MessageConsumer<T> requestTargetInstance(Class<? extends MessageConsumer<T>> targetClass) throws ReflectiveOperationException;

}
