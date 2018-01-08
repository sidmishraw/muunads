/**
 * Project: Muunads
 * Package: example
 * File: Maybe.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 22, 2017 1:05:01 PM
 */
package example;

import muunads.Monad;

/**
 * Similar to Haskell's Maybe a monad.
 * {@code Maybe a = Empty | Just a}
 * 
 * @author sidmishraw
 *
 *         Qualified Name: example.Maybe
 *
 */
public abstract class Maybe<A> implements Monad<A> {}
