package holl;

import bundle.message.Message;
import bundle.message.MessageConsumer;

public class MessageAsdf implements MessageConsumer {


    @Override
    public void consume(Message message) {
        message.setResult(true);
    }
}
