package bundle.routing;

import bundle.message.MessageConsumer;

import java.util.WeakHashMap;

class MessageRouterConcreteImpl extends MessageRouterAbstractImpl {

    private static final MessageRouter instance = new MessageRouterConcreteImpl();
    private WeakHashMap<Class<? extends MessageConsumer>, MessageConsumer> instanceMap = new WeakHashMap<>();

    private MessageRouterConcreteImpl() {

    }

    static MessageRouter getInstance() {
        return instance;
    }

    @Override
    protected <T> MessageConsumer<T> requestTargetInstance(Class<? extends MessageConsumer<T>> targetClass) throws ReflectiveOperationException {
        if (instanceMap.containsKey(targetClass)) {
            return instanceMap.get(targetClass);
        }
        instanceMap.put(targetClass, targetClass.getDeclaredConstructor().newInstance());
        return instanceMap.get(targetClass);
    }

}
