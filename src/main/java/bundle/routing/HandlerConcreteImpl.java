package bundle.routing;

import bundle.message.Message;

import java.util.concurrent.CompletableFuture;

public class HandlerConcreteImpl extends HandlerAbstractImpl {

    private static final Handler instance = new HandlerConcreteImpl();
    private MessageRouter messageRouter = MessageRouterConcreteImpl.getInstance();

    private HandlerConcreteImpl() {

    }

    public static Handler getInstance() {
        return instance;
    }

    @Override
    protected <T> CompletableFuture<T> processAsync(Message<T> message) throws Exception {
        return messageRouter.routeMessageAsync(message);

    }

    @Override
    protected <T> T process(Message<T> message) throws Exception {
        return messageRouter.routeMessage(message);
    }
}
