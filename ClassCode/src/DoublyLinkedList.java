//T is a placeholder for the data type
//T is an abtract data tupe
//T cannot be a primitive data type
//If you need a DLL of int, then use Integer (wrapper for int)

//**DOWNLOAD FROM HER FILES ***/
public class DoublyLinkedList {
    
    //visible only inside DoublyLinkedList
    private class Node<T>{
        T data; //data part is of type T (T can be string, integer, or double)
        Node<T> next; // link to the next note in the DLL
        Node<T> prev; //link to the previous node in the DLL
    }

    private Node first; //reference to the first node in the DLL
    private int size;

    //constructor
    public DoublyLinkedList(){
        first = null;
        size = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size; //so you know the size of the LL easier
    }

    public void addToFront(String dataToInsert){
        Node newNode = new Node();
        newNode.data = dataToInsert; //update data
        newNode.next = first; //points to oldFirst
        newNode.prev = null;

        if (!isEmpty()){ //if it is empty and u still do this, the newNode would be null
            first.prev = newNode; // oldFirst points to newFirst
        }

        first = newNode; //update the First

        size += 1; //increment when u add and decrement when u delete so you can return it to the client
    }

    public void print(){
        System.out.println("null <-");
        for(Node ptr = first; ptr != null; ptr = ptr.next){
            System.out.println(ptr.data + " <-> ");
        }
        System.out.println(" -> null");
    }

    public boolean addAfter (String dataToInsert)

    //client
    public static void main(String[] args){

        DoublyLinkedList dll = new DoublyLinkedList(); //empty DLL
        dll.addToFront("Rockstar");
        dll.addToFront("Flowers");
        dll.addToFront("Funkbot 1000");
        dll.addToFront("Funkytown");
        dll.print();

    }
}
