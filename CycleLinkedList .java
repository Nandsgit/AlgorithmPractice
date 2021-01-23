
import static org.junit.Assert.*;

/**
 * Description of solution: To find if the linked list countains a cycle or not.
 * A cycle in a linked list means that some nodes are conencted in the linked list.
 * This is an example of implementation of Floyd's Cycle Detection.
 *
 */


public class CycleLinkedList {

    /*This function returns true if given linked
    list has a cycle, else returns false. */
    public static boolean hasCycle( Node head) {

        Node slowPointer = head, fastPointer = head;
        while (slowPointer!= null && fastPointer!= null && fastPointer.getNext()!= null){
            slowPointer = slowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();
            if(slowPointer == fastPointer){
                System.out.println("Loop found in the given linked list");
                return true;
            }
        }
        return false;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Node head = new Node("start");
        Node prev = head;
        for (int i =0; i<4; i++) {
            Node temp = new Node(Integer.toString(i));
            prev.setNext(temp);
            prev=temp;
        }

        boolean b = hasCycle(head);
        System.out.println("Testing...");
        assertEquals(b, false);
        System.out.println("Success!");

        prev.setNext(head.getNext());

        b = hasCycle(head);
        System.out.println("Testing...");
        assertEquals(b, true);
        System.out.println("Success!");

    }

}
