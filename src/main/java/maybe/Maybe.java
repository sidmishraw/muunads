/**
 * Project: Muunads
 * Package: maybe
 * File: Maybe.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 30, 2017 7:14:59 PM
 */
package maybe;

import java.util.Objects;
import java.util.function.Function;

import lombok.Getter;
import muunads.Monad;

/**
 * <p>
 * The Maybe monad. Represents optional value. If there is a concrete value, the value is Just else it is Empty.
 * </p>
 * 
 * <p>
 * Haskell code
 * <code>
 *   f1 :: Maybe Int -> IO ()
 *   f1 x = case x of
 *      Nothing -> putStrLn "Nothing"
 *      (Just x) -> putStrLn $ "Just " ++ (show x) 
 * </code>
 * </p>
 * 
 * <p>
 * <code>
 *      Maybe<Integer> i = new Just(4);
 *      if(!i.equals(Maybe.Empty()) {
 *          System.out.println("The value of i = " + i.value());
 *      } else {
 *          System.out.println("i is empty");
 *      }
 * </code>
 * </p>
 * 
 * 
 * @author sidmishraw
 *
 *         Qualified Name: maybe.Maybe
 *
 */
public abstract class Maybe<T> implements Monad<T> {
    
    /**
     * <p>
     * Nothing type constructor.
     * </p>
     * 
     * @author sidmishraw
     *
     *         Qualified Name: maybe.Nothing
     *
     * @param <T>
     */
    public static final class Nothing<T> extends Maybe<T> {
        
        /**
         * Singleton instance.
         */
        private static volatile Maybe<?> singleton = null;
        
        private Nothing() {}
        
        /**
         * <p>
         * The singleton instance of Nothing<T>
         * </p>
         * 
         * @return the singleton instance.
         */
        @SuppressWarnings("unchecked")
        public static final synchronized <T> Maybe<T> getInstance() {
            if (Objects.isNull(singleton)) {
                singleton = new Nothing<T>();
            }
            return (Nothing<T>) singleton;
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see muunads.Monad#wrap(java.lang.Object)
         */
        @SuppressWarnings("unchecked")
        @Override
        public <M extends Monad<T>> M wrap(T a) {
            return (M) getInstance();
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see muunads.Monad#bind(java.util.function.Function)
         */
        @SuppressWarnings("unchecked")
        @Override
        public <C extends Monad<B>, B> C bind(Function<T, C> fromAToMonadOfB) {
            return (C) getInstance();
        }
        
    }
    
    /**
     * <p>
     * Just the value, concrete value.
     * </p>
     * 
     * @author sidmishraw
     *
     *         Qualified Name: maybe.Just
     *
     * @param <T>
     */
    public static final class Just<T> extends Maybe<T> {
        
        private @Getter T value;
        
        /**
         * @param value
         */
        public Just(T value) {
            this.value = value;
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see muunads.Monad#wrap(java.lang.Object)
         */
        @SuppressWarnings("unchecked")
        @Override
        public <M extends Monad<T>> M wrap(T a) {
            return (M) new Just<T>(a);
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see muunads.Monad#bind(java.util.function.Function)
         */
        @Override
        public <C extends Monad<B>, B> C bind(Function<T, C> fromAToMonadOfB) {
            return fromAToMonadOfB.apply(this.value);
        }
        
    }
}
