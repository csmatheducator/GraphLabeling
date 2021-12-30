/**
 * class GracefulLabelingCriteria. Only for undirected graph. 
 * Label vertices using 0, 1, 2, ..., lambda, which is an injective since lambda
 * is greater than number of vertices of the graph.
 * We then label edges (uv) with abs(l(v)-l(u)), here l(v) is the label of vertex v.
 * Edge's labels shall cover all numbers from 1, 2, .., numOfEdges
 *
 * @author Hongbiao Zeng
 * @version 10/20/2021
 */
import java.util.*;
public class GracefulLabelingCriteria implements Criteria
{
    /**
     * simply return false since graceful labeling will not allow repeated labels
     */
    public boolean allowRepeat(){
        return false;
    }

     /**
     * check if the given labeling of the given graph meets graceful criteria.
     * @param labeling The given labeling as an int array
     * @param g The given graph 
     * @return true if the labeling meet graceful criteria; false, otherwise
     */ 
    public boolean meetCriteria(int[] labeling, Graph g){
        // check for all vertex pairs, the criteria meet
        int[] edgeLabels = new int[g.getNumOfEdges()/2]; 
        // our graph double count edges
        int count = 0;
        int numOfV = g.getNumOfVertices();
        for(int i = 0; i < numOfV - 1; i++){
            for(int j = i+1; j < numOfV; j++){
                if(g.hasEdge(i+1, j+1))
                {
                    edgeLabels[count++] = Math.abs(labeling[i]-labeling[j]);
                }
            }
        }
        
        Arrays.sort(edgeLabels);
        for(int i = 0; i < edgeLabels.length; i++)
            if(edgeLabels[i] != i+1)
                return false;
        return true;
    }
}
