//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MatrixMultiplication extends Thread {
    static int[][] A = {
            {1, 2},
            {3, 4}
    };
    static int[][] B = {
            {5, 6},
            {7, 8}
    };
    static int[][] C = new int[2][2];
    int row;

    // Constructor to accept the row index this thread will handle
    MatrixMultiplication(int row) {
        this.row = row;
    }

    // Thread execution starts here
    public void run() {
        for (int col = 0; col < 2; col++) {
            C[row][col] = 0;
            for (int i = 0; i < 2; i++) {
                C[row][col] += A[row][i] * B[i][col];
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create threads for each row
        MatrixMultiplication[] threads = new MatrixMultiplication[2];

        for (int i = 0; i < 2; i++) {
            threads[i] = new MatrixMultiplication(i);
            threads[i].start(); // start the thread
        }

        // Wait for all threads to finish
        for (int i = 0; i < 2; i++) {
            threads[i].join();
        }

        // Print the result matrix
        System.out.println("Result Matrix C:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
