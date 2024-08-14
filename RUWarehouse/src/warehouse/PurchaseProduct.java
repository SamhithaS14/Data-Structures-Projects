package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Use this file to test purchaseProduct

        Warehouse w3 = new Warehouse();
        int numberProducts = StdIn.readInt();

        for(int i = 0; i < numberProducts; i++){
            if(StdIn.readString().equals("add")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();

            w3.addProduct(id, name, stock, day, demand);
            }
                else{
                    int day = StdIn.readInt();
                    int id = StdIn.readInt();
                    int amount = StdIn.readInt();

                    w3.purchaseProduct(id, day, amount);
                }
        }

        StdOut.println(w3);
    }
}
