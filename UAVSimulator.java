import java.util.Scanner;

public class UAVSimulator {
    public static void main(String[] args) throws Exception {

        System.out.println("\n          =============== UAV Simulator=============\n");
        System.out.println("Please input the location file name and UAV data file name to proceed.");

        DSAGraph graph = new DSAGraph();
        String locationFile, uavDataFile;
        Scanner sc = new Scanner(System.in);

        locationFile = sc.next();
        uavDataFile = sc.next();

        System.out.println("\nThank you. Building graph now ");

        //Reading the UAV data and location data files.
        FileIO.readUAVData(uavDataFile, graph);
        FileIO.readLocationData(locationFile, graph);

        System.out.println("\nGraph built.");

        graphFunctions(graph);

    }

    public static void graphFunctions(DSAGraph graph) throws Exception {

        DSAHashTable uavDataTable = new DSAHashTable();
        uavDataTable.hashTable(graph.getVerticeCount());
        DSAHeap heap = new DSAHeap(500);

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?\n0.Exit menu\n1.Add a location\n2.Add a edge\n3.Remove a location\n4.Display a location's data\n5.Display graph as adjacency list \n6.DFS Traversal (Display HashTable, Heap, High Risk Locations, Search location)\n7.BFS Traversal\n8.Dijkstras Algorithm");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    graph = graph.insertLocationWithPara(graph);
                    break;

                case 2:
                    graph = graph.insertEdge(graph);
                    break;

                case 3:
                    graph = graph.deleteLocation(graph);
                    break;

                case 4:
                    graph.serachLocation();
                    break;

                case 5:
                    DSAGraph.displayAsList();
                    break;

                case 6:
                    graph.dfsWrapper(graph.getFirstVertex().getLabel(), uavDataTable, heap);
                    break;

                case 7:
                     graph.bfsWrapper();
                     break;
                case 8:
                    graph.dijkstraAlgorithmWrapper();
                    break;

                default:
                    System.out.println("Please input a valid number");
            }
        }
        System.out.println("Thank you for using UAV Simulator");

    }
}







