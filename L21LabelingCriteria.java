/**
 * class L21LabelingCriteria
 * If two vertices are connected (distance is 1), then the difference
 * of their labelings is at least 2; if the distance of two vertices is
 * 2, then the difference of their labelings is at least 1
 * @author Hong Biao Zeng
 * @version 10/21/2021
 */
import java.util.*;
public class L21LabelingCriteria implements Criteria
{
    /**
     * simply return false since primelabeling allows repeated labels
     */
    public boolean allowRepeat(){
        return true;
    }

    private boolean meetCriteria(int l1, int l2, int distance){
        if(distance == 1)
            return Math.abs(l1-l2) >= 2;
        else if(distance == 2)
            return Math.abs(l1-l2) >= 1;
        return true;
    }

    /**
     * check if the given labeling of the given graph meets L(1, 2) criteria.
     * @param labeling The given labeling as an int array
     * @param g The given graph 
     * @return true if the labeling meet L(1, 2) criteria; false, otherwise
     */ 
    public boolean meetCriteria(int[] labeling, Graph g){
        // check for all vertex pairs, the criteria meet
        int numOfV = g.getNumOfVertices();
        for(int i = 0; i < numOfV - 1; i++){
            for(int j = i+1; j < numOfV; j++){
                if(!meetCriteria(labeling[i], labeling[j], g.distance(i+1, j+1)))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
