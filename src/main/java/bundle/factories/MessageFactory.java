package bundle.factories;

import bundle.message.Message;
import bundle.message.MessageImpl;

import java.util.ArrayList;
import java.util.List;

class MessageFactory<K> extends AbstractFactory<Message<K>> {
    /*
     We might want (eventually) to organize all messages according to their keys.
     Or maybe we want to have a pool of messages from which to retrieve
     Or maybe we want to have a source for all the messages, not just return a new MessageImpl();
     Any other data manipulation can be invoked here, this is why this class has been left empty for now.
     */
    private List<String> messageKeys;

    MessageFactory() {
        super();
        messageKeys = new ArrayList<>();
    }


    @Override
    public Message<K> fetch(String messageKey) {
        if (!messageKeys.contains(messageKey)) {
            messageKeys.add(messageKey);
        }
        return new MessageImpl<>();
    }

    @Override
    public void addFor(String messageKey, Message<K> kMessage) {
        //not needed since the fetch automatically updates the list
    }
}
