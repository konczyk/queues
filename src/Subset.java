import java.util.Scanner;

/**
 * Client, that takes integer parameter k, reads in a sequence of strings
 * from standard input and prints exactly k of them, uniformly at random
 */
public class Subset {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (sc.hasNext()) {
            rq.enqueue(sc.next());
        }

        while (k > 0) {
            System.out.println(rq.dequeue());
            k--;
        }

    }

}
