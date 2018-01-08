/**
 * Project: Muunads
 * Package: muunads.utils
 * File: Muunads.java
 * 
 * @author sidmishraw
 *         Last modified: Jan 6, 2018 2:24:48 PM
 */
package muunads.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.io.IO;

/**
 * Utility functions taking advantage of Monads.
 * 
 * @author sidmishraw
 *
 *         Qualified Name: muunads.utils.Muunads
 *
 */
public class Muunads {
    
    private static final Logger logger = LoggerFactory.getLogger(Muunads.class);
    
    /**
     * <p>
     * Haskell like {@code do} block. The following Haskell snippet exhibits the usage of the {@code do}
     * block.
     * </p>
     * {@code 
     *      main = do
     *          putStrLn "Hello"
     *          putStrLn "World"
     * }
     * 
     * <p>
     * The {@code do} block is syntactic sugar for the bind operation.
     * </p>
     * {@code 
     *      main = putStrLn "Hello" >>=  (putStrLn "World")
     * }
     * 
     * <p>
     * Or for a more complicated example.
     * </p>
     * {@code 
     *      -- using the `do` block
     *      main = do
     *          val <- readLn -- reads a line from the stdio, the value is unboxed and assigned to `val`.
     *          putStrLn val  -- displays the value of `val` to the stdio(console).
     * }
     * {@code 
     *      -- using >>= operator
     *      main = readLn >>= (\a -> putStrLn a)
     * }
     * 
     * <p>
     * What I intend to achieve is something like:
     * </p>
     * {@code 
     * IO<String> readLn = new IO<String>(() -> {
     *      String input = null;
     *      try(BufferReader br = new BufferReader(new InputStringReader(System.in)) {
     *           input = br.readLine();
     * } catch(Exception e) {
     * e.printStackTrace();
     * }
     * return input;
     * });
     * }
     */
    
    /**
     * <p>
     * Performs the IO action and returns the result.
     * </p>
     * 
     * @param ioAction
     *            The IO action to perform.
     * @return The result of the IO action.
     */
    public static final <T> T Do(IO<T> ioAction) {
        return ioAction.unwrap();
    }
    
    /**
     * <p>
     * This action when performed, reads a line from the System.in or standard input stream.
     * </p>
     * 
     * <blockquote>Defaults to console.</blockquote>
     * 
     * <p>
     * Based on Haskell's {@code readLn :: Read a => IO a} function. Usage:
     * </p>
     * 
     * <pre>
     * {@code
     *     // will peform the read action and store the String in `x`.
     *     // String x = readLn.unwrap();
     * }
     * </pre>
     */
    public static final IO<String> readLn = new IO<String>(() -> {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in) {
            /**
             * do nothing, this will prevent
             * the try with resources from closing
             * the STDIN stream.
             * 
             * @throws IOException
             */
            @Override
            public void close() throws IOException {}
        })) {
            input = br.readLine();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return input;
    });
    
    /**
     * <p>
     * Puts the string onto the STDOUT (console).
     * </p>
     * 
     * <p>
     * Based on Haskell's {@code putStrLn :: String -> IO ()}. Here, {@code ()} or {@code Unit} is replaced by
     * {@linkplain Void}.
     * </p>
     * 
     * <pre>
     * Usage:
     * 
     * {@code 
     *    // will put the input/applied string on the STDOUT.
     *    putStrLn.apply("Hello world!").unwrap();
     * }
     * 
     * </pre>
     */
    public static final Function<String, IO<Void>> putStrLn = (String str) -> new IO<Void>(() -> {
        System.out.println(str);
        return null;
    });
}
