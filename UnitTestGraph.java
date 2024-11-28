public class UnitTestGraph {
    public static void main(String[] args) throws Exception {

        //Testing adding a vertexes.
        System.out.println("Testing addVertex()");
        DSAGraph testGraph = new DSAGraph();
        testGraph.addVertex("A",32,45,90);
        testGraph.addVertex("B",26,50,35);
        testGraph.addVertex("C",38,55,75);
        testGraph.addVertex("D",45,30,80);
        testGraph.addVertex("E",29,40,65);
        testGraph.addVertex("F",31,20,85);
        testGraph.addVertex("G",42,60,50);
        testGraph.addVertex("H",36,25,95);
        testGraph.addVertex("I",27,50,40);
        testGraph.addVertex("J",33,35,60);
        if(testGraph.getVerticeCount()==10){
        System.out.println("Test passed !!!!!");}
        else{
            System.out.println("TEST FAILED");
        }
        System.out.println("=======================================\n");

        //Testing adding edges.
        System.out.println("Testing addEdges");
        testGraph.addEdges("A" ,"B" ,3.5);
        testGraph.addEdges("A","C" ,2.1);
        testGraph.addEdges("A" ,"E", 1.8);
        testGraph.addEdges("B" ,"C", 4.2);
        testGraph.addEdges("B" ,"F", 2.5);
        testGraph.addEdges("C" ,"D", 1.3);
        testGraph.addEdges("C" ,"G", 3.1);
        testGraph.addEdges("D" ,"H", 2.9);
        testGraph.addEdges("E" ,"F", 1.2);
        testGraph.addEdges("E" ,"G", 2.6);
        testGraph.addEdges("E" ,"I", 3.4);
        testGraph.addEdges("F" ,"H", 1.9);
        testGraph.addEdges("G" ,"H", 3.5);
        testGraph.addEdges("G" ,"J", 2.8);
        testGraph.addEdges("I" ,"J" ,2.2);
        if(testGraph.getEdgeCount()==15){
        System.out.println("Test passed!!!!!");}
        else {
            System.out.println("TEST FAILED");
        }
        System.out.println("=======================================\n");

        //Testing if the verticeList has a particular vertex.
        System.out.println("Testing hasVertex");
        if(testGraph.hasVertex("G")){
            System.out.println("Test passed!!!!!");}
        else {
            System.out.println("TEST FAILED");
        }
        System.out.println("=======================================\n");

        //Testing getting a vertex from the verticeList.
        System.out.println("Testing getVertex");
        DSAGraphVertex vertex = DSAGraph.getVertex("F");
        if(vertex.getLabel()=="F"){
            System.out.println("Test passed!!!!!");
        }
        else {
            System.out.println("TEST FAILED");
        }
        System.out.println("=======================================\n");

        //Testing getting the first vertex.
        System.out.println("Testing getFirstVertex");
        if(testGraph.getFirstVertex().getLabel() =="A")
        {
            System.out.println("Test passed!!!!!");
        }
        else {
            System.out.println("TEST FAILED");
        }
        System.out.println("=======================================\n");

        //Testing displaying the list.
        System.out.println("Testing displayAsList");
        DSAGraph.displayAsList();
        System.out.println("=======================================\n");

        //Testing Depth First Traversal - (Traversing the entire graph and visiting every vertex).
        System.out.println("Testing Depth First Search");
        String start ="A";
        DSAHeap heap = new DSAHeap(1000);
        DSAHashTable hash = new DSAHashTable();
        hash.hashTable(testGraph.getVerticeCount());
        testGraph.dfsTest(start,hash,heap);
        System.out.println("=======================================\n");

        //Testing Breadth First Search - (Finding the shortest path between vertices in a unweighted graph).
        System.out.println("Testing Breadth First Search");
        String end = "F";
        testGraph.bfsTest(start, end);
        System.out.println("=======================================\n");

        //Testing Dijkstra's Algorithm - (Finding the shortest path between vertices in a weighted graph)
        System.out.println("Testing Dijkstra's Algorithm");
        testGraph.dijkstrasAlgoTest(start,end);
        System.out.println("=======================================\n");

        //Testing removing a vertex.
        System.out.println("Testing removeVertex");
        DSAGraph.removeVertex("A",testGraph);
        DSAGraph.removeVertex("C",testGraph);
        System.out.println("Adjacency list after vertex was removed : ");
        DSAGraph.displayAsList();
        System.out.println("=======================================\n");

        System.out.println("Thank you for using DSAGraph Test Harness");

    }
}
