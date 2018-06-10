package holl;

import bundle.routing.Handler;
import bundle.routing.HandlerConcreteImpl;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {


        Handler handler = HandlerConcreteImpl.getInstance();
        ExpressionChecker checker = new ExpressionChecker();

        String result = "";

        try {
            result = handler.run("validate-expression", checker, ExpressionConsumer.class, "CF_32", "it", "3", "ST", "5");
            CompletableFuture<String> rAsync = handler.runAsync("validate-expression", "CF_224", "first", "1", "second", "2");
            result = handler.run("message");
            System.out.println("Result :" + result);
            System.out.println(rAsync.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
