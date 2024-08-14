package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Use this file to test deleteProduct

    Warehouse w2 = new Warehouse();
    int numberOfProducts = StdIn.readInt();
        
    
        for(int i = 0; i < numberOfProducts; i++){
            if(StdIn.readString().equals("add")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();

            w2.addProduct(id, name, stock, day, demand);
            }
            else{
                
                w2.deleteProduct(StdIn.readInt()); //reads id next to delete
                
            }
        }

            StdOut.println(w2);
    }
    }

