import java.util.Scanner;
import java.util.Random;

public class SubsetSampled {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // number of items to print
        // equals max number of items in the queue
        int k = Integer.parseInt(args[0]);

        // populate queue
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

        for (String s: rq)
            System.out.println(s);
    }
}
