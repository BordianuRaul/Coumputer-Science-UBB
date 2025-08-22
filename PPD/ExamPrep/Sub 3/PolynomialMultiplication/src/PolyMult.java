import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class PolyMult {
    static int[] p1 = {3, 2, 5, 5, 5};
    static int[] p2 = {1, 4, 2, 5, 5};
    static int[] res;
    static int n = p1.length;
    static ReentrantLock lock = new ReentrantLock();

    public static void mul(int s, int e) {
        for (int i = s; i < e; i++) {
            for (int j = 0; j < n; j++) {
                lock.lock();
                try { res[i + j] += p1[i] * p2[j]; }
                finally { lock.unlock(); }
            }
        }
    }

    public static void run(int t, int s, int e) throws InterruptedException {
        if (t <= 1) { mul(s, e); }
        else {
            int slice = (e - s) / t;
            Thread[] ts = new Thread[t];
            for (int i = 0; i < t; i++) {
                int st = s + i * slice;
                int en = (i == t - 1) ? e : st + slice;
                ts[i] = new Thread(() -> mul(st, en));
                ts[i].start();
            }
            for (Thread th : ts) { th.join();}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Threads: ");
        int t = sc.nextInt();
        sc.close();
        res = new int[2 * n - 1];
        run(t, 0, n);
        for (int v : res) System.out.print(v + " ");
    }
}
