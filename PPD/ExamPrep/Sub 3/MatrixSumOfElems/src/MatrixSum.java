import java.util.concurrent.atomic.AtomicInteger;

public class MatrixSum {
    static final int THREADS = 2;
    static final int MATRIX[][] = {{1,9,1},{1,1,1},{1,1,1}};
    static final int ROWS = MATRIX.length;
    static final int COLS = MATRIX[0].length;
    static final AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> computeSum(0, 0, ROWS * COLS, THREADS - 1));
        t.start(); t.join();

        System.out.println(SUM.get());
    }

    static void computeSum(int r, int c, int size, int depth) {
        if (size == 1) { SUM.addAndGet(MATRIX[r][c]); return; }
        int leftSize = (size + 1) / 2;
        int rightSize = size / 2;
        int r2 = r + (leftSize / COLS);
        int c2 = (c + (leftSize % COLS)) % COLS;
        if (c + (leftSize % COLS) >= COLS) r2++;
        if (depth == 0) { computeSum(r, c, leftSize, 0); computeSum(r2, c2, rightSize, 0); }
        else {
            Thread t1 = new Thread(() -> computeSum(r, c, leftSize, (depth + 1) / 2 - 1));
            int finalR = r2;
            Thread t2 = new Thread(() -> computeSum(finalR, c2, rightSize, depth / 2 - 1));
            t1.start(); t2.start();
            try { t1.join(); t2.join(); } catch (InterruptedException e) {}
        }
    }
}
