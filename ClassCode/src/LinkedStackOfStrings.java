import java.util.NoSuchElementException;

// NOTE that the API (Application Programming Interface) is the same

public class LinkedStackOfStrings {
    
    private Node first = null; // front of the list
    
    // nested private class seen ONLY inside this file
    private class Node   {      
        private String item;      
        private Node next;   
    }
    
    public boolean isEmpty() {  
        return first == null;  
    }
 
    // Running time? O(1)
    // Same as AddToFront
    // Can overflow happen with this implementation?
    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }   
    
    // Running time? O(1)
    // Problems? Underflow
    public String pop() {

        // what happens when the stack is empty?
        if ( isEmpty() ) {
           throw new NoSuchElementException("Underflow - stack is empty");
        }
        String item = first.item;
        first = first.next; // first now points to the second node
        return item;   
    }

    // Client
    public static void main (String[] args) {
        LinkedStackOfStrings s = new LinkedStackOfStrings();
        s.push("jacket");
        s.push("shirt");
        s.push("pants");
        s.push("socks");
       
        String item = null;
        int n = 5;
        
        try {
            // code that might throw an exception
            while ( n > 0 ) {
                item = s.pop();
                System.out.println("Popped item: " + item);
                n--;
            } 
        } catch (NoSuchElementException exce) {
            // if exception is thrown, we catch it here
            // and handle
            System.out.println(exce.getMessage());
        }
    }
}
 