
import static org.junit.Assert.*;

public class MaxHeap {

    private static int[] HeapArray;
    private int size;
    private int maxsize;
    private static final int FRONT = 1;

    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = maxsize;
        HeapArray = new int[maxsize];
        //initialize heap array to a set of numbers, rearranged a little
        for (int i = FRONT; i < HeapArray.length; i++) {
            HeapArray[i] = maxsize -  i;
        }
    }


    public int[] getHeapArray() {
        return HeapArray;
    }

    // Return the index of the parent of the node currently at pos
    private int parent(int pos)
    {
        return (pos / 2);
    }

    // Return the index of the leftchild of the node currently at pos
    private int leftChild(int pos)
    {
        return (2 * pos);
    }

    // Return the index of the rightchild of the node currently at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }


    //Function to heapify the node at position i, up to the position HeapSize
    private void maxHeapify(int i, int heapSize){
        int left = leftChild(i); //get the index of element to its left
        int right = rightChild(i);

        int largest;

        if(left <= heapSize && (HeapArray[left] > HeapArray[i])){
            largest = left;
        } else {
            largest = i;
        }

        if(right <= heapSize && (HeapArray[right] > HeapArray[largest])){
            largest = right;
        }

        if( largest != i){
            swap(i,largest);
            maxHeapify(heapSize,largest);
        }
    }

    public void buildMaxHeap() {
        for(int i = (HeapArray.length / 2) - 1; i >= FRONT; i--){
            maxHeapify(i,HeapArray.length);
        }
    }

    public void sort() {
        buildMaxHeap();
        int heapSize = HeapArray.length - 1;
        for(int i = heapSize; i >= 2; i--){
            swap(1,i);
            heapSize--;
            maxHeapify(1,heapSize);
        }
    }

    // Swap two nodes of the heap at index first second
    private void swap(int first, int seconds)
    {
        int tmp;
        tmp = HeapArray[first];
        HeapArray[first] = HeapArray[seconds];
        HeapArray[seconds] = tmp;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int SIZE = 7;
        MaxHeap heap = new MaxHeap(SIZE);
        heap.sort();
        int[] heapArr = heap.getHeapArray();
        assertEquals(heapArr[0], 0);
        assertEquals(heapArr[1], 1);
        assertEquals(heapArr[2], 2);
        assertEquals(heapArr[SIZE-1], SIZE-1);
        assertEquals(heapArr.length, SIZE);
        System.out.println("The minimum sum is: " + HeapArray[FRONT] + "+" + HeapArray[FRONT+1] + "=" + ( HeapArray[FRONT] + HeapArray[FRONT+1] ));


    }

}
