package a1;

/**
 * Description of solution: Call C(n, k)
 * The value of LaTeX: \binom{n}{k}( n k ) is the number of ways to choose
 * an (unordered) subset of k distinct elements from a set of n elements.
 * LaTeX: \binom{n}{k}( n k ) is also called the number of k-combinations of
 * k distinct elements from a set with n elements and is written as the
 * function C(n, k).
 * */

import static org.junit.Assert.*;
public class Q3 {

        // Return the number of ways to choose a subset of k distinct elements from a set of n elements
        static int recursiveFunction(int n, int k){
            if( k == 0 || k == n ){
                return 1;
            }
            return recursiveFunction( n-1,k-1) + recursiveFunction(n-1,k);
        }

        public static void main(String[] args){
            System.out.println("Testing...");
            assertEquals(recursiveFunction(14,3), 364);
            System.out.println("C(14,3) is " + recursiveFunction(14,3));
            assertEquals(recursiveFunction(14,11), 364);
            System.out.println("C(14,11) is " + recursiveFunction(14,11));
            assertEquals(recursiveFunction(18,8), 43758);
            System.out.println("C(18,8) is " + recursiveFunction(18,8));
            System.out.println("Success!");
        }
}
