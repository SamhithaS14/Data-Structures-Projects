import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;

public class FixedCapacityStackOfStrings {
   
    private String[] s; // Stack
    private int t = 0;  // number of items on the stack, the top of the stack
    
    // constructor
    public FixedCapacityStackOfStrings(int capacity)   {  
        s = new String[capacity]; 
        // all positions will be null
    }
    
    public boolean isEmpty() {  
        return t == 0;  
    } 
   
    // 1.Running time? O(1)
    // 2.Problems? overflow
    public void push(String item) {  

        if ( t == s.length ) {
            // stack is full
            // resize: look at ResizingArrayStackOfStrings.java
        }

        s[t] = item; // 1 array write
        t += 1;
    }  
   
    // 1.Running time? O(1)
    // 2.Problem? Underflow - stack is empty
    // 3. loitering: holding a reference to an object that is no longer needed
    public String pop() {

        if ( isEmpty() ) {
            // handle the underflow
            throw new NoSuchElementException("Stack is empty");
        }

        t -= 1;
        String item = s[t]; // save the item to return to user
        s[t] = null;        // IMPORTANT to remove the reference to avoid loitering
        return item;
    }



    // CLIENT code
    public static void main(String[] args) {

        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(4);

        StdOut.print("Enter items to be pushed onto the stack: ");
        while ( !StdIn.isEmpty() ) {
            stack.push(StdIn.readString());
        }

        System.out.println("All items pushed on the stack, pop from stack");

        while ( !stack.isEmpty() ) {
            System.out.println("\t" + stack.pop());
        }

        System.out.println("Stack empty");
    }
}
