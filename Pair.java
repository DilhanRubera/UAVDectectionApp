public class Pair {

    DSAGraphVertex name;
    double distance;
    String vertexName ;
    String parent;


    //Constructor used to insert a pair of an adjacent vertex and its distance from the vertex.
    public Pair(DSAGraphVertex name, double value) {
        this.name =  name;
        this.distance = value;
    }

    //Constructor to store a vertex and its parent vertex.
    //Used in BFS and Dijkstra's Algorithm.
    public Pair(String vertexName, String parent) {
        this.vertexName = vertexName;
        this.parent = parent;
    }

    //Accessors
    public String getVertexName() {
        return vertexName;
    }
    public String getParent(){
        return parent;
    }
    public DSAGraphVertex getName() {
        return name;
    }
    public double getDistance() {
        return distance;
    }

}
