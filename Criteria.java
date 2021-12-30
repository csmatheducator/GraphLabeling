 
 /** 
  * GraphLabeling Tools. Many labeling tools are defined in this class
  * @author Hongbiao Zeng
  * @version 10/21/2021
 */

public interface Criteria{

    /**
     * Indicates if the labels are allowed to repeat under this Criteria
     * @return true if labels are allowed to repeat; false otherwise.
     */
    boolean allowRepeat();

    /**
     * check if the given labeling of the given graph meets this criteria.
     * @param labeling The given labeling as an int array
     * @param g The given graph 
     * @return true if the labeling meet this criteria; false, otherwise
     */ 
    boolean meetCriteria(int[] labeling, Graph g);
}