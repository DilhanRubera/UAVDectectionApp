import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class FileIO {

    //Reads the location data files and adds the edges to the graph.
    public static DSAGraph readLocationData(String fileName, DSAGraph graph) {
        String line ;
        String[] lineArray;

        FileInputStream fileStrm = null;
        InputStreamReader rdr = null;
        BufferedReader br = null;
        int lineNum = 0;

        try {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            br = new BufferedReader(rdr);

            line = br.readLine();
            while (line != null) {
                if(lineNum!= 0) {
                    lineArray = line.split(" ");

                    //Read in data to the addEdges() method.
                    graph.addEdges(lineArray[0], lineArray[1], Double.parseDouble(lineArray[2]));

                }
                lineNum++;
                line = br.readLine();
            }
            fileStrm.close();
        } catch (IOException e) {
            if (fileStrm != null) {
                try {
                    fileStrm.close();
                } catch (IOException e2) {
                }
            }

            System.out.println("Error in file reading" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return graph;
    }

    //Reads the UAV data file and adds the data of each location to its vertex.
    public static DSAGraph readUAVData(String fileName, DSAGraph graph) {
        String line ;
        String[] lineArray;

        FileInputStream fileStrm = null;
        InputStreamReader rdr = null;
        BufferedReader br = null;
        int lineNum = 0;

        try {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            br = new BufferedReader(rdr);

            line = br.readLine();

            while (line != null) {
                lineArray = line.split(" ");

                //Read the vertex data to addVertex() method.
                graph.addVertex(lineArray[0], parseInt(lineArray[1]), parseInt(lineArray[2]), parseInt(lineArray[3]));
                lineNum++;
                line = br.readLine();
            }

            fileStrm.close();

        } catch (IOException e) {
            if (fileStrm != null) {
                try {
                    fileStrm.close();
                } catch (IOException e2) {
                }
            }

            System.out.println("Error in file reading" + e.getMessage());
        }

        return graph;
    }

}
