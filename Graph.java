
/**
 * A Directed Graph class. Directed Graph is represented as Adjacency list
 *
 * @author Hong Biao Zeng
 * @version Oct 17, 2021
 */

import java.util.*;
import java.io.*;

class Graph<T> {

    // Use Hashmap to store the edges in the graph
    // The keys of map are the vertices of the graph
    // The value of each key, which is a list, are the 
    // vertices that have an edge with that key
    private Map<T, List<T> > map; 

    /**
     * create an empty directed graph
     */
    public Graph(){
        map = new HashMap<>();
    }

    /**
     * create a graph from data in file. The data in file has form 
     * vertex_1 edges
     * vertex_2 edges
     * where edges are lists of vertices that vertex_i has an edge to
     * @param file The file name as String
     */
    public static Graph createGraphFromFile(String fileName) throws Exception{
        Graph result = new Graph();
        // open file to read
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        while (input.hasNextLine()){
            String[] data = input.nextLine().split("\\s+"); // by white spaces
            // add edges
            for(int i = 1; i < data.length; i++)
                result.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[i]));
        }
        return result;
    }

    /**
     * add a new vertex to the graph. This vertex, not connected to 
     * any other vertex yet
     * @param s The new vertex to add to the graph.
     */
    public void addVertex(T s)
    {
        map.put(s, new LinkedList<T>());
    }

    /**
     * add edge between two vertices
     * @param source The source vertex
     * 
     */
    public void addEdge(T source, T destination)
    {

        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
    }

    /**
     * return the number of vertices
     * @return the number of vertices of the graph
     */
    public int getNumOfVertices()
    {
        return map.keySet().size();
    }

    /**
     * return the number of edges
     * @return the number of edges of the graph
     */
    public int getNumOfEdges()
    {
        int count = 0;
        for(T key:map.keySet())
            count += map.get(key).size();
        return count;
    }

    /**
     * return maximum degree of the graph vertices
     * @return the maximum degree of vertices
     */
    public int getMaxDegree(){
        int max = 0;
        for(T v : map.keySet()){
            if(map.get(v).size() > max)
                max = map.get(v).size();
        }
        return max;
    }

    /**
     * check if the graph has vertex s
     * @param s The vertex to check
     * @return true if the graph contains given vertex; false otherwise
     */
    public boolean hasVertex(T s)
    {
        return map.containsKey(s);
    }

    /**
     * check if the graph has an edge from source vertex to destination vertex
     * @param source Beginning vertex
     * @param destination Destination vertex
     */
    public boolean hasEdge(T source, T destination)
    {
        if(!hasVertex(source)) return false;
        return map.get(source).contains(destination);
    }

    /**
     * get a neighbor of give vertex
     * @param source the vertex whose neighbors will be returned
     * @return a list of vertices that have edges from source
     */
    public List<T> getNeighbors(T source){
        return map.get(source);
    }
    // Prints the adjancency list of each vertex.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }

    /**
     * find distance between two vertices.
     * Shall use Dijkistra shortest distance algorithm. However, for this
     * project purpose, we only care about distance 1, 2, and other.
     * So it only return 1, 2, and 100 (other cases, similar to infinity)
     * @param source source vertex
     * @param destination destination vertex
     * @return distance from source to destination
     */
    public int distance(T source, T destination){
        if(hasEdge(source, destination)) return 1;
        for(T ele: map.get(source)){
            if(hasEdge(ele, destination))
                return 2;
        }
        return 100;
    }

    // A test function for this class
    public static void main(String [] args) throws Exception{
        Graph g = Graph.createGraphFromFile("circulant1.txt");
        System.out.println(g);
        System.out.println(g.distance(1, 3));
        System.out.println(g.distance(1, 4));
        System.out.println(g.distance(5, 3));
    }
}

