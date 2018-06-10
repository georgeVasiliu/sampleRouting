package bundle.factories;

import bundle.message.MessageValidator;

import java.util.HashMap;
import java.util.Map;

class ValidationFactory<T extends MessageValidator> extends AbstractFactory<T> {

    private Map<String, T> map;

    ValidationFactory() {
        super();
        map = new HashMap<>();
    }


    @Override
    public T fetch(String messageKey) {
        return map.getOrDefault(messageKey, null);
    }

    @Override
    public void addFor(String messageKey, T t) {
        map.put(messageKey, t);
    }
}
