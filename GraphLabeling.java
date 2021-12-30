/**
 * class GraphLabeling
 * Provide labeling for given graph with given labels and criteria
 * @author Hong Biao Zeng
 * @version 10/18/2021
 */
import java.util.*;
import java.io.*;

public class GraphLabeling
{
    private Graph g;
    private int[] labels;
    private Criteria criteria;

    /**
     * Constructor
     * @param g The graph to be labeled
     * @param labels The labels to be assigned to the graph
     * @param criteria The riteria used to assign the labels
     */
    public GraphLabeling(Graph g, int[] labels, Criteria criteria){
        this.g = g;
        this.labels = labels;
        this.criteria = criteria;
    }

    /**
     * return a String representation of first found labeling
     * @return a String representation of first found labeling
     */
    public String getOneLabeling(){
        int[] indices = new int[g.getNumOfVertices()];
        // initialize to 0, 1, 2, 3...
        for(int i = 0; i < indices.length; i++){
            if(!criteria.allowRepeat())
                indices[i] = i;
            else
                indices[i] = 0;
        }
        do{
            int[] arr = new int[indices.length];
            for(int i = 0; i < arr.length; i++){ // initialize arr with used labels
                arr[i] = labels[indices[i]];
            }
            do{
                if(criteria.meetCriteria(arr, g)){
                    String s = "One sample labeling is: \n";
                    for(int i = 0; i < arr.length; i++)
                        s += "Vertex " + (i+1) + " is labeld with " + arr[i] + "\n";
                    return s;
                }
            }while(LabelingTools.nextPermutation(arr));
        }while(LabelingTools.nextCombination(indices, labels.length-1, criteria.allowRepeat()));
        return "No labeling exist for given criteria";
    }

    /**
     * print out all labelings to given file
     * @param fileName the file name where labelings will be stored
     * @throws a general exception will be thrown if file is not found
     */
    public void printLabeling(String fileName) throws Exception{
        FileWriter fw = new FileWriter(fileName);
        int count = 0;
        int[] indices = new int[g.getNumOfVertices()];
        // initialize to 0, 1, 2, 3...
        for(int i = 0; i < indices.length; i++){
            if(!criteria.allowRepeat())
                indices[i] = i;
            else
                indices[i] = 0;
        }
        do{
            int[] arr = new int[indices.length];
            for(int i = 0; i < arr.length; i++){ // initialize arr with used labels
                arr[i] = labels[indices[i]];
                //System.out.print(arr[i]);
            }
            //System.out.println();
            do{
                if(criteria.meetCriteria(arr, g)){
                    //if(hasLabeling(arr)){
                    count++;
                    fw.write("Labeling #" + count + ":\n");
                    for(int i = 0; i < arr.length; i++)
                        fw.write("Vertex " + (i+1) + " is labeld with " + arr[i] + "\n");

                }
            }while(LabelingTools.nextPermutation(arr));
        }while(LabelingTools.nextCombination(indices, labels.length-1, criteria.allowRepeat()));

        fw.close();
    }
}
