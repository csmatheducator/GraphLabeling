
/** 
 * FibonacciLabelingCriteria
 * label each vertex with fibonacci numbers. No label repeats.
 * If two vertices share edge, then their edge is labels with 0 or 1 depends on
 * the fibonacci numbers assigned to vertices have same parity
 * for graph with n vertices, use first n+1 fibonacci nunbers to label them
 *
 * @author Hong Biao Zeng
 * @version 10/28/2021
 */
import java.util.*;
public class FibonacciLabelingCriteria implements Criteria
{
    /**
     * simply return false since Fibonacci labeling will not allow repeated labels
     */
    public boolean allowRepeat(){
        return false;
    }

    /**
     * check if the given labeling of the given graph meets Fibonacci criteria.
     * @param labeling The given labeling as an int array
     * @param g The given graph 
     * @return true if the labeling meet this criteria; false, otherwise
     */ 
    public boolean meetCriteria(int[] labeling, Graph g){
        //System.out.println(edgeLabels.length);
        int evenCount = 0;
        int oddCount = 0;
        int numOfV = g.getNumOfVertices();
        for(int i = 0; i < numOfV - 1; i++){
            for(int j = i+1; j < numOfV; j++){
                if(g.hasEdge(i+1, j+1))
                {
                    if((labeling[i]-labeling[j]) % 2 == 0)
                        evenCount++;
                    else
                        oddCount++;
                }
            }
        }

        return Math.abs(evenCount - oddCount) <= 1;
    }
}
