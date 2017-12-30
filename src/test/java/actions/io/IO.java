/**
 * Project: Muunads
 * Package: actions.io
 * File: IO.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 23, 2017 10:59:54 AM
 */
package actions.io;

import java.util.function.Function;
import java.util.function.Supplier;

import muunads.Monad;

/**
 * The IO Action. Actions that have something to do with IO.
 * For eg - Logging, HTTP, etc.
 * 
 * @author sidmishraw
 *
 *         Qualified Name: actions.io.IO
 *
 */
public class IO<A> implements Monad<A> {
    
    /**
     * The action performed when the IO action is performed, basically the IO action.
     */
    private Supplier<A> action;
    
    /**
     * Creates an IO action.
     * 
     * @param action
     *            the IO action to perform.
     */
    public IO(Supplier<A> action) {
        this.action = action;
    }
    
    /*
     * Wraps `a` in an IO action, which when performed gives back a.
     * 
     * @see muunads.Monad#wrap(java.lang.Object)
     */
    @Override
    public Monad<A> wrap(A a) {
        return new IO<>(() -> a); // wrap the a in a supplier to simulate a delayed action
    }
    
    /*
     * Performs the IO action, then shoves the result of the action to the function provided (bound).
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @Override
    public <B extends Monad<C>, C> B bind(Function<A, B> fromAToMonadOfB) {
        A actionResult = this.action.get(); // perform the action and the result is passed on to the function {@code
                                            // fromAToMonadOfB}
        return fromAToMonadOfB.apply(actionResult);
    }
    
    /**
     * Performs the IO action to return the result of the action.
     * 
     * @return the result of the IO action.
     */
    public A perform() {
        return this.action.get();
    }
}
