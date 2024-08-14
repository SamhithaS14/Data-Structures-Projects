package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Uset his file to test restock

    Warehouse w1 = new Warehouse();
    int numProducts = StdIn.readInt();

    for(int i = 0; i < numProducts; i++){
        if(StdIn.readString().equals("add")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();

            w1.addProduct(id, name, stock, day, demand);
        }
            else {
                w1.restockProduct(StdIn.readInt(), StdIn.readInt());
            }
        }

        StdOut.println(w1);
    }
}
