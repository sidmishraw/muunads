/**
 * Project: Muunads
 * Package: maybe
 * File: Just.java
 * 
 * @author sidmishraw
 *         Last modified: Jan 6, 2018 2:21:11 PM
 */
package maybe;

import java.util.function.Function;

import lombok.Getter;
import muunads.Monad;

/**
 * <p>
 * Represents the concrete value.
 * </p>
 * 
 * @author sidmishraw
 *
 *         Qualified Name: maybe.Just
 *
 */
public class Just<T> extends Maybe<T> {
    
    private @Getter T val;
    
    /**
     * Just the value.
     * 
     * @param val
     *            The value.
     */
    public Just(T val) {
        this.val = val;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @Override
    public <C extends Monad<B>, B> C bind(Function<T, C> fromAToMonadOfB) {
        return fromAToMonadOfB.apply(this.val);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#unwrap()
     */
    @Override
    public T unwrap() {
        return this.val;
    }
}
