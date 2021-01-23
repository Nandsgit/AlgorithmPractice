
import java.util.Arrays;
import static org.junit.Assert.*;

/**
 * Description of solution: Strassen’s matrix multiplication method makes 7 recursive calls.
 * Strassen’s method is a divide and conquer method in the sense that this method divides square
 * matrices into sub-matrices of size N/2 x N/2
 *
 * Explain in your solution documentation (in the Java file header):

- Which case of the Master's Theorem applies (CLRS case 1, 2 or 3).

    T(n) = O(1) + O(n^2) + 7 T(n/2) + O (n^2)
    T(n) = 7 T (n/2) + O(n^2)
    f(n) = n^2
    n^(logb(a)) = n^(log2(7) = n^2.80736

    So we can conclude that Case 1 of Master Method applies to Stassen's Matrix which gives us O(n^2.81)

- What is the time complexity of the above method in terms of an upper bound O(.)?

    O(n^(log2(7))) = O(n^2.81)

- Do you expect this to run faster than the naive method, which has cubic complexity?

    yes, we expect it to be slightly faster then the N^3 given solution.

 */
public class MatrixMult {

    /** Function to multiply matrices
     *  Assumes A and B are square and have the same size!
     *  @param A - matrix A to multiply (first operand)
     *  @param B - matrix B to multiply (second operand)
     ***/
    public static int[][] multiply(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] R = new int[n][n];
        /** base case **/
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        else
        {
            //intialize the matrices with size/2 row and columns
            int [][] X11 = new int[n/2][n/2];
            int [][] X12 = new int[n/2][n/2];
            int [][] X21 = new int[n/2][n/2];
            int [][] X22 = new int[n/2][n/2];
            int [][] Y11 = new int[n/2][n/2];
            int [][] Y12 = new int[n/2][n/2];
            int [][] Y21 = new int[n/2][n/2];
            int [][] Y22 = new int[n/2][n/2];

            //this fucntion will divide the given matrix into first 4 halfs

            split(A,X11,0,0);
            split(A,X12,0,n/2);
            split(A,X21,n/2,0);
            split(A,X22,n/2,n/2);

            //this function will divide the given matrix into second 4 half

            split(B,Y11,0,0);
            split(B,Y12,0,n/2);
            split(B,Y21,n/2,0);
            split(B,Y22,n/2,n/2);

            //now we perform the matrix operations

            int [][] M1 = multiply(add(X11,X22), add(Y11,Y22));
            int [][] M2 = multiply(add(X21,X22), Y11);
            int [][] M3 = multiply(X11,sub(Y12,Y22));
            int [][] M4 = multiply(X22,sub(Y21,Y11));
            int [][] M5 = multiply(add(X11,X12), Y22);
            int [][] M6 = multiply(sub(X21,X11), add(Y11,Y12));
            int [][] M7 = multiply(sub(X12,X22), add(Y21,Y22));

            //adding the following matrices above M1, M2, M3, M4, M5, M6
            //into Z11, Z12, Z21, Z22

            int [][] Z11 = add(sub(add(M1,M4),M5),M7);
            int [][] Z12 = add(M3,M5);
            int [][] Z21 = add(M2,M4);
            int [][] Z22 = add(sub(add(M1, M3),M2),M6);

            //Joining the matrix in the end

            join(Z11,R,0,0);
            join(Z12,R,0,n/2);
            join(Z21,R,n/2,0);
            join(Z22,R,n/2,n/2);

        }
        /** return result **/
        return R;
    }

    /**
     * Function to subtract two matrices A and B
     ***/
    public static int[][] sub(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    /**
     * Function to add two matrices A and B
     ***/
    public static int[][] add(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    /**
     * Function to split parent matrix into child matrices.
     * Assumes C is initialized.
     * @param P - parent matrix
     * @param C - A child matrix that will get the corresponding indices of the parent
     * @param iB - start row in parent
     * @param jB - start column in parent
     ***/
    public static void split(int[][] P, int[][] C, int iB, int jB)
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    /**
     * Function to join child matrices into a parent matrix
     * Assumes C is initialized.
     * @param P - parent matrix
     * @param C - A child matrix that will provide the corresponding indices of the parent
     * @param iB - start row in parent
     * @param jB - start column in parent
     ***/
    public static void join(int[][] C, int[][] P, int iB, int jB)
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Ensure that multiplying the two matrices gives the correct result!
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};

        int[][] C = multiply(A, B);
        System.out.println(C.toString());
        //result of A * B matrices multiplied together
        System.out.println(Arrays.deepToString(C));
        System.out.println("Testing...");
        assertEquals(C[0][0], 19);
        assertEquals(C[0][1], 22);
        assertEquals(C[1][0], 43);
        assertEquals(C[1][1], 50);
        System.out.println("Success!");
    }

}
