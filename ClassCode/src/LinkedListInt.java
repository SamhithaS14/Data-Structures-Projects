import java.util.NoSuchElementException;

public class LinkedListInt {
    
    //linked list of integers

    //private class: only visible inside LinkedListInt
    //class inside another class, nested classes since the Node class is so small

    private class Node{
        //instance variable of the Node class
        private int data; //data part
        private Node next; //link part

        //constructor for the node class
        Node ( int dataToInsert, Node link){
            data = dataToInsert;
            next = link;
        }
    }

    // Instance variables of the LinkedListInt class
    private Node front; // reference to the first node of the LL

    //constructor for linked list class
    LinkedListInt(){
        front = null; // empty LL
    }

    //inserts a node to the front of the LL
    //Running time? f(n) = 2 operations no matter the size of n => O(1)
    public void addToFront (int nodeData){
        Node newNode = new Node(nodeData, front); //updating data part & address to next node before put on the next
        front = newNode; //update the value of front to the next Node before
    }

    public boolean insertAfter (int target, int dataToInsert){
        Node ptr = front;
        while (ptr!=null){
            if (ptr.data == target){
                //insert dataToInsert after the object that holds target
                Node newNode = new Node(dataToInsert, ptr.next);
                ptr.next = newNode;
                return true;
            }

            //ptr.data is not equal to target
            // move ptr to the next node
            ptr = ptr.next;
        }

        //data to delete not found
        return false;
    }
     //never want to move front from the beginning because otherwise you'll lose the value of the first node
     //iterates through LL and prints all its data
     //Running time?
        // count ptr assignment -- n + 1 times 
        // f(n)= n + 1 => O(n)
        //time it takes to traverse is proportional thru LL cuz ptr has to walk through the list

     public void print(){
        Node ptr = front; // pointing to first node of the linked list
    
        while(ptr != null){
            System.out.print(ptr.data + " -> ");
            ptr = ptr.next; //updates ptr to point to the next node in the LL
        }
        System.out.println("\\");
    }

    //Search for an item in the LL
    //minimum number of assignments = 1
    public boolean search (int target){
        Node ptr = front;
        while(ptr != null){
            if (ptr.data == target){
                return true;
            }
            ptr = ptr.next; // otherwise will have an infinte loop
        }
        return false;
    }

   public void deleteFront(){

    if (front == null){ //Java exceptions
        //create an object of type exception 
        //throw it/send it back to the method that called this method aka here the main method
        throw new NoSuchElementException("The LL is empty");
    }

    front = front.next; // front points to the second node of the LL
    //the old front will be removed when the garbage collector wakes up
   }

   public boolean delete (int dataToDelete){
    Node ptr = front;
    Node prev = null;

    while (ptr!=null){
        if (ptr.data == dataToDelete){

            if (ptr == front){
                //dataToDelete is at the first node of the LL
                front = front.next;
            } else {
                prev.next = ptr.next; // remove the node pointed by ptr
            }
            return true;
            
        }
        //data to delete not found

        //moving the pointers together
            prev = ptr;
            ptr = ptr.next;
    }
            //data to delete not found
            return false;

   }

    //client: test our LinkedListInt class
    public static void main (String[] args){
        LinkedListInt lli = new LinkedListInt();
       
        try{ // try block to catch the exception in the code inside
            lli.deleteFront();
        } catch (NoSuchElementException referenceToExceptionObject){
            System.out.println("Empty linked list.");
            //for the client to handle the exception -- can do in anyway way they want

        }
       
        //lli.addToFront(9);
        //lli.addToFront(8);
        //lli.addToFront(5);
        //lli.addToFront(4);
        //lli.addToFront(3);
        //lli.addToFront(2);
        //lli.addToFront(1);
        

        //lli.print();
        // 1 -> 2 -> 3 -> 4 -> 5 -> \

        //lli.search(4);

        //lli.insertAfter(5,6);
        //lli.print();
    }

}
