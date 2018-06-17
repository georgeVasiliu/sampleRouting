package bundle.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FactoryProducerTest {

    @Test
    void testGetValidationFactory() {
        AbstractFactory validationFactory = FactoryProducer.getFactory(FactoryType.VALIDATION);
        assertNotNull(validationFactory);
    }

    @Test
    void testGetConsumerFactory() {
        AbstractFactory consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
        assertNotNull(consumerFactory);
    }

    @Test
    void testGetMessageFactory() {
        AbstractFactory messageFactory = FactoryProducer.getFactory(FactoryType.MESSAGE);
        assertNotNull(messageFactory);
    }

    @Test
    void testRandomExpectedNullFetch() {
        AbstractFactory consumerFactory = FactoryProducer.getFactory(FactoryType.CONSUMER);
        assertNull(consumerFactory.retrieve("shouldReturnNull"));
    }


}