import java.util.*;
import java.util.concurrent.*;

public class PrimeFinder {
    private static int t = 4, n = 100;
    private static List<Integer> primes = Collections.synchronizedList(new ArrayList<>());
    private static List<Integer> basePrimes = new ArrayList<>();

    private static boolean isPrime(int num, List<Integer> base) {
        int limit = (int) Math.sqrt(num);
        for (int p : base) {
            if (p > limit) break;
            if (num % p == 0) return false;
        }
        return true;
    }

    private static void find(int start, int end, List<Integer> base, List<Integer> res) {
        for (int i = start; i <= end; i++)
            if (isPrime(i, base))
                synchronized (primes) { res.add(i); }
    }

    private static void compute() throws InterruptedException, ExecutionException {
        int limit = (int) Math.sqrt(n);
        for (int i = 3; i <= limit; i += 2)
            if (isPrime(i, basePrimes)) {
                basePrimes.add(i);
                primes.add(i);
            }

        ExecutorService exec = Executors.newFixedThreadPool(t);
        List<Future<List<Integer>>> tasks = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            int start = limit + 1 + (i * (n - limit)) / t;
            int end = limit + 1 + ((i + 1) * (n - limit)) / t - 1;
            tasks.add(exec.submit(() -> {
                List<Integer> res = new ArrayList<>();
                find(start, end, basePrimes, res);
                return res;
            }));
        }

        for (Future<List<Integer>> task : tasks) primes.addAll(task.get());
        exec.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        primes.add(2);
        basePrimes.add(2);
        compute();
        primes.sort(Integer::compareTo);
        System.out.println(primes);
    }
}
