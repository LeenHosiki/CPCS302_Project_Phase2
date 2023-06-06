package GraphFramework;

import java.util.List;

public class SingleSourceSPAlg {

    // Declare Variables
    Graph graphObj;
   String[] thePath;
    int[] thedistance;
  

    // ---------------------------------------------------------------------
        // Default constructor
    public SingleSourceSPAlg() {

    }

     public void computeDijkstraAlg(Graph Thegraph, Vertex source) {
        this.graphObj = Thegraph;
        int sourcelabel = Integer.parseInt(source.getLabel());

        // Define:
        // distance: least costly distance from parent to verticies.
        // path: frm parent to vertex.
        thedistance = new int[Thegraph.verticesNo];
        thePath = new String[Thegraph.verticesNo];
        
        for (int i = 0; i < Thegraph.verticesNo; i++) {
            Thegraph.vertices.get(i).setIsVisited(false);// Dijsktra algorithm must first intialize all vertices as NOT visited
            thedistance[i] = Integer.MAX_VALUE; // And initialize all verticies' distance as INFINITE
  }
        thedistance[sourcelabel] = 0;        // However, only distance for parent vertex is 0
        Vertex src = Thegraph.vertices.get(sourcelabel);
        thePath[sourcelabel] = src.displayInfo();
   
        for (int i = 0; i < Thegraph.verticesNo - 1; i++) {
            int min = MinimumDistanceVertexIndex(thedistance); // Use method to get minimum distance for any adjacent vertex that has NOT been visited
            Thegraph.vertices.get(min).setIsVisited(true);  // Then change status as VISITED

            List<Edge> adjList = Thegraph.vertices.get(min).getAdjList();
            for (int j = 0; j < adjList.size(); j++) {
                Edge edgeObj = adjList.get(j);
                edgeObj.setParent(src);
                String edgeInf = edgeObj.getDestination().displayInfo();
                int lab = Integer.parseInt(edgeObj.getDestination().getLabel());
                int edgeWeight = edgeObj.getWeight();
                String edgeParent = edgeObj.getParent().displayInfo();
                // Vertex must be NOT visited and is not the source
                if (!Thegraph.vertices.get(lab).getIsVisited() && edgeWeight != Integer.MAX_VALUE && edgeWeight != 0) {
                    // Find the total cost
                    int totalDistance = thedistance[min] + edgeWeight;
                    // If the cost is less than the current distance to the vertex, update the distance
                    if (totalDistance < thedistance[lab]) {
                        thedistance[lab] = totalDistance;
                        // Update the path to the vertex
                        if (thePath[min] == null) {
                            thePath[lab] = edgeParent + " – " + edgeInf;
                        } else {
                            thePath[lab] = thePath[min] + " – " + edgeInf;
                        }
                    }
                }
            }
        }
        
        // Implement a limit for Dijstra
        if (Thegraph.verticesNo < 2000) {
            displayInfo(source);
        }
    }
    
    // ---------------------------------------------------------------------
    
    // Methods
    public int MinimumDistanceVertexIndex(int[] minDis) {
        int theindx = 0;//assume to find smallest
        int minimumDistance = Integer.MAX_VALUE;

        for (int i = 0; i < graphObj.verticesNo; i++) {
            // Vertex must be NOT visited and has least cost
            if (graphObj.vertices.get(i).getIsVisited() != true && minDis[i] < minimumDistance) {
                // Take least distance
                minimumDistance = minDis[i];
                theindx = i; // index
            }
        }
        // Return index
        return theindx;
    }
    
    // ---------------------------------------------------------------------

    // Print
    public void displayInfo(Vertex source) {
        System.out.println();  
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println();  
        char sourceChar =(char) (Integer.parseInt(source.getLabel()) + 'A');
        System.out.println("The starting point location is " +sourceChar);
       System.out.println();  
       System.out.println("The routes from location " + sourceChar+ " to the rest of the locations are:");

        for (int i = 1; i < graphObj.verticesNo; i++) {
            // Verticies are not adjacent
            int m=Integer.MIN_VALUE;
            int k=Integer.MAX_VALUE;
            int dis=thedistance[i];
            String path= thePath[i];
            
            if (dis >= m && dis < 0 || dis <= k && dis > 500000) {
                System.out.println();  
                System.out.println( path+ " --- route length: no path"); //no path between source and target vertex
            } else  {
                 System.out.println();  
                System.out.println( path+ " --- route length: " + dis); // print path and weight
            }
        }
    }
}
