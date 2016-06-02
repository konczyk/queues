# Randomized Queues and Deques

Generic data types for a deque and a randomized queue.

## Goal

Implement deque and randomized queue data structures using arrays and linked
lists.

## Implementation constraints
- Fixed public API for `Deque` and `RandomizedQueue`
- `Deque` and `RandomizedQueue` should not call library function except those
in `java.lang`, `java.util.Iterator`, `java.util.Random` and
`java.util.NoSuchElementException`
- `Subset` running time must be linear in the size of the input, must use only
a constant amount of memory plus either `Deque` or `RandomizedQueue` of size the
same as number of strings in the input

## Performance requirements
- Both deque and randomized queue containing n items must use at most 48n + 192
bytes of memory, with deque using space proportional to the number of items
currently in the deque
- `Deque` implementation must support each operation in constant worst-case
time, including iterator operations
- `RandomizedQueue` implementation must support each operation (except creating
an iterator) in constant amortized time, iterator construction in linear time
and iterator operations `next()` and `hasNext()` in constant worst-case time

## Enhancements
- Implement optional sampling in `Subset` client and use a `RandomizedQueue` of
size at most k, where k is the number of strings to print.

## Sample client

Build a jar file:

    $ ./gradlew assemble

Client options:

    $ java -cp build/libs/queues.jar Subset -h

Print a subset of 4 strings from the input sequence, uniformly at random:

    $ echo "A B C D E F G H I" | java -cp build/libs/queues.jar Subset -k 4 -

    D
    C
    G
    F

Print a subset of 4 strings from the input sequence, uniformly at random,
using reservoir sampling to limit the maximum size of the queue to k:

    $ echo "A B C D E F G H I" | java -cp build/libs/queues.jar Subset -k 4 -s -

    D
    C
    G
    F

Print a subset of 4 strings from the generated input of 100000 strings,
uniformly at random:

    $ java -cp build/libs/queues.jar Subset -k 4 -n 100000

    BCC
    A
    BHE
    FFG

Print a subset of 4 strings from the generated input of 100000 strings (up to 5
chars long each), uniformly at random, using reservoir sampling to limit the
maximum size of the queue to k:

    $ java -cp build/libs/queues.jar Subset -k 4 -n 100000 -m 5 -s

    BBAAD
    I
    FFEE
    IH
