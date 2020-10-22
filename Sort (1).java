
import java.util.*;

import static org.junit.Assert.*;

public class Sort {

    /**
     * Build a random array
     * @param rand a Random object
     * @param LENGTH The range of the integers in the array
     *             will be from 0 to LENGTH-1
     * @return
     */
    private static int[] build_random_array(Random rand, int LENGTH) {
        int[] array = new int[LENGTH];
        //set index 0 to 0 for consistency with CLRS, where sorting starts at index 1
        array[0] = 0;
        for (int i = 1; i < LENGTH; i++) {
            // Generate random integers in range 0 to 999
            int rand_int = rand.nextInt(LENGTH);
            array[i] = rand_int;
        }
        return array;
    }

    /**
     * Assert the array is sorted correctly
     * @param array_unsorted The original unsorted array
     * @param array_sorted The sorted array
     */
    public static void assert_array_sorted(int[] array_unsorted, int[] array_sorted) {
        int a_sum = array_unsorted[0];
        int b_sum = array_sorted[0];
        for (int i = 1; i < array_unsorted.length; i++) {
            a_sum += array_unsorted[i];
        }
        for (int i = 1; i < array_sorted.length; i++) {
            b_sum += array_sorted[i];
            assertTrue(array_sorted[i-1] <= array_sorted[i]);
        }
        assertEquals(a_sum, b_sum);
    }

    /**
     * Insertion Sort
     * @param array unsorted array
     */
    public static void insertionSort(int[] array){
        int len = array.length;
        for(int i = 1; i < len; ++i){
            int key = array[i];
            int j = i - 1;

            while( j >= 0 && array[j] > key){
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Selection Sort
     * @param array unsorted array
     */
    public static void selectionSort(int[] array){

        int len = array.length;
        //Moving  boundary one by one of unsorted array

        for(int i = 0; i < len ; i++){

            //finding min element in unsorted array
            int min = i;
            for ( int j = i +1; j < len; j++){
                if (array[j] < array[min])
                    min = j;

            }

            //Swapping the found min with first element
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }

    /**
     * Heap Sort
     * @param array unsorted array
     */
    public static void heapSort(int[] array){

        int n = array.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // call max heapify on the reduced heap
            heapify(array, i, 0);
        }
    }

    /**
     * Heap sort helper method
     * @param arr unsorted array
     * @param n size of heap
     * @param i node index
     */
    public static void heapify(int arr[], int n, int i){
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i){
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }


    /**
     * Merge Sort
     * @param array unsorted array
     * @param left left index
     * @param right right index
     */
    public static void mergeSort(int[] array, int left, int right){

        if (left < right){
            // Find the middle point
            int mid = (left+right)/2;

            // Sort first and second halves
            mergeSort(array, left, mid);
            mergeSort(array , mid+1, right);

            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }

    /**
     * Merges two subarrays of arr[].
     * @param arr unsorted array
     * @param l left index
     * @param m mid index
     * @param r right index
     */
    public static void merge(int arr[], int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2){
            if (L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            }
            else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Quick Sort
     * @param array unsorted array
     */

    public static void quickSort(int[] array){
        int low = 0;
        int high = array.length -1;
        quickSort(array,low,high);
    }

    /**
     * Quick Sort helper method
     * @param array unsorted array
     * @param low lower index
     * @param high higher index
     */
    public static void quickSort(int[] array, int low, int high){
        if (low < high){
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(array, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(array, low, pi-1);
            quickSort(array, pi+1, high);
        }
    }

    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */

    /**
     * Quick Sort partition helper method
     * @param arr Unsorted array
     * @param low low index
     * @param high high index
     * @return
     */
    public static int partition(int arr[], int low, int high){
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /**
     * Bucket Sort
     * @param array Unsorted array
     */
    public static void bucketSort(int[] array){
        int bucketCount = array.length/2;
        int minIntValue = 0;
        int maxIntValue = array.length - 1;
        // Create bucket array
        List<Integer>[] buckets = new List[bucketCount];
        // Associate a list with each index in the bucket array
        for(int i = 0; i < bucketCount; i++){
            buckets[i] = new LinkedList<>();
        }

        // Assign integers from array to the proper bucket
        /**
         * TODO implement
         */

        for( int i = 0; i < array.length; i++){
            buckets[array[i]/2].add(array[i]);
        }

        // sort buckets
        for(List<Integer> bucket : buckets){
            Collections.sort(bucket);
        }
        int i = 0;
        // Merge buckets to get sorted array
        for(List<Integer> bucket : buckets){
            for(int num : bucket){
                array[i++] = num;
            }
        }
    }



    public static void main(String[] args){
        int NUM_RUNS = 3;
        // create instance of Random class
        Random rand = new Random();

        /////////////////////////////////////////
        int LENGTH=100;
        System.out.println("_____________INPUT "+LENGTH+"_____________");
        int[] array_100 = build_random_array(rand, LENGTH);

        //For runtime computations
        long startTime, endTime;

        double duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100_c = array_100.clone();
            startTime = System.currentTimeMillis();
            insertionSort(array_100_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
            assert_array_sorted(array_100, array_100_c);
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100_c = array_100.clone();
            startTime = System.currentTimeMillis();
            selectionSort(array_100_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
            assert_array_sorted(array_100, array_100_c);
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100_c = array_100.clone();
            startTime = System.currentTimeMillis();
            heapSort(array_100_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
            assert_array_sorted(array_100, array_100_c);
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100_c = array_100.clone();
            startTime = System.currentTimeMillis();
            mergeSort(array_100_c,0,array_100_c.length -1);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
            assert_array_sorted(array_100, array_100_c);
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100_c = array_100.clone();
            startTime = System.currentTimeMillis();
            quickSort(array_100_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
            assert_array_sorted(array_100, array_100_c);
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100_c = array_100.clone();
            startTime = System.currentTimeMillis();
            bucketSort(array_100_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
            assert_array_sorted(array_100, array_100_c);
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        /////////////////////////////////////////
        LENGTH=1000;
        System.out.println("_____________INPUT "+LENGTH+"_____________");
        int[] array_1000 = build_random_array(rand, LENGTH);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_1000_c = array_1000.clone();
            startTime = System.currentTimeMillis();
            insertionSort(array_1000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_1000_c = array_1000.clone();
            startTime = System.currentTimeMillis();
            selectionSort(array_1000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_1000_c = array_1000.clone();
            startTime = System.currentTimeMillis();
            heapSort(array_1000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_1000_c = array_1000.clone();
            startTime = System.currentTimeMillis();
            mergeSort(array_1000_c,0,array_1000_c.length -1 );
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_1000_c = array_1000.clone();
            startTime = System.currentTimeMillis();
            quickSort(array_1000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_1000_c = array_1000.clone();
            startTime = System.currentTimeMillis();
            bucketSort(array_1000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);



        /////////////////////////////////////////
        LENGTH=10000;
        System.out.println("_____________INPUT "+LENGTH+"_____________");
        int[] array_10000 = build_random_array(rand, LENGTH);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_10000_c = array_10000.clone();
            startTime = System.currentTimeMillis();
            insertionSort(array_10000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_10000_c = array_10000.clone();
            startTime = System.currentTimeMillis();
            selectionSort(array_10000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_10000_c = array_10000.clone();
            startTime = System.currentTimeMillis();
            heapSort(array_10000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_10000_c = array_10000.clone();
            startTime = System.currentTimeMillis();
            mergeSort(array_10000_c,0,array_10000_c.length -1 );
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_10000_c = array_10000.clone();
            startTime = System.currentTimeMillis();
            quickSort(array_10000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_10000_c = array_10000.clone();
            startTime = System.currentTimeMillis();
            bucketSort(array_10000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);


        /////////////////////////////////////////
        LENGTH=100000;
        System.out.println("_____________INPUT "+LENGTH+"_____________");
        int[] array_100000 = build_random_array(rand, LENGTH);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100000_c = array_100000.clone();
            startTime = System.currentTimeMillis();
            insertionSort(array_100000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100000_c = array_100000.clone();
            startTime = System.currentTimeMillis();
            selectionSort(array_100000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100000_c = array_100000.clone();
            startTime = System.currentTimeMillis();
            heapSort(array_100000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100000_c = array_100000.clone();
            startTime = System.currentTimeMillis();
            mergeSort(array_100000_c,0,array_100000_c.length -1);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100000_c = array_100000.clone();
            startTime = System.currentTimeMillis();
            quickSort(array_100000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        duration = 0;
        for (int t = 0 ; t < NUM_RUNS ; t++) {
            int[] array_100000_c = array_100000.clone();
            startTime = System.currentTimeMillis();
            bucketSort(array_100000_c);
            endTime = System.currentTimeMillis();
            duration += ((double) (endTime - startTime));
        }
        duration = duration / (double) NUM_RUNS;
        System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

    }

}

/**
 *                          Paste of the run:
  _____________INPUT 100_____________
 InsertionSort mean runtime over 3 runs is 0.0
 SelectionSort mean runtime over 3 runs is 0.0
 HeapSort mean runtime over 3 runs is 0.3333333333333333
 MergeSort mean runtime over 3 runs is 0.0
 QuickSort mean runtime over 3 runs is 0.0
 BucketSort mean runtime over 3 runs is 0.6666666666666666
 _____________INPUT 1000_____________
 InsertionSort mean runtime over 3 runs is 2.3333333333333335
 SelectionSort mean runtime over 3 runs is 3.0
 HeapSort mean runtime over 3 runs is 0.3333333333333333
 MergeSort mean runtime over 3 runs is 0.3333333333333333
 QuickSort mean runtime over 3 runs is 0.6666666666666666
 BucketSort mean runtime over 3 runs is 2.0
 _____________INPUT 10000_____________
 InsertionSort mean runtime over 3 runs is 17.0
 SelectionSort mean runtime over 3 runs is 51.333333333333336
 HeapSort mean runtime over 3 runs is 1.6666666666666667
 MergeSort mean runtime over 3 runs is 2.0
 QuickSort mean runtime over 3 runs is 1.0
 BucketSort mean runtime over 3 runs is 5.666666666666667
 _____________INPUT 100000_____________
 InsertionSort mean runtime over 3 runs is 1142.3333333333333
 SelectionSort mean runtime over 3 runs is 2569.0
 HeapSort mean runtime over 3 runs is 11.666666666666666
 MergeSort mean runtime over 3 runs is 19.0
 QuickSort mean runtime over 3 runs is 8.0
 BucketSort mean runtime over 3 runs is 17.666666666666668

 Process finished with exit code 0

 */
