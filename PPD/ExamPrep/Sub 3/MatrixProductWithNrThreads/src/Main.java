import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Matrix lhs = new Matrix(3, 3);
        //Matrix rhs = new Matrix(3, 6);

        Scanner scanner = new Scanner(System.in);
        int nrThreads = scanner.nextInt();
        System.out.println("Power: ");
        int power = scanner.nextInt();

        MatrixMult matrixMult = new MatrixMult();

        Matrix res = lhs;
        for(int i = 1; i < power; i++) {
            res = matrixMult.multiply(res, lhs, nrThreads);
        }

        //Matrix result = matrixMult.multiply(lhs, rhs, nrThreads);

        System.out.println(lhs);
        //System.out.println(rhs);

        System.out.println(res);
    }
}