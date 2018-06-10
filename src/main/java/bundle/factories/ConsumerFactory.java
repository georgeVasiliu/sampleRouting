package bundle.factories;

import bundle.message.MessageConsumer;

import java.util.HashMap;
import java.util.Map;

class ConsumerFactory<T extends Class<? extends MessageConsumer>> extends AbstractFactory<T> {

    private Map<String, T> mapper;

    ConsumerFactory() {
        super();
        mapper = new HashMap<>();
    }


    @Override
    public T fetch(String messageKey) {
        return mapper.getOrDefault(messageKey, null);
    }

    @Override
    public void addFor(String messageKey, T t) {
        mapper.put(messageKey, t);
    }


}
