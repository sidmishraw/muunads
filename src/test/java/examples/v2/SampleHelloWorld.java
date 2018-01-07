/**
 * Project: Muunads
 * Package: examples.v2
 * File: SampleHelloWorld.java
 * 
 * @author sidmishraw
 *         Last modified: Jan 6, 2018 7:44:08 PM
 */
package examples.v2;

import muunads.Monad;
import muunads.utils.Muunads;

/**
 * <p>
 * A sample Hello world application using Monads.
 * </p>
 * 
 * @author sidmishraw
 *
 *         Qualified Name: examples.v2.SampleHelloWorld
 *
 */
public class SampleHelloWorld {
    
    /**
     * <p>
     * Uses Muunads `Do` utility method.
     * </p>
     */
    private static void usingMuunadsDo() {
        String msg = Muunads.Do(Muunads.readLn);
        Muunads.Do(Muunads.putStrLn.apply("Sample using `Muunads.Do()` :: \n " + msg));
    }
    
    /**
     * <p>
     * Uses the {@link Monad#unwrap()} instead of {@link Muunads#Do(actions.io.IO)}.
     * </p>
     */
    private static void usingUnwrap() {
        String msg = Muunads.readLn.unwrap();
        Muunads.putStrLn.apply("Sample using `unwrap` :: \n " + msg).unwrap();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        usingMuunadsDo();
        Muunads.putStrLn.apply("---------").unwrap(); // Action is not performed immediately.
        usingUnwrap();
    }
}
