/**
 * Project: Muunads
 * Package: muunads
 * File: Monad.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 22, 2017 4:46:04 PM
 */
package muunads;

import java.util.function.Function;

/**
 * A Monad of type T.
 * 
 * @author sidmishraw
 *
 *         Qualified Name: muunads.Monad
 *
 */
public interface Monad<A> {
    
    /**
     * Similar to Haskell's {@code return} function. It takes something of type A and wraps it into a Monad<A>.
     * 
     * <p>
     * In Haskell, it is defined as:
     * 
     * <p>
     * {@code return :: a -> M a}
     * 
     * <p>
     * {@code return a = M a }
     * 
     * @param a
     *            the object of type A
     * @return the Monad<A> or container/context containing {@code a}
     */
    public <M extends Monad<A>> M wrap(A a);
    
    /**
     * Binds or shoves a Monad {@code M a} into the function {@code (f: a -> M b)} to produce a new Monad {@code (M b)}.
     * 
     * <p>
     * 
     * The bind method of a monad allows for chaining or composition.
     * 
     * <p>
     * 
     * Similar to Haskell's {@code (>>=) :: M a -> (a -> M b) -> M b}
     * 
     * @param fromAToMonadOfB
     *            a function that takes something of type A and returns a Monad of type B, f :: a -> M b
     * @return the result, Monad of type B, Monad<B>
     */
    public <C extends Monad<B>, B> C bind(Function<A, C> fromAToMonadOfB);
}
