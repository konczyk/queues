import java.util.Scanner;

public class Subset {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // number of items to print
        int k = Integer.parseInt(args[0]);

        // populate queue
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (sc.hasNext())
            rq.enqueue(sc.next());

        // print k items uniformly at random, without repetitions
        while (k > 0) {
            System.out.println(rq.dequeue());
            k--;
        }

    }

}
