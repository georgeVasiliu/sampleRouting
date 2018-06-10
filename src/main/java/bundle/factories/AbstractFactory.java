package bundle.factories;


public abstract class AbstractFactory<T> {

    AbstractFactory() {
    }

    public abstract T fetch(String messageKey);

    public abstract void addFor(String messageKey, T t);

}
