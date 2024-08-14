package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Use this file to test all methods

    Warehouse w4 = new Warehouse();
    int numProd = StdIn.readInt();
       
        for(int i = 0; i < numProd; i++){
            
            String read = StdIn.readString();
            if(read.equals("add")){
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int stock = StdIn.readInt();
                int demand = StdIn.readInt();
                w4.addProduct(id, name, stock, day, demand);

            } else if (read.equals("purchase")){
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                int amount = StdIn.readInt();
                w4.purchaseProduct(id, day, amount);

            } else if (read.equals("restock")){
                w4.restockProduct(StdIn.readInt(), StdIn.readInt());
                
            } else if (read.equals("delete")){
                w4.deleteProduct(StdIn.readInt());
            }
        }
        StdOut.println(w4);

    }
}
