import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MatrixPower {
    private static int t = 4, n = 3, p = 2;
    private static int[][] a = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    private static int computePoint(int[][] a, int[][] b, int i, int j) {
        int sum = 0;
        for (int k = 0; k < n; k++) {
            sum += a[i][k] * b[k][j];
        }
        return sum;
    }

    private static List<int[]> splitWorkload(int n, int t) {
        List<int[]> intervals = new ArrayList<>();
        int index = 0, step = n / t, mod = n % t;
        while (index < n) {
            intervals.add(new int[]{index, index + step + (mod > 0 ? 1 : 0)});
            index += step + (mod > 0 ? 1 : 0);
            mod--;
        }
        return intervals;
    }

    private static int[][] mult(int[][] a, int[][] b, int t) throws InterruptedException {
        int[][] result = new int[n][n];
        List<Thread> threads = new ArrayList<>();
        List<int[]> intervals = splitWorkload(n, t);

        for (int idx = 0; idx < t; idx++) {
            final int start = intervals.get(idx)[0];
            final int end = intervals.get(idx)[1];
            Thread thread = new Thread(() -> {
                for (int i = start; i < end; i++) {
                    for (int j = 0; j < n; j++) {
                        result[i][j] = computePoint(a, b, i, j);
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return result;
    }

    private static int[][] power(int[][] a, int p, int t) throws InterruptedException {
        if (p == 1) {
            return a;
        }

        int[][] temp = power(a, p / 2, t);
        if (p % 2 == 0) {
            return mult(temp, temp, t);
        } else {
            return mult(a, mult(temp, temp, t), t);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] res = power(a, p, t);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
