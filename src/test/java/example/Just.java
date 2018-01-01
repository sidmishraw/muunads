/**
 * Project: Muunads
 * Package: example
 * File: Just.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 22, 2017 6:49:23 PM
 */
package example;

import java.util.function.Function;

import lombok.Getter;
import muunads.Monad;

/**
 * @author sidmishraw
 *
 *         Qualified Name: example.Just
 *
 */
public class Just<A> extends Maybe<A> {
    
    /**
     * The concrete contents of the Maybe Monad.
     */
    private @Getter A a;
    
    /**
     * Just a
     * 
     * @param a
     *            the contents of the Just data constructor
     */
    public Just(A a) {
        this.a = a;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#wrap(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <M extends Monad<A>> M wrap(A a) {
        return (M) new Just<>(a);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @Override
    public <B extends Monad<C>, C> B bind(Function<A, B> fromAToMonadOfB) {
        return fromAToMonadOfB.apply(this.a);
    }
}
