package bundle.factories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FactoryProducerTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getFactory() {
        ValidationFactory validationFactory = (ValidationFactory) FactoryProducer.getFactory(FACTORY_TYPE.VALIDATION);
        ConsumerFactory consumerFactory = (ConsumerFactory) FactoryProducer.getFactory(FACTORY_TYPE.CONSUMER);
        MessageFactory messageFactory = (MessageFactory) FactoryProducer.getFactory(FACTORY_TYPE.MESSAGE);


        assertNotNull(validationFactory);
        assertNotNull(consumerFactory);
        assertNotNull(messageFactory);
    }
}