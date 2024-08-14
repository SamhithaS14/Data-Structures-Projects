import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class DrunkenTurtles {
    
    public static void main (String[] args) {

        // t = # of turtles
        // s = # of steps

        int numberOfTurtles = 100;
        int   numberOfSteps = 200;
        double     stepSize = 0.02;

        // allocate enough space for numberOfTurtles in an array
        Turtle[] turtles = new Turtle[numberOfTurtles];  

        // running time? 
        //tilde notation : t
        // big o : O(t)

        // instantiate the turtles, each turtle is one object
        // place each turtle in a random spot, put it in the array
        for ( int i = 0; i < turtles.length; i++ ) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            // color is R G B
            Color c = new Color(StdRandom.uniform(256),StdRandom.uniform(256),StdRandom.uniform(256)); //instantiate a color object
            turtles[i] = new Turtle (x, y, 0.0, c); // creating an object of type Turtle
        }

        StdOut.println("Number turtles " + numberOfTurtles); // come back to it next class

        // running time? 
        //tilde notation : 2ts
        //big o : O(ts)

        // make each turtle take one step at a time for a total of numberOfSteps
        for (int s = 1; s < numberOfSteps; s++ ) {            
            // traverse entire array to make each turtle turn left by a random angle and all turtles take one step
            for (int i = 0; i < turtles.length; i++ ) {
                double delta = StdRandom.uniform(0.0, 360.0); // angle the turtle is going to turn left by
                turtles[i].turnLeft(delta);
                turtles[i].moveForward(stepSize);
            }
        }
    }
}
