
public class LinkedQueueOfStrings {
    
    private Node first; // reference to the first node in the linked list
    private Node last;  // reference to the last node in the linked list

    // private inner class only visible within LinkedQueueOfStrings
    private class Node {
        private String item;    // data
        private Node   next;    // link to next Node in the linked list
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    // Running time? O(1)
    // enqueue at the end of the list
    public void enqueue(String item) {
        
        Node oldLast = last; // reference to current last node
        
        last = new Node(); // instantiate a new Node object
        last.item = item;  // data
        last.next = null;  // next will point nowhere because it is the last node

        if ( isEmpty() ) {
            // first node to be added to the list
            first = last; 
        } else {
            oldLast.next = last;
        }           
    }
    
    // Running time? 
    // dequeue from the front of the linked list
    public String dequeue() {

        String item = first.item; // save the item to return
        first       = first.next; // update first to point to the second item
        
        if (isEmpty()) { 
            last = null;    // last node to be removed from the list
        }
        return item;
    }

    // Implement the print method
    
    public static void main (String[] args) {
        
    }
}
