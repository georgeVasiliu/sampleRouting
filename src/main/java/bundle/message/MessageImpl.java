package bundle.message;


import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class MessageImpl<T> implements Message<T> {

    private List<Callback> callbackList;
    private Object[] args;
    private Class targetClass;
    private T t;


    public MessageImpl() {
        callbackList = new ArrayList<>();
    }

    @Override
    public List<Callback> getCallbackList() {
        return callbackList;
    }

    @Override
    public void registerCallback(Callback callback) {
        callbackList.add(callback);
    }

    @Override
    public T getResult() {
        return t;
    }

    @Override
    public void setResult(T t) {
        this.t = t;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    @Override
    public void setArgs(Object... args) {
        this.args = args;
    }

    @Override
    public Class getTarget() {
        return targetClass;
    }

    @Override
    public <K extends MessageConsumer> void setTarget(Class<K> k) {
        targetClass = k;
    }


}
