# Randomized Queues and Deques

Estimate the value of the percolation threshold via Monte Carlo simulation. 

## Examples 

Build a jar file:

    $ ./gradlew assemble

Print 4 strings from the input sequence, uniformly at random, using a simple 
Subset client:

    $ echo "A B C D E F G H I" | java -cp build/libs/queues.jar Subset 4 

    D
    C
    G
    F

Print 4 strings from the generated input of 100000 strings, uniformly at 
random, using a simple Subset client:

    $ ./gen_strings.py 100000 | java -cp build/libs/queues.jar Subset 4 

    CB
    BCA
    A
    BC

Print 4 strings from the generated input of 100000 strings (up to 5 chars long), 
uniformly at random, using a SubsetSampled client which implements a reservoir 
sampling to limit the size of a queue:

    $ ./gen_strings.py 100000 --maxlen 5 | java -cp build/libs/queues.jar SubsetSampled 4 

    EAD
    BECDA
    DCAE
    BAC
