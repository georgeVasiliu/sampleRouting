package holl;

import bundle.exceptions.ConsumerException;
import bundle.message.Message;
import bundle.message.MessageConsumer;

public class ExpressionConsumer implements MessageConsumer {

    @Override
    public void consume(Message message) throws ConsumerException {
        System.out.print("Hello, this is");


        String[] elements = new String[message.getArgs().length];
        Object args[] = message.getArgs();
        for (int i = 0; i < elements.length; i++) {
            elements[i] = (String) args[i];

        }
        System.out.println(args[0]);

        try {
            for (int i = 1; i < args.length; i += 2) {
                if (i % 2 == 1) {
                    System.out.println("I am " + args[i] + " the " + args[i + 1]);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        message.setResult("Presentation done successfully!");
    }
}
