
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

public class VectorSum {
    private static class Node {
        public Node l, r;
        public int v = 0;
        public int sum() {
            int ls = l == null ? 0 : l.sum();
            int rs = r == null ? 0 : r.sum();
            v += ls + rs;
            return v;
        }
    }

    private static ExecutorService pool;

    private static Node split(Node t, int[] arr) throws ExecutionException, InterruptedException {
        if (arr.length == 1) {
            t.v = arr[0];
            return t;
        }
        int m = arr.length / 2;
        var lTask = pool.submit(() -> split(new Node(), Arrays.copyOfRange(arr, 0, m)));
        var rTask = pool.submit(() -> split(new Node(), Arrays.copyOfRange(arr, m, arr.length)));
            t.l = lTask.get();
            t.r = rTask.get();

        return t;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        int th = scanner.nextInt();

        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        pool = Executors.newWorkStealingPool(th);
        Node t = new Node();
        split(t, arr);
        int sum = t.sum();

        System.out.println(sum);

    }
}
