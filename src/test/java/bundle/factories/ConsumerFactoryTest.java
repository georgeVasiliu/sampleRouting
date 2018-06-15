package bundle.factories;

import bundle.data.TestConsumer;
import bundle.exceptions.ConsumerException;
import bundle.message.Message;
import bundle.message.MessageConsumer;
import bundle.message.MessageImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumerFactoryTest {

    @Test
    void testConsumerFactory() {
        AbstractFactory<Class<? extends MessageConsumer>> consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
        Class<? extends MessageConsumer> consumer = consumerFactory.retrieve("expectedNull");
        assertNull(consumer);
        consumerFactory.register("expectedMessage", TestConsumer.class);
        assertNotNull(consumerFactory.retrieve("expectedMessage"));
        assertSame(TestConsumer.class, consumerFactory.retrieve("expectedMessage"));
    }

    @Test
    void testMessageResultSetCorrectly() throws Exception {
        AbstractFactory<Class<? extends MessageConsumer>> consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
        consumerFactory.register("expectedMessage", TestConsumer.class);
        Message<Boolean> message = new MessageImpl<>();
        consumerFactory.retrieve("expectedMessage").newInstance().consume(message);
        assertTrue(message.getResult());
    }

    @Test
    void testExceptionThrown() throws Exception {
        AbstractFactory<Class<? extends MessageConsumer>> consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
        consumerFactory.register("expectedMessage", TestConsumer.class);
        Message<Boolean> message = new MessageImpl<>();
        message.setArgs("hello", 2);
        try {
            consumerFactory.retrieve("expectedMessage").newInstance().consume(message);
        } catch (ConsumerException e) {
            assertTrue(e.getMessage().contains("expected"));
        }
    }

    @Test
    void testExceptionBypass() throws Exception {
        AbstractFactory<Class<? extends MessageConsumer>> consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
        consumerFactory.register("expectedMessage", TestConsumer.class);
        Message<Boolean> message = new MessageImpl<>();
        message.setArgs(2, 2);
        consumerFactory.retrieve("expectedMessage").newInstance().consume(message);
        assertFalse(message.getResult());
    }
}
