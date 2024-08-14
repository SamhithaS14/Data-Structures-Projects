public class QuickFindUF {
    
    // Array stores the information that two vertices are connected.
    // Vertices that are connected have the same id
    private int[] id;


    // Initializes the data structure
    // Running time, how many array accesses? n array accesses
    public QuickFindUF (int n) {
        id = new int[n]; // initialize an array of n vertices
        
        // set id of each vertex to itself
        for (int i = 0; i < n; i++) {
            id[i] = i; //array write: id[i] is on left side so its going to be written
                        //have n array writes
        }
   }
   
   // Returns the representative of the set of vertices that contains p
   // How? return id of p
   // Running time, how many array accesses? f(n) = 1 = constand O(1)
   public int find (int p) {  
    return id[p]; //1 read
   }

   // Connect vertices p and q
   // How? change all entries with id[p] to id[q]
   // Running time, how many array accesses? f(n) = 2 reads + n reads + (n-1) writes = 2n + 1
                //TILDE: 2N
                //Big O: O(n) -- linear time bc u might have to update the entire array or almost all of it
   public void union (int p, int q) {
    
    int pid = id[p];         //array read because reading value and storing in array index
    int qid = id[q];         //array read
    for (int i = 0; i < id.length; i++) {
        if (id[i] == pid) {    //array read
            id[i] = qid;       //array write at most n-1, at least 0
        }
    }
   }

   // Client code
   public static void main (String[] args) {
    QuickFindUF qf = new QuickFindUF(10);
    qf.union(0, 2);
    qf.union(3, 7);
    qf.union(7, 8);
    
    boolean conn = qf.find(3)==qf.find(8);
    System.out.println("Are 3 and 8 connected? " + conn);

   }
}
