import java.util.*;

public class Matrix {
    private List<List<Integer>> d;
    int r, c;

    public Matrix(int r, int c) {
        this.r = r; this.c = c;
        d = new ArrayList<>(r);
        Random rand = new Random();
        for (int i = 0; i < r; i++) {
            List<Integer> row = new ArrayList<>(c);
            for (int j = 0; j < c; j++) row.add(rand.nextInt(100));
            d.add(row);
        }
    }

    public List<Integer> getRow(int i) { return d.get(i); }
    public List<Integer> getColumn(int i) {
        List<Integer> col = new ArrayList<>(r);
        for (List<Integer> row : d) col.add(row.get(i));
        return col;
    }

    public void setElement(int r, int c, int v) { d.get(r).set(c, v); }
    public int getNrRows() { return r; }
    public int getNrColumns() { return c; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> row : d) {
            for (int el : row) sb.append(el).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
