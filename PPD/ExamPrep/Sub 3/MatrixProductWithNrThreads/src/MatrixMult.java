import java.util.List;

public class MatrixMult {
    public Matrix multiply(Matrix A, Matrix B, int t) throws InterruptedException {
        Matrix res = new Matrix(A.getNrRows(), B.getNrColumns());
        int r = res.getNrRows(), c = res.getNrColumns();
        Thread[] ts = new Thread[t];

        for (int i = 0; i < t; i++) {
            int s = i, k = t;
            ts[i] = new Thread(() -> computeKth(A, B, res, s, k, r, c));
            ts[i].start();
        }

        for (Thread th : ts) th.join();
        return res;
    }

    private void computeKth(Matrix A, Matrix B, Matrix res, int s, int k, int r, int c) {
        for (int i = 0; i < r; i++) {
            for (int j = s; j < c; j += k) {
                res.setElement(i, j, compute(A.getRow(i), B.getColumn(j)));
            }
        }
    }

    private int compute(List<Integer> row, List<Integer> col) {
        int res = 0;
        for (int i = 0; i < row.size(); i++) res += row.get(i) * col.get(i);
        return res;
    }
}
