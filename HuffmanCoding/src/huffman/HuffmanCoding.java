package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        StdIn.setFile(fileName);

        sortedCharFreqList = new ArrayList<CharFreq>();
        int[] valueArray = new int[128];
        double totalSize = 0;

        while(StdIn.hasNextChar() == true){
            totalSize += 1;
            char c = StdIn.readChar();
            int val = (int)c;

            if(val != 127){
                valueArray[val] += 1;
            } else{
                valueArray[0] +=1;
            }
        }
        
        for(int i = 0; i < valueArray.length; i++){
            if(valueArray[i] != 0){
                char character = (char)i;
                double probOcc = valueArray[i] / totalSize;
                CharFreq charfreq = new CharFreq(character, probOcc);
                sortedCharFreqList.add(charfreq);
            }
        }

        if(sortedCharFreqList.size() == 1){
            CharFreq charfreq1 = (CharFreq)sortedCharFreqList.get(0); //getting 1st obj
            char c1 = charfreq1.getCharacter();
            char c2 = (char)((int)c1 + 1); //get ascii val & add to get the next one
            CharFreq charfreq2 = new CharFreq(c2, 0);
            sortedCharFreqList.add(charfreq2);
        }
        
        Collections.sort(sortedCharFreqList);
        
	/* Your code goes here */
        
    }

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

	/* Your code goes here */

    /* 
    Queue<TreeNode> source = new Queue<TreeNode>();
    Queue<TreeNode> target = new Queue<TreeNode>();

    for(int i = 0; i < sortedCharFreqList.size(); i++){
        //TreeNode node = new TreeNode(sortedCharFreqList.get(i), null, null);
        source.enqueue(new TreeNode(sortedCharFreqList.get(i), null, null));
    }

    //tbh whats going on

    TreeNode node1 = new TreeNode();
    TreeNode node2 = new TreeNode();

    while(source.isEmpty() != true || target.size() != 1){
        if(source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()){
            node1 = source.dequeue();
       } else {
            node1 = target.dequeue();
       }

        if(source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()){
            node2 = source.dequeue();
       } else {
            node2 = target.dequeue();
       }
    }

    TreeNode newNode = new TreeNode(new CharFreq(null, (node1.getData().getProbOcc() + node2.getData().getProbOcc())), node1, node2);

    target.enqueue(newNode);
    */

    if (sortedCharFreqList.size() == 0) { //check if anything is even there
        return;
    }

    Queue<TreeNode> target = new Queue<TreeNode>();

    while (sortedCharFreqList.size() > 0 || (target.size() > 1)) { //to go in and start comparing

        TreeNode node1 = getNode(target); //goes into the queue
        TreeNode node2 = getNode(target);
        
        double totalProb = node1.getData().getProbOcc() + node2.getData().getProbOcc();
        
        if (node1.getData().getProbOcc() <= node2.getData().getProbOcc()) {
            target.enqueue(new TreeNode(new CharFreq(null, totalProb), node1, node2));
            }
            else {
                target.enqueue(new TreeNode(new CharFreq(null, totalProb), node2, node1));
            }
    }
    
    huffmanRoot = target.dequeue();
} 

//helper method
private TreeNode getNode(Queue<TreeNode> source) {
    if (sortedCharFreqList.isEmpty() && source.isEmpty()) {
        return null;
    }

    if (sortedCharFreqList.isEmpty() && !source.isEmpty()) { //dequeue from source
        return source.dequeue();
    }

    if (!sortedCharFreqList.isEmpty() && source.isEmpty()) { //create new node with the first element
        return new TreeNode(sortedCharFreqList.remove(0), null, null);
    }

    if (sortedCharFreqList.get(0).getProbOcc() <= source.peek().getData().getProbOcc()) { //if prob is lower than what's in source
        return new TreeNode(sortedCharFreqList.remove(0), null, null);
    }

    return source.dequeue();
    }

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {

	/* Your code goes here */

    encodings = new String[128];
    encoding(huffmanRoot, "");
}

private void encoding(TreeNode node, String encoded){ //populating array after getting to leaf nodes (where letters are)

    TreeNode left = node.getLeft();
    TreeNode right = node.getRight();
    if (left == null && right == null) {
        encodings[node.getData().getCharacter()] = encoded; //will go to that ASCII value in the array and put a "" there for all letters not in my code
    } 
    else {
        encoding(left,encoded + "0"); //adds a 0 to existing encoded string bc left link = 0
        encoding(node.getRight(), encoded + "1"); //adds a 1 to existing encoded string bc right link = 1
    }
}



    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);

        String intString = "";
        
        while (StdIn.hasNextChar()) {
            intString = intString + encodings[StdIn.readChar()];

        }
         writeBitString(encodedFile, intString);

    }


	/* Your code goes here */
    
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);

	/* Your code goes here */

        String encoded = readBitString(encodedFile);
        TreeNode node = huffmanRoot;
        String decode = "";

        for(int i = 0; i < encoded.length(); i++){
            if (node.getData().getCharacter() != null) {
                decode += node.getData().getCharacter();
                node = huffmanRoot;
                i--;
            }

            else if (encoded.substring(i,i+1).equals("0")) {
                node = node.getLeft();
            }
            else { //character is 1
                node = node.getRight();
            }

        }
        decode += node.getData().getCharacter();

        StdOut.print(decode);

    }

    
    

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
