import java.util.Iterator;

//Code based on the Practical 6 submission. Multiple new methods have been added.
public class DSAGraphVertex {
    private String label;         //Stores vertex name.
    private DSALinkedList links;  //Adjacency list with adjacent vertexes.
    private boolean visited;      //Boolean to check if vertex has been visited.
    private int tempValue;        //Stores vertex temperature.
    private int humidityValue;    //Stores vertex temperature.
    private int windSpeedValue;   //Stores vertex temperature.

    //Constructors
    public DSAGraphVertex(String inLabel, int inTempValue, int inHumidityValue, int inWindSpeedValue) {
        label = inLabel;
        links = new DSALinkedList();
        visited = false;
        tempValue = inTempValue;
        humidityValue = inHumidityValue;
        windSpeedValue = inWindSpeedValue;

    }

    public DSAGraphVertex(String inLabel) {
        label = inLabel;
        links = new DSALinkedList();
        visited = false;

    }

    //Accessors
    public String getLabel() {
        return label;
    }

    public int getTempValue() {
        return tempValue;
    }

    public int getHumidityValue() {
        return humidityValue;
    }

    public int getWindSpeedValue() {
        return windSpeedValue;
    }

    public boolean getVisited() {
        return visited;
    }


    public DSALinkedList getAdjacent() {
        return links;
    }

    //Return the distance to an adjacent vertex.
    public double getNeighbourDistance(DSAGraphVertex vertex) {

        Pair currentPair;

        Iterator iter = links.iterator();

        while (iter.hasNext()) {
            currentPair = (Pair) iter.next();

            if (currentPair.getName().getLabel().equals(vertex.getLabel())) {
                return currentPair.getDistance();
            }
        }
        return 0;
    }

    //Mutators

    //Add an Edge with its weight to the adjacency list.
    public void addEdge(DSAGraphVertex inVertex, double weight) {
        //Edge and weight are stored as one pair.

        Pair newPair = new Pair(inVertex, weight);
        links.insertLast(newPair);
    }

    public void setVisited(boolean pState) {
        visited = pState;
    }
}





