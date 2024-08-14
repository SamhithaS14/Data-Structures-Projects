package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */


import java.util.*;
public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

    	// WRITE YOUR CODE HERE

        String LocateTitanInputFile = args[0];
        String LocateTitanOutputFile = args[1];

        StdIn.setFile(LocateTitanInputFile);

        int numGenerators = StdIn.readInt();

        HashMap<Integer, Double> titanMap = new HashMap<>();

        for(int i = 0; i < numGenerators; i++){
            int key = StdIn.readInt();
            double value = StdIn.readDouble();
            titanMap.put(key, value); //cannot repeat key
            //StdOut.println(titanMap.get(key)); 
                //.get returns all the values
        }

        int [][] matrix = new int[numGenerators][numGenerators];

        for(int r = 0; r < numGenerators; r++){
            for(int c = 0; c < numGenerators; c++){
                matrix[r][c] = StdIn.readInt();
            }
        }

        for(int r = 0; r < numGenerators; r++){
            for(int c = 0; c < numGenerators; c++){
            matrix[r][c] = (int)((matrix[r][c])/(titanMap.get(r)*titanMap.get(c)));
            }
        }

    //Dijkstra Algo//

        int [] minCost = new int[numGenerators];
        boolean[] DijkstraSet = new boolean[numGenerators];

        for(int i = 0; i < minCost.length; i++){
            if (i == 0){
                minCost[i] = 0;
            }
                else{
                    minCost[i] = Integer.MAX_VALUE;
                }
        }

       
    //the min cost vertex to get to from every vertex 
        for(int i = 0; i < numGenerators; i++){

            int currentSource = 0;
            int minVal = Integer.MAX_VALUE;

            for(int j = 1; j < numGenerators; j++){
                if(minCost[j] < minVal && DijkstraSet[j] == false){
                    minVal = minCost[j];
                    currentSource = j; //we at j
                }
            }

            DijkstraSet[currentSource] = true; //we visit j so mark it off

            for(int w = 0; w < numGenerators; w++){ //go through every potential neighbor
                
                if(matrix[currentSource][w] != 0){ //there is a pathway  - each neighbor
                    
                    if(DijkstraSet[w] == false && minCost[currentSource] != Integer.MAX_VALUE && minCost[w] > (minCost[currentSource] + matrix[currentSource][w])){
                        minCost[w] = minCost[currentSource] + matrix[currentSource][w];
                    }
                }
                
            }

        }

        StdOut.setFile(LocateTitanOutputFile);
        StdOut.print(minCost[numGenerators-1]);
        
        
    }
}

