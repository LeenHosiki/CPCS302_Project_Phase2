package AirFreightApp;

import GraphFramework.*;

/*
 *  @authors Kawka
 */
public class Route extends Edge {

    int theRoute;
    
 //constructor
    public Route(Vertex sourcvere, Vertex desver, int weight) {
        super(sourcvere, desver, weight);
     }

     @Override
    public void displayInfo() {
        System.out.println(" --- route length: " + theRoute);
    } 

} 
