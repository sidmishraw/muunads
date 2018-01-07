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
     * Useful for read actions, useful for IO actions that take no input and produce some output.
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
    
    /*
     * <p>Performs the IO action to return the result of the action.</p>
     * 
     * @see muunads.Monad#unwrap()
     * 
     * @return the result of the IO action.
     */
    @Override
    public A unwrap() {
        return this.action.get();
    }
}
