/**
 * Project: Muunads
 * Package: maybe
 * File: Nothing.java
 * 
 * @author sidmishraw
 *         Last modified: Jan 6, 2018 2:17:27 PM
 */
package maybe;

import java.util.Objects;
import java.util.function.Function;

import muunads.Monad;

/**
 * @author sidmishraw
 *
 *         Qualified Name: maybe.Nothing
 *
 */
public class Nothing<T> extends Maybe<T> {
    
    /**
     * To make Nothing<T> singleton.
     */
    private static Nothing<?> singleton = null;
    
    /**
     * Nothing. Symbolizes the nothing value.
     * 
     * @return Nothing<T>
     */
    @SuppressWarnings("unchecked")
    public static <T> Nothing<T> getInstance() {
        if (Objects.isNull(singleton)) {
            singleton = new Nothing<T>();
        }
        return (Nothing<T>) singleton;
    }
    
    /**
     * 
     */
    private Nothing() {}
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <C extends Monad<B>, B> C bind(Function<T, C> fromAToMonadOfB) {
        return (C) this;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see muunads.Monad#unwrap()
     */
    @Override
    public T unwrap() {
        return null;
    }
}
