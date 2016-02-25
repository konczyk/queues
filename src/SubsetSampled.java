import java.util.Scanner;
import java.util.Random;

/**
 * Client, that takes integer parameter k, reads in a sequence of strings
 * from standard input and prints exactly k of them, uniformly at random,
 * using RandomizedQueue object of maximum size at most k.
 */
public class SubsetSampled {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; sc.hasNext(); i++) {
            String s = sc.next();
            if (i < k) {
                rq.enqueue(s);
            } else if (rand.nextInt(i + 1) < k) {
                rq.dequeue();
                rq.enqueue(s);
            }
        }

        for (String s: rq) {
            System.out.println(s);
        }
    }
}
