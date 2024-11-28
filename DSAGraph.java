import java.util.Iterator;
import java.util.Scanner;

//Code based on the Practical 6 submission. Multiple new methods have been added.
//Below is an implementation of a weighted graph.
public class DSAGraph {
    private static DSALinkedList verticeList;
    private int verticeCount;
    private int edgeCount;

    //Constructor
    public DSAGraph() {
        verticeList = new DSALinkedList();
        int verticeCount = 0;
        int edgeCount = 0;
    }


    //Method is used to add a vertex (location) with the temperature, humidity and windspeed values.
    public void addVertex(String label, int temp, int humidity, int windSpeed) {
        if (!hasVertex(label)) {
            DSAGraphVertex vertex = new DSAGraphVertex(label, temp, humidity, windSpeed);
            verticeList.insertLast(vertex);
            verticeCount++;
        }
    }

    //Method is used to add a vertex (location) without the temperature, humidity and windspeed values.
    public void addVertex(String label) {
        if (!hasVertex(label)) {
            DSAGraphVertex vertex = new DSAGraphVertex(label);
            verticeList.insertLast(vertex);
            verticeCount++;
        }
    }

    //Used to add an edge with the weight between the two vertexes
    public void addEdges(String label1, String label2, double weight) throws Exception {
       //If the vertex is not already in the graph (not in the verticeList) , the vertex is added to the graph.
        if (!hasVertex(label1)) {
            addVertex(label1);
            verticeCount++;
        }
        if (!hasVertex(label2)) {
            addVertex(label2);
            verticeCount++;
        }

        DSAGraphVertex vertex1 = getVertex(label1);
        DSAGraphVertex vertex2 = getVertex(label2);

        //Vertexs are added to their respective adjacency lists.
        if (vertex1 != null && vertex2 != null) {
            vertex1.addEdge(vertex2, weight);
            vertex2.addEdge(vertex1, weight);
            edgeCount = edgeCount + 1;
        }
    }

    //Method to check if a particular vertex is present in the graph (verticeList).
    public boolean hasVertex(String label) {
        DSAGraphVertex vertex ;
        boolean found = false;
        var iter = verticeList.iterator();
        while (iter.hasNext()) {
            vertex = (DSAGraphVertex) iter.next();
            if (vertex.getLabel().equals(label)) {
                found = true;
            }
        }
        return found;
    }

    //Method is used to get a vertex from the verticeList.
    public static DSAGraphVertex getVertex(String label) throws Exception {
        DSAGraphVertex vertex ;
        DSAGraphVertex returnVertex = null;

        var iter = verticeList.iterator();
      try {
          while (iter.hasNext()) {
              vertex = (DSAGraphVertex) iter.next();
              if (vertex.getLabel().equals(label)) {
                  returnVertex = vertex;
              }

          }
      }
      catch (Exception e){
          throw new Exception("Vertex not found");
      }
        return returnVertex;

      }

      //Method used to get the first vertex of the verticeList.
    public DSAGraphVertex getFirstVertex() {
        DSAGraphVertex vertex ;
        Iterator iter = verticeList.iterator();
        vertex = (DSAGraphVertex) iter.next();
        return vertex;
    }

    //Method to get the number of vertices in the verticeList.
    public int getVerticeCount() {
        return verticeCount;
    }

    //Method to get the number of edges.
    public int getEdgeCount() {return edgeCount;}

    //Method to display the graph as an Adjacency List.
    public static void displayAsList() {
        System.out.println("\nGraph as an Adjacency List");

        //Iterating through verticeList
        var iter = verticeList.iterator();
        while (iter.hasNext()) {
            DSAGraphVertex vertex = (DSAGraphVertex) iter.next();
            System.out.print("Vertex : "+ vertex.getLabel() );
            System.out.println();


            //Iterating through the adjacency vertexes of the vertex.
            var iter2 = vertex.getAdjacent().iterator();
            while (iter2.hasNext()) {
                Pair adjacent = (Pair)iter2.next();
                System.out.print(adjacent.getName().getLabel() + " ");

            }
            System.out.println();
        }
    }

    //Method used to enter a location with a users input.
    // Method is called in the menu when the use wishes to add a new location.
    public DSAGraph insertLocationWithPara(DSAGraph graph){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the name of the location:");
        String name = sc.nextLine();

        System.out.println("Enter the Temperature:");
        int temp = sc.nextInt();

        System.out.println("Enter the Humidity:");
        int humidity = sc.nextInt();

        System.out.println("Enter the Wind Speed:");
        int windSpeed = sc.nextInt();

        addVertex(name, temp, humidity, windSpeed);
        System.out.println("Location added!");

        return graph ;
    }

    //Method used to enter an ege with a users input.
    // Method is called in the menu when the use wishes to add a new edge.
    public DSAGraph insertEdge(DSAGraph graph) throws Exception {

        insertLocationWithPara(graph);
        insertLocationWithPara(graph);

        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the name of the first location:");
        String name1 = sc.nextLine();
        System.out.println("Enter the name of the second location:");
        String name2 = sc.nextLine();
        System.out.println("Enter the distance between the two locations:");
        double distance = sc.nextInt();
        addEdges(name1, name2, distance);
        System.out.println("Edge added");
        return graph;
    }

    //Method is used to display the data of a particular location based on the users input.
    //Method is called in the menu when a user wishes to display the data of a particular location.
    public void serachLocation() throws Exception {
        System.out.println("\nEnter the location to search:");
        Scanner sc = new Scanner(System.in);
        String label = sc.next();

        DSAGraphVertex vertex = getVertex(label);
        System.out.println("Displaying location data");
        System.out.println("Name of location:"+ vertex.getLabel() + "\nTemperature: "+vertex.getTempValue()+"\nHumidity:"+vertex.getHumidityValue()+"\nWindSpeed:" + vertex.getWindSpeedValue());
        System.out.println("Adjacent vertexes of "+ label);
        var iter = vertex.getAdjacent().iterator();
        while (iter.hasNext()){
            Pair pair = (Pair)iter.next();
            System.out.println(pair.getName().getLabel());
        }
    }

    //Method to delete a location from a users input.
    //Method is called in the menu when a user wants to delete a location.
    public DSAGraph deleteLocation(DSAGraph graph) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the name of the location to delete:");
        String name = sc.nextLine();
        DSAGraph returnGraph = removeVertex(name,graph);
        graph = returnGraph;
        System.out.println("Location deleted");
        return graph ;
    }

    //A helper method for deleteLocation().
    //Method is used to remove a vertice from the verticeList and remove the vertex from the adjacency lists of its adjacent vertexes.
    public static DSAGraph removeVertex(String label, DSAGraph graph) throws Exception {

        //The adjacent vertexes of the vertex to be deleted are removed first.
        DSAGraphVertex vertex = getVertex(label);
        var iter = vertex.getAdjacent().iterator();

        //Iterate through the vertex's adjacency list.
        while(iter.hasNext()){

            Pair tempPair= (Pair) iter.next();

            //Iterate through the verticeList to find the vertices that are adjacent to the vertex to be deleted.
            var iter2 = verticeList.iterator();
            while(iter2.hasNext()){

                DSAGraphVertex currVertex = (DSAGraphVertex) iter2.next();

                if(currVertex.getLabel().equals(tempPair.name.getLabel()))
                {
                    //Iterate through adjacency list of the adjacent vertices
                    var iter3 = currVertex.getAdjacent().iterator();

                    while (iter3.hasNext()){

                        Pair tempPair2 = (Pair) iter3.next();

                        //If the adjacent vertex is equal to the vertex to be deleted,the vertex is removed.
                        if(tempPair2.getName().getLabel().equals(vertex.getLabel()))
                        {
                            currVertex.getAdjacent().remove(tempPair2);
                        }
                    }
                }
            }
        }

        //Finally iterate through the verticeList and delete the vertex.
        var iter4 = verticeList.iterator();
        while(iter4.hasNext()){
            DSAGraphVertex currentVertex = (DSAGraphVertex) iter4.next();
            if(currentVertex.getLabel().equals(label))
            {
                verticeList.remove(currentVertex);
            }

        }

        return graph;
    }

    //Wrapper method for dfsUtil.
    //Calls dfsUtil and once DFS traversal is done it displays a menu to display the hashtable, heaptable and high risk areas.
    public void dfsWrapper(String label, DSAHashTable uavTable, DSAHeap heap) throws Exception {
        boolean running= true;
        Scanner sc= new Scanner(System.in);
        DSAGraphVertex vertex = getVertex(label);

        dfsUtil(vertex, uavTable, heap);

        while(running) {
            System.out.println("\nWould you like to display 1) UAV data (Hash) table and 2) heap table? 3)High risk areas 4)Look up a location in the hash table");
            int input = sc.nextInt();
            switch (input){
                case 1:
                    uavTable.displayHashTable();
                    break;
                case 2:

                    heap.displayHeap();
                    break;

                case 3:
                    heap.displayHighRisk();
                    break;
                case 4:
                    System.out.println("\nWhat is the location you want to look up? ");
                    String answer = sc.next();
                    DSAGraphVertex vertex2 = (DSAGraphVertex) uavTable.get(answer);
                    System.out.println("Vertex : "+ vertex2.getLabel()+"\nTemperature : "+vertex2.getTempValue()+"\nHumidity : "+vertex2.getHumidityValue()+"\nWind Speed "+ vertex2.getWindSpeedValue());

                default:
                    running= false;

            }
        }
        System.out.println("Thank you for using DFS Traversal!");
    }
    //Helper method used in UnitTestGraph to test Depth First Search.
    public void dfsTest(String label, DSAHashTable uavTable, DSAHeap heap) throws Exception{
        DSAGraphVertex vertex = getVertex(label);
        dfsUtil(vertex, uavTable, heap);
    }

    //Recursive Method used to carry out DepthFirstSearch to visit all vertexes.
    //Once it visits a vertex it extracts its data and calculates the locations risk value.
    //Then inserts the location to the hashtable and heap.
    private void dfsUtil(DSAGraphVertex vertex, DSAHashTable uavDataTable, DSAHeap heap) {
        int riskValue ;
        Pair adj;


        vertex.setVisited(true);

        System.out.println("Vertex "+vertex.getLabel()+" was visited.");

        //Inserts the vertex into the hashtable once a vertex is visited.
        uavDataTable.put(vertex.getLabel(), vertex);
        System.out.println("Data of Vertex "+vertex.getLabel()+" was put in the Hash Table.");

        //Calculates the risk value of that location and inserts it into the heap. The risk value acts as the priority here.
        riskValue = calculateTempRisk(vertex) + calculateHumidityRisk(vertex) + calculateWindRisk(vertex);
        System.out.println("Risk value of "+vertex.getLabel()+" is " + riskValue+". Added to the Heap.");
        System.out.println("\n");

        //Risk value and vertex is added to the heap.
        heap.add(riskValue, vertex.getLabel());

        //Iterates through the adjacency list of a vertex.
        DSALinkedList adjacencyList = vertex.getAdjacent();
        var iter = adjacencyList.iterator();

        while (iter.hasNext()) {
            adj = (Pair) iter.next();
            DSAGraphVertex temp =  adj.getName();
            if (!temp.getVisited()) {
                dfsUtil(adj.getName(), uavDataTable, heap);
            }
        }

    }

    //Helper method to calculate the risk value using the temperature of a vertex.
    public int calculateTempRisk(DSAGraphVertex vertex) {
        int tempRisk = 0;

        if ((vertex.getTempValue()) > 40) {
            tempRisk = 3;
        } else if (vertex.getTempValue() >= 33 && vertex.getTempValue() <= 40) {
            tempRisk = 2;
        } else if (vertex.getTempValue() >= 25 && vertex.getTempValue() <= 32) {
            tempRisk = 1;
        }
        return tempRisk;
    }

    //Helper method to calculate the risk value using the humidity of a vertex.
    public int calculateHumidityRisk(DSAGraphVertex vertex) {
        int humidityRisk = 0;

        if (vertex.getHumidityValue() >= 50) {
            humidityRisk = 1;

        } else if (vertex.getHumidityValue() >= 31 && vertex.getHumidityValue() <= 40) {
            humidityRisk = 2;
        } else if (vertex.getHumidityValue() <= 30) {
            humidityRisk = 3;
        }
        return humidityRisk;
    }

    //Helper method to calculate the risk value using the wind speed of a vertex.
    public int calculateWindRisk(DSAGraphVertex vertex) {
        int windRisk = 0;

        if (vertex.getWindSpeedValue() <= 40) {
            windRisk = 1;

        } else if (vertex.getWindSpeedValue() >= 41 && vertex.getWindSpeedValue() <= 55) {
            windRisk = 2;
        } else if (vertex.getWindSpeedValue() > 55) {
            windRisk = 3;
        }
        return windRisk;
    }

    //Wrapper method for bfsUtil.
    //Used to ask the user to enter the start and end location and conduct BreadthFirstSearch thus displaying the shortest path between them.
    public void bfsWrapper() throws Exception {
        Scanner sc= new Scanner(System.in);
        System.out.println("Determine the shortest path between 2 locations using BFS");

        String start = sc.next();
        String end = sc.next();

        DSALinkedList result = bfsUtil(start, end);
        System.out.println("Shortest path between location "+start+" and location "+end);
        result.display();

    }

    //A helper method used in UnitTestGraph to test Breadth First Search.
    public void bfsTest(String startNodeName, String endNodeName) throws Exception {
        DSALinkedList result = bfsUtil(startNodeName, endNodeName);
        System.out.println("Shortest path between location "+startNodeName+" and location "+endNodeName);
        result.display();
    }

    //Method is used to conduct BreadthFirstSearch and determine the shortest path.
    //Code is based on zhaohuabing's GitHub repository "shortest-path-unweighted-graph-bsf-java".
    //Link - https://github.com/zhaohuabing/shortest-path-unweighted-graph-bsf-java
    //This BFS method does not take into consideration the weight between the edges.
    private DSALinkedList bfsUtil(String startNodeName, String endNodeName) throws Exception {
        DSALinkedList parents = new DSALinkedList();
        DSALinkedList temp= new DSALinkedList();
        DSALinkedList visited = new DSALinkedList();

        DSAGraphVertex start = getVertex(startNodeName);

        temp.insertLast(start);

        //The pair class is used here to store a vertex and its parent vertex.
        Pair newPair = new Pair(startNodeName,null);

        //The pair is inserted to a list.
        parents.insertFirst(newPair);

        //Visited vertexes are added to a list.
        visited.insertLast(start);

        while(temp.getCount()>0){

            DSAGraphVertex currentVertex = (DSAGraphVertex) temp.peekFirst();

            //Iterate through the adjacent vertexes.
            DSALinkedList neighbours = currentVertex.getAdjacent();
            var iter = neighbours.iterator();

            while(iter.hasNext()){

                Pair neighbour =(Pair) iter.next();
                DSAGraphVertex vertex = neighbour.getName();
                String nodeName = neighbour.getName().getLabel();

                if(visited.containsVertex(vertex)){
                    continue;
                }
                else
                {
                    temp.insertLast(neighbour.getName());
                    Pair pair2 = new Pair(nodeName,currentVertex.getLabel());

                    //If the vertex is not visited add the node to the parents and visited list.
                    parents.insertFirst(pair2);
                    visited.insertFirst(vertex);

                    //If the end node is reached return the BFS path to the bfsWrapper() method.
                    if(nodeName.equals(endNodeName)){
                        return getBfsPath(parents,endNodeName);
                    }
                }
            }

            temp.removeFirst();

        }
        return null;
    }

    //Helper method of bfsUtil().
    //Method used to get the list with the vertexes in the shortest path.
    public DSALinkedList getBfsPath(DSALinkedList parents, String endNodeName){
        DSALinkedList path = new DSALinkedList();
        String vertex = endNodeName;

        while(vertex!= null){
            path.insertFirst(vertex);
            String parent = getBfsParents(parents, vertex);
            vertex = parent;
        }
        return path;
    }

    //Helper method of getBfsPath().
    //Used to return the parent vertex of a vertex.
    public String getBfsParents(DSALinkedList parents, String endNodeName) {
        var iter = parents.iterator();

        while (iter.hasNext()) {
            Pair temp = (Pair) iter.next();
            if (temp.getVertexName().equals(endNodeName)) {
                return  temp.getParent();
            }
        }
        return endNodeName;
    }
    //Wrapper for dijkstraAlgo() method.
    //Asks the user how many UAVs are present and the start and end location of each UAV.
    public void dijkstraAlgorithmWrapper() throws Exception {
        Scanner sc= new Scanner(System.in);
        int num = 1;

        //Asks user amount of UAVs are present.
        System.out.println("How many UAVs are present?");
        int uavNum = sc.nextInt();

        while(uavNum>0) {

            //Asks user the start and end locations.
            System.out.println("Determine the shortest path between two locations using Dijkstras Algorithm.\nEnter the start location and end location of UAV "+num );
            String start = sc.next();
            String end = sc.next();

            System.out.println("\nUAV traversal started");

            //Displays the shortest path between the two locations.
            DSALinkedList result = dijkstraAlgo(start, end);
            System.out.println("Shortest path between " + start + " and " + end + " is:");
            result.display();
            System.out.println("\n");

            uavNum-=1;
            num +=1;
        }
        System.out.println("All UAVs have finished traversals.");
    }

    //A helper method used in UnitTestGraph to test Dijkstra's Algorithm.
    public void dijkstrasAlgoTest(String start, String end) throws Exception {
        DSALinkedList result = dijkstraAlgo(start, end);
        System.out.println("Shortest path between " + start + " and " + end + " is:");
        result.display();
    }

    //Method is used to find the shortest path between two locations using Dijkstra's Algorithm.
    //Code based on zhaohuabing's GitHub repository "shortest-path-weighted-graph-dijkstra-java".
    //Link - https://github.com/zhaohuabing/shortest-path-weighted-graph-dijkstra-java
    //Algorithm considers the weight of each edge when determining the shortest path.
    private DSALinkedList dijkstraAlgo(String startNodeName, String endNodeName) throws Exception {
        DSALinkedList parents = new DSALinkedList();
        DSALinkedList visited = new DSALinkedList();
        DSAHeap priorityQueue = new DSAHeap(1000);

        //Add start node to the priority queue.
        Pair start = new Pair(startNodeName, null);
        priorityQueue.add(0,start);

        while (priorityQueue.getHeapCount() > 0) {

            //Extracts the vertex with the shortest distance to the start vertex from the priority queue.
            DSAHeapEntry currentPathNodeEntry =  priorityQueue.extractMin();
            Pair currentPathNode = (Pair) currentPathNodeEntry.getValue();
            double priority = currentPathNodeEntry.getPriority();

            DSAGraphVertex currentVertex = getVertex(currentPathNode.getVertexName());

            if(!visited.containsVertex(currentVertex)){

                parents.insertLast(currentPathNode);
                visited.insertLast(currentVertex);

                //If the end vertex is reached return the shortest path.
                if (currentPathNode.getVertexName().equals(endNodeName)) {
                    return getPath(parents, endNodeName);
                }

            //Iterate through the adjacency list of the vertex.
                DSALinkedList neighbours = currentVertex.getAdjacent();
                var iter = neighbours.iterator();

                while (iter.hasNext()) {

                    Pair neighbour = (Pair) iter.next();

                    DSAGraphVertex neighbourVertex =  neighbour.getName();
                    double neighbourDistance = currentVertex.getNeighbourDistance(neighbourVertex);

                    //Add the distance of the adjacent vertex to the total distance from the root.
                    double distance2root = priority + neighbourDistance;
                    Pair newNode = new Pair(neighbourVertex.getLabel(), currentPathNode.getVertexName());

                    //Add the total distance from the root to the priority queue.
                    priorityQueue.add(distance2root, newNode);
                }
            }
        }
        return null;
    }


    //Helper method for dijkstraAlgo().Extracts the vertexes from the shortest path list.
    public DSALinkedList getPath(DSALinkedList parents, String endNodeName) {
        DSALinkedList path = new DSALinkedList();
        String node = endNodeName;

        while (node != null )
        {
            path.insertFirst(node);
            node = getParent(parents, node);
        }
        return path;
    }

    //Helper method for getPath().Returns the parent vertec of a vertex.
    public String getParent(DSALinkedList parents, String endNodeName){
        var iter = parents.iterator();
        while(iter.hasNext()){
            Pair pair = (Pair) iter.next();
            if(pair.getVertexName().equals(endNodeName)){
                return (String) pair.getParent();
            }
        }
        return endNodeName;
    }


}







