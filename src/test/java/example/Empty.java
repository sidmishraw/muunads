/**
 * Project: Muunads
 * Package: example
 * File: Empty.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 22, 2017 5:34:33 PM
 */
package example;

import java.util.Objects;
import java.util.function.Function;

import muunads.Monad;

/**
 * @author sidmishraw
 *
 *         Qualified Name: example.Empty
 *
 */
public class Empty<T> extends Maybe<T> {
    
    private static Empty<?> empty = null;
    
    private Empty() {}
    
    /**
     * Gets the singleton instance of the Empty type.
     * 
     * @param t
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Maybe<T> getInstance(T t) {
        if (Objects.isNull(empty)) {
            empty = new Empty<>();
        }
        return (Maybe<T>) empty;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#wrap(java.lang.Object)
     */
    @Override
    public Monad<T> wrap(T a) {
        return getInstance(a);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <B extends Monad<C>, C> B bind(Function<T, B> fromAToMonadOfB) {
        return (B) this;
    }
}
