/**
 * Project: Muunads
 * Package: example
 * File: SimpleDriver2.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 30, 2017 9:27:27 PM
 */
package example;

import maybe.Maybe;

/**
 * @author sidmishraw
 *
 *         Qualified Name: example.SimpleDriver2
 *
 */
public class SimpleDriver2 {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Maybe<Integer> i = Maybe.Nothing.getInstance();
        Maybe<Character> c = Maybe.Nothing.getInstance();
        System.out.println(i);
        System.out.println(c);
        System.out.println(Maybe.Nothing.getInstance().wrap('a'));
    }
    
}
