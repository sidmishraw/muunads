/**
 * Project: Muunads
 * Package: maybe
 * File: Maybe.java
 * 
 * @author sidmishraw
 *         Last modified: Dec 30, 2017 7:14:59 PM
 */
package maybe;

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
public abstract class Maybe<T> implements Monad<T> {}
