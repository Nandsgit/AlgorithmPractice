package a1;

/**
 * Description of solution:a function that reads numbers in from a file with one number per
 * line and outputs all the possible sums that can be formed by subsets of the numbers.
 * */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class Q2 {

    public static ArrayList<Integer> allSums1( ArrayList<Integer> arr){

        int sum = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();

        //for adding 0 as the first element of arr
        list.add(0);

        //for adding all elements in the list
        for (int i = 0; i < arr.size(); i++) {
            sum = sum + (int) arr.get(i);
            list.add(arr.get(i));
        }

        //combinations with all elements in the list
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int a,b, c = 0;
                a = (int) (arr.get(i));
                b = (int) (arr.get(j));
                c = a+b;
                list.add(c); //adds the compbinations to list
            }
        }
        list.add(sum);
        return list;
    }

    public static void main(String[] args) {

        //https://www.baeldung.com/java-file-to-arraylist
        ArrayList<Integer> result = new ArrayList<Integer>();//= Files.readAllLines(Paths.get("nums.txt"));
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/becoming1/Desktop/test1.txt"))) {
            while (br.ready()) {
                result.add(Integer.valueOf(br.readLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        ArrayList<Integer> sums = allSums1(result);
        System.out.println("Testing...");
        System.out.println(sums);
        assertEquals(sums.size(), 8);
        assertEquals(sums.contains(0), true);
        assertEquals(sums.contains(1), true);
        assertEquals(sums.contains(2), true);
        assertEquals(sums.contains(4), true);
        assertEquals(sums.contains(3), true);
        assertEquals(sums.contains(5), true);
        assertEquals(sums.contains(6), true);
        assertEquals(sums.contains(7), true);
        System.out.println("Success!");
    }

}
