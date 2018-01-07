/**
 * Project: Muunads
 * Package: example
 * File: SimpleDriver.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 22, 2017 6:10:45 PM
 */
package example;

import java.util.Arrays;

import actions.io.IO;

/**
 * @author sidmishraw
 *
 *         Qualified Name: example.SimpleDriver
 *
 */
public class SimpleDriver {
    
    /**
     * Returns an IO action that prints the string onto STDOUT.
     * 
     * @param str
     *            the string to be printed to STDOUT.
     * @return the IO action.
     */
    public static IO<Void> putStrLn(String str) {
        return new IO<Void>(() -> {
            System.out.println(str);
            return null;
        });
    }
    
    /**
     * Performs the IO<Void> actions sequentially.
     * 
     * @param actions
     *            the IO<Void> actions to perform
     */
    @SafeVarargs
    public static void perform(IO<Void>... actions) {
        Arrays.asList(actions).forEach(act -> {
            act.unwrap();
        });
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Maybe<Integer> x = Empty.getInstance(0);
        Maybe<Integer> y = new Just<Integer>(3);
        Maybe<Integer> z = y.bind(a -> new Just<>(a + 3));
        
        System.out.println("x =  " + x.toString());
        System.out.println("y =  " + y.toString());
        System.out.println("z =  " + z.toString());
        System.out.println("x >= \\a -> Just a = " + x.bind(a -> new Just<>(a))); // empty
        
        if (y instanceof Just<?>) {
            System.out.println("Y is concrete with value = " + ((Just<Integer>) y).getA());
        } else if (y instanceof Empty<?>) {
            System.out.println("Y is empty :: " + y);
        }
        
        if (z instanceof Just<?>) {
            System.out.println("Z is concrete with value = " + ((Just<Integer>) z).getA());
        } else if (z instanceof Empty<?>) {
            System.out.println("Z is empty :: " + z);
        }
        
        IO<Void> ioAction = new IO<Void>(() -> {
            System.out.println("IO Action in action!");
            return null;
        });
        
        System.out.println("IO action = " + ioAction);
        System.out.println("Performing IO action...");
        ioAction.unwrap();
        
        int res = ioAction.bind((v) -> new IO<Integer>(() -> {
            System.out.println("Performing IO returning an int");
            return 5;
        })).unwrap();
        
        System.out.println("res = " + res);
        
        perform(putStrLn("Hello"), putStrLn("World!"), putStrLn("I'm Sid"),
                putStrLn("Trying to make Munnadic Java real!"));
    }
    
}
