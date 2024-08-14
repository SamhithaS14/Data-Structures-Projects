public class QuickUnionUF {
    
  // Array stores the information that two vertices are connected.
  // Vertices that are connected have the same root
  private int[] parent;  
    
  // Initializes the data structure
  // Running time, how many array accesses? O(n)
  public QuickUnionUF(int n)   {      
      parent = new int[n];
      for (int i = 0; i < n; i++)  {        
        parent[i] = i; 
      }
  }

  // Returns the representative of the set of vertices that contains p
  // How? returns the root
  // The root is the vertex that is its own parent
  public int find (int p) {
     while (p != parent[p]) {  //1 read
       p = parent[p]; //1 read
     }      
   return p;   
  } 
  
  // Connect vertices p and q
  // How? change the root of p to point to root of q
  // Running time, how many array accesses? F(n) =  n + n + 1 = O(n)
  public void union(int p, int q) {
     int rootP = find(p); //get the root of P
     int rootQ = find(q); //get the root of Q
     parent[rootP] = rootQ;    //root of P updates with the root of Q
                              // 1 write
  }
}
