package main;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReverseString {

    public static void main(String[] args) {
        String message = "Hello World for you";

        reverseString(Arrays.asList(message.split(" ")),
                x -> new StringBuffer(x).reverse().toString(),
                System.out::print);

    }

    private static void reverseString(List<String> asList,
                                      Function<String, String> rule,
                                      Consumer<String> c) {
        for (String word : asList)
            c.accept(rule.apply(word + " "));
    }
}