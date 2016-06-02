import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.PositiveInteger;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Client, that reads in or generates a sequence of string and prints exactly
 * k of them, uniformly at random
 */
public class Subset {

    private final Random rand = new Random();

    @Parameter(
        names = {"--subset", "-k"},
        description = "Subset of strings to print",
        required = true,
        validateWith = PositiveInteger.class)
    private int subset;

    @Parameter(
        names = {"--strings-num", "-n"},
        description = "Number of generated strings",
        validateWith = PositiveInteger.class)
    private int num;

    @Parameter(
        names = {"--maxlen", "-m"},
        description = "Generated string max length",
        validateWith = MaxLenValidator.class)
    private int max = 3;

    @Parameter(
        names = {"--sampling", "-s"},
        description = "Use reservoir sampling to limit the queue size to the subset size")
    private boolean sampling = false;

    @Parameter(
        names = {"--help", "-h"},
        description = "Usage help",
        help = true)
    private boolean help = false;

    @Parameter(
        names = {"--stdin", "-"},
        description = "Read strings from stdin ")
    private boolean stdin = false;

    public static class MaxLenValidator implements IParameterValidator {
        @Override
        public void validate(String name, String value) throws ParameterException {
            String msg = "Parameter --maxlen should be a positive integer "
                + "between 1 and 10 (found " + value + ")";
            try {
                int n = Integer.parseInt(value);
                if (n < 1 || n > 10) {
                    throw new ParameterException(msg);
                }
            } catch (Exception e) {
                throw new ParameterException(msg);
            }
        }
    }

    public static void main(String[] args) {
        Subset client = new Subset();
        JCommander jc = new JCommander(client);
        jc.setProgramName("Subset");

        if (args.length == 0) {
            jc.usage();
            return;
        }
        try {
            jc.parse(args);
            client.validate();
            if (client.help) {
                jc.usage();
                return;
            }
            client.run();
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validate() throws ParameterException {
        if (stdin && num > 0) {
            throw new ParameterException(
                "Parameters --stdin and --strings-num are mutually exclusive");
        }
    }

    private void run() {
        RandomizedQueue<String> rq;
        if (stdin) {
            try (Scanner sc = new Scanner(System.in)) {
                if (!sampling) {
                    rq = buildQueue(sc);
                } else {
                    rq = buildMinimumSizeQueue(sc);
                }
            }
        } else {
            if (!sampling) {
                rq = buildQueue(new Generator());
            } else {
                rq = buildMinimumSizeQueue(new Generator());
            }
        }

        while (subset > 0) {
            System.out.println(rq.dequeue());
            subset--;
        }
    }

    private RandomizedQueue<String> buildQueue(Iterator<String> iterator) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (iterator.hasNext()) {
            rq.enqueue(iterator.next());
        }

        return rq;
    }

    private RandomizedQueue<String> buildMinimumSizeQueue(Iterator<String> iterator) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; iterator.hasNext(); i++) {
            String s = iterator.next();
            if (i < subset) {
                rq.enqueue(s);
            } else if (rand.nextInt(i + 1) < subset) {
                rq.dequeue();
                rq.enqueue(s);
            }
        }

        return rq;
    }

    private class Generator implements Iterator<String> {

        private int generated = 0;
        private final StringBuilder sb = new StringBuilder();

        @Override
        public boolean hasNext() {
            return generated < num;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("operation not supported");
        }

        @Override
        public String next() {
            sb.setLength(0);
            int len = rand.nextInt(max);
            for (int i = 0; i <= len; i++) {
                sb.append((char) (rand.nextInt(10) + 65));
            }
            generated++;

            return sb.toString();
        }
    }
}
