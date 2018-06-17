package bundle.factories;


public class FactoryProducer {

    public static AbstractFactory getFactory(FactoryType factoryType) {
        return factoryType.getFactory();
    }
}
