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
     * First executes the current STMAction, the result is then shoved into the function `fromAToMonadOfB` and the
     * resulting Monad is returned.
     * 
     * @see muunads.Monad#bind(java.util.function.Function)
     */
    @Override
    public <B extends Monad<C>, C> B bind(Function<A, B> fromAToMonadOfB) {
        A actionResult = this.action.get();
        return fromAToMonadOfB.apply(actionResult);
    }
    
    /*
     * <p>Performs the STMAction and returns any result of the action.</p>
     * 
     * @see muunads.Monad#unwrap()
     * 
     * @return The result obtained after performing the STMAction.
     */
    @Override
    public A unwrap() {
        return this.action.get();
    }
}
