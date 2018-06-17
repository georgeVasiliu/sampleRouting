package bundle.data;

import bundle.exceptions.ConsumerException;
import bundle.message.Message;
import bundle.message.MessageConsumer;

public class TestConsumer implements MessageConsumer<Boolean> {
    @Override
    public void consume(Message<Boolean> message) throws ConsumerException {
        message.setResult(true);
    }
}
