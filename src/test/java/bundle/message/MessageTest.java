package bundle.message;

import bundle.exceptions.ConsumerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getCallbackList() {
        Message message = new MessageImpl();
        assertEquals(message.getCallbackList().size(), 0);
    }

    @Test
    void registerCallback() {
        Message message = new MessageImpl();
        message.registerCallback(param -> null);
        assertEquals(message.getCallbackList().size(), 1);
    }

    @Test
    void getResult() {
        Message<Boolean> message = new MessageImpl<>();
        assertNull(message.getResult());
    }

    @Test
    void setResult() {
        Message<Boolean> message = new MessageImpl<>();
        message.setResult(false);
        assertFalse(message.getResult());
    }

    @Test
    void getArgs() {
        Message message = new MessageImpl();
        assertNull(message.getResult());
    }

    @Test
    void setArgs() {
        Message message = new MessageImpl();
        message.setArgs(new Object[]{0, 1});
        assertEquals(message.getArgs().length, 2);
    }

    @Test
    void getTarget() {
        Message message = new MessageImpl();
        assertNull(message.getTarget());
    }

    @Test
    void setTarget() {
        Message message = new MessageImpl();
        message.setTarget(MessageConsumerImpl.class);
        assertNotNull(message.getTarget());
    }


    class MessageConsumerImpl implements MessageConsumer {

        @Override
        public void consume(Message message) throws ConsumerException {
            System.out.println("Hello");
        }
    }
}