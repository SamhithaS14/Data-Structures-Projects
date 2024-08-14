package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {

    // Use this file to test addProduct

    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse w = new Warehouse();
        int numberOfProducts = StdIn.readInt();
        
    
        for(int i = 0; i < numberOfProducts; i++){
        
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();

            w.addProduct(id, name, stock, day, demand); //does this automatically add to the right sector?
        }

        StdOut.println(w);
    }
	
}
