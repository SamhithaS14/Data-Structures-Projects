public class CircularLinkedList<T> {
    
    private class Node<T>{
        T data;
        Node<T> next;
    }

    private Node<T> last; //points to the last node in the CLL
    int size;

    CircularLinkedList(){
        last = null; //empty CLL
        size = 0;
    }

    public boolean isEmpty(){
        return last == null;
    }

    public void addToFront(T dataToInsert){

        Node<T> newNode = new Node<T>();
        newNode.data = dataToInsert;

        if(isEmpty()){
            //first node to be added in the CLL if list has no elements
            newNode.next = newNode; //new node points to itself
            last = newNode; //this is the last node
        } else{
            newNode.next = last.next;
            last = newNode;
        }

        size += 1;
    }

    public void print(){
        if (isEmpty()){
            System.out.println("Empty");
            return;
        }

        Node<T> ptr = last.next; //ptr points to the first node
        
        do{
            System.out.println(ptr.data + " -> ");
            ptr = ptr.next;
        }
        while( ptr != last.next);

        
    }

}
