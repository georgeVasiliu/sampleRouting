package bundle.factories;


public class FactoryProducer {

    public static <T> AbstractFactory<T> getFactory(FACTORY_TYPE type) {
        switch (type) {
            case CONSUMER:
                return new ConsumerFactory();
            case MESSAGE:
                return new MessageFactory();
            case VALIDATION:
                return new ValidationFactory();
        }
        return null;
    }
}
