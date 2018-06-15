package bundle.data;

import bundle.exceptions.ConsumerException;
import bundle.message.Message;
import bundle.message.MessageConsumer;

public class TestExceptionConsumer implements MessageConsumer<Boolean> {
    @Override
    public void consume(Message<Boolean> message) throws ConsumerException {
        if (message.getArgs()[0] instanceof String) {
            throw new ConsumerException("Expected int, got String!");
        }
        message.setResult(false);
    }
}
