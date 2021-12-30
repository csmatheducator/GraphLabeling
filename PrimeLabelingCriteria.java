/**
 * class PrimeLabelingCriteria
 * Criteria for prime labeling
 * For a graph with n vertices, prime labeling assign labels {1, 2, 3, ..., n} to 
 * vertices, every number in labels set shall be used once and only once. 
 * If two vertices form an edge, then their labels shall be coprime
 *
 * @author Hong Biao Zeng
 * @version 10/18/2021
 */
public class PrimeLabelingCriteria implements Criteria
{
    /**
     * simply return false since primelabeling will not allow repeated labels
     */
    public boolean allowRepeat(){
        return false;
    }

    private boolean meetCriteria(int l1, int l2, int distance){
        if(distance == 1)
            return LabelingTools.gcd(l1, l2) == 1;
        return true;
    }

    /**
     * check if the given labeling of the given graph meets prime labeling criteria.
     * @param labeling The given labeling as an int array
     * @param g The given graph 
     * @return true if the labeling meet the criteria; false, otherwise
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
