package bundle.factories;


public abstract class AbstractFactory<T> {

    public abstract T retrieve(String messageKey);

    public abstract void register(String messageKey, T t);

}
