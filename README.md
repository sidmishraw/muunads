# Muunads

Author: Sidharth Mishra


## Description

Muunads are Monads for Java. These *monads* are inspired by Haskell's monads.

My IO and STM actions are designed to be lazy. They are performed with call to `unwrap()`.

## Usage Examples as of v0.2

```java

    /**
     * <p>
     * Uses Muunads `Do` utility method.
     * </p>
     */
    private static void usingMuunadsDo() {
        String msg = Muunads.Do(Muunads.readLn);
        Muunads.Do(Muunads.putStrLn.apply("Sample using `Muunads.Do()` :: \n " + msg));
    }

    /**
     * <p>
     * Uses the {@link Monad#unwrap()} instead of {@link Muunads#Do(actions.io.IO)}.
     * </p>
     */
    private static void usingUnwrap() {
        String msg = Muunads.readLn.unwrap();
        Muunads.putStrLn.apply("Sample using `unwrap` :: \n " + msg).unwrap();
    }
    
    ...
    
	/**
     * @param args
     */
    public static void main(String[] args) {
        usingMuunadsDo();
        Muunads.putStrLn.apply("---------").unwrap(); // Action is not performed immediately without a call to unwrap()
        usingUnwrap();
    }
    
```


## IO Actions are Muunads

## Changelog v0.2

* The Monad interface has dropped `wrap()` and added `unwrap()`. `unwrap()` performs the action and returns the contents of the Monad or action.

* This also eliminates the need for a `perform()` in the `IO<A>` Monad.

* Also, this version includes the `Muunads` utility class. It has examples for Haskell's `readLn` and `putStrLn` functions.

```java
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
```

* `readLn` in Muunads is defined as an `IO<String>` action. When performed using the `unwrap()`, it will read a line from the `STDIN` and return the `String`.

> Note: Because `try-with-resources` block closes the `System.in` stream if the `close()` of `InputStreamReader` class is not overridden, I have overridden the close() to do nothing.

* Similarly, `putStrLn` in Muunads is defined as a `Function<String, IO<Void>>` that takes in a String and produces an `IO<Void>` action, which when performed will put the applied String on the `STDOUT`.

```java
    public static final Function<String, IO<Void>> putStrLn = (String str) -> new IO<Void>(() -> {
        System.out.println(str);
        return null;
    });
```

## Changelog v0.1

Consider the following IO action in Haskell:

```haskell
main :: IO ()
main = putStrLn "Hello World!"
```

The `IO ()` action performs an IO action which prints `"Hello World!"` to the `STDOUT` and returns `()` unit or `Void`.
Similarly, Muunads also defines an IO action for Java. It too is a Monad.

```java
IO<Void> ioAction = new IO<Void>(() -> {
    System.out.println("IO Action in action!");
    return null;
});

ioAction.perform(); // prints `IO Action in action!` to STDOUT and returns nothing or null (Void).
```

The problem at first glance is the verbosity. Second, Haskell's IO system was built from a monadic point of view but, Java's was not. 
Using this library, the consumer is able to get the power of Haskell like monadic IO actions but, they need to design the actions themselves.

The following example demonstrates a possible usage of the IO action in Java to emulate `putStrLn` in Haskell.

```java
/**
 * Returns an IO action that prints the string onto STDOUT.
 * 
 * @param str
 *            the string to be printed to STDOUT.
 * @return the IO action.
 */
public static IO<Void> putStrLn(String str) {
    return new IO<Void>(() -> {
        System.out.println(str);
        return null;
    });
}

/**
 * Performs the IO<Void> actions sequentially.
 * 
 * @param actions
 *            the IO<Void> actions to perform
 */
@SafeVarargs
public static void perform(IO<Void>... actions) {
    Arrays.asList(actions).forEach(act -> {
        act.perform();
    });
}

...

// somewhere inside main()
perform(putStrLn("Hello"), 
	putStrLn("World!"), 
	putStrLn("I'm Sid"),
	putStrLn("Trying to make Munnadic Java real!"));
```

In Haskell this would be as simple as:

```haskell
main :: IO()
main = do
  putStrLn "Hello"
  putStrLn "World!"
  putStrLn "I'm Sid"
  putStrLn "Trying to make Munnadic Java real!"
```

But, this syntactically this sweet-sugary form comes from Haskell's library and language syntax. However, this is easily emulated by my `putStrLn()` and `perform()` methods.

## Requirements

* Java 8+ for LAMBDAS --- since lambdas are supported from Java8+

* Project Lombok for boilerplate reduction

* Gradle for builds
