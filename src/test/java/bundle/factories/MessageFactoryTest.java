package bundle.factories;

import bundle.message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageFactoryTest {

    @Test
    void testMessageFactory() {
        AbstractFactory<Message<Boolean>> messageFactory = FactoryProducer.getFactory(FactoryType.MESSAGE);
        Message<Boolean> message = messageFactory.retrieve("shouldReturnInstance");
        assertNotNull(message);
    }
}
