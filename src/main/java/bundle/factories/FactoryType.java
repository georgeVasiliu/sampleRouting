package bundle.factories;


public enum FactoryType {
    CONSUMER {
        @Override
        public AbstractFactory getFactory() {
            return new ConsumerFactory();
        }
    },
    MESSAGE {
        @Override
        public AbstractFactory getFactory() {
            return new MessageFactory();
        }
    },
    VALIDATION {
        @Override
        public AbstractFactory getFactory() {
            return new ValidationFactory();
        }
    };

    abstract AbstractFactory getFactory();
}
