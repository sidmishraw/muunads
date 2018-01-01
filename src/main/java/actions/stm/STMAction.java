/**
 * Project: Muunads
 * Package: actions.stm
 * File: STMAction.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 23, 2017 1:07:57 PM
 */
package actions.stm;

import java.util.function.Function;
import java.util.function.Supplier;

import muunads.Monad;

/**
 * A STM action. To be used as the actions inside the Transactions.
 * 
 * @author sidmishraw
 *
 *         Qualified Name: actions.stm.STMAction
 *
 */
public class STMAction<A> implements Monad<A> {
    
    /**
     * The action performed when the STMAction is executed.
     */
    private Supplier<A> action;
    
    /**
     * Creates a new STMAction with the specified action.
     * 
     * @param action
     *            the action to be performed when executing the STMAction.
     */
    public STMAction(Supplier<A> action) {
        this.action = action;
    }
    
    /*
     * Wraps `a` in a Supplier (function) delaying the action. Gives back a new STMAction, which when performed returns
     * `a`.
     * 
     * @see muunads.Monad#wrap(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <M extends Monad<A>> M wrap(A a) {
        return (M) new STMAction<>(() -> a); // delayed action returns `a`
    }
    
    /*
     * First executes the current STMAction, the result is then shoved into the function `fromAToMonadOfB` and the
     * resulting Monad is returned.
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @Override
    public <B extends Monad<C>, C> B bind(Function<A, B> fromAToMonadOfB) {
        A actionResult = this.perform();
        return fromAToMonadOfB.apply(actionResult);
    }
    
    /**
     * Performs the STMAction and returns any result of the action.
     * 
     * @return the result obtained after performing the STMAction.
     */
    public A perform() {
        return this.action.get();
    }
}
