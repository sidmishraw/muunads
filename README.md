# Muunads

Author: Sidharth Mishra


## Description

Muunads are Monads for Java. These *monads* are inspired by Haskell's monads.


## IO Actions are Muunads

Consider the following IO action in Haskell:

```haskell
main :: IO ()
main = putStrLn "Hello World!"
```

The `IO ()` action performs an IO action which prints `"Hello World!"` to the `STDOUT` and returns `()` unit or `void`.
Similarly, Muunads also defines an IO action for Java. It too is a monad.

```java
IO<Void> ioAction = new IO<Void>(() -> {
    System.out.println("IO Action in action!");
    return null;
});

ioAction.perform(); // prints `IO Action in action!` to STDOUT and returns nothing
```

The problem at first glance is the verbosity. Second, Haskell's IO system was built from a monadic point of view but, Java's was not. Using this library, the consumer is able to get the power of Haskell like monadic IO actions but, they need to design the actions themselves.

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
