/**
 * LabelingTools collect tools needed in labeling
 *
 * @author Hongbiao Zeng
 * @version 10/17/2021
 */
import java.util.*;
import java.io.*;

public class LabelingTools
{
    // swap two elements in an array
    private static void swap(int arr[], int left, int right)
    {
        // Swap the data
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    // reverse portion of array
    private static void reverse(int arr[], int left, int right)
    {
        // Reverse the sub-array
        while (left < right) {
            swap(arr, left++, right--);
        }
    }

    /**
     * compute gcd of a and b using Euclidean Algorithm
     * @param a an integer
     * @param b an integer
     * @return gcd(a, b) as integer
     */
    public static int gcd(int a, int b){
        int r = a % b;
        while(r != 0){
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    /**
     * arr changed to its next permuation 
     * @param arr a permuation as array
     * @return true if there is next permuation and arr changed to its next permuation
     *         false no next permutation exists, arr has no change
     */
    public static boolean nextPermutation(int arr[])
    {
        if (arr.length <= 1) // only one element, no next permuation
            return false;

        int last = arr.length - 2;

        // find the longest non-increasing suffix and find the pivot
        while (last >= 0) {
            if (arr[last] < arr[last + 1]) {
                break;
            }
            last--;
        }

        // If there is no increasing pair there is no higher order permutation
        if (last < 0)
            return false;

        int nextGreater = arr.length - 1;
        // Find the rightmost successor to the pivot
        for (int i = arr.length - 1; i > last; i--) {
            if (arr[i] > arr[last]) {
                nextGreater = i;
                break;
            }
        }
        // Swap the successor and the pivot
        swap(arr, nextGreater, last);
        // Reverse the suffix
        reverse(arr, last + 1, arr.length - 1);
        // Return true as the next_permutation is done
        return true;
    }

    /**
     * get next combination in 0, 1, 2, ..., m-1
     * These are indices for a combination in labels array
     * @param arr the array to hold current combination
     * @param max 0, 1, 2, .., m are where combination chosen from
     * @allowRepeate true if combination allow repeated number; false otherwise
     */

    public static boolean nextCombination(int[] arr, int max, boolean allowRepeate){
        // find first right most element that is not its max value yet
        int i = arr.length - 1;
        while(i >= 0 && arr[i] == max){
            i--;
            if(!allowRepeate)
                max--;
        }
        if(i == -1)
            return false; // no more max value
        arr[i] = arr[i]+1;
        for(int j = i+1; j < arr.length; j++){
            if(!allowRepeate)
                arr[j] = arr[j-1] + 1;
            else
                arr[j] = arr[i];
        }
        return true;
    }

    /**
     * initialize arr with 1, 2, .., n where n is array length
     * @param arr Array to be initialized
     */
    public static void initializeArray(int[] arr){
        for(int i = 0; i < arr.length; i++)
            arr[i] = i + 1;
    }

    /**
     * initialize arr with 0, 1, 2, .., n-1 where n is array length
     * @param arr Array to be initialized
     */
    public static void initializeArrayStarts0(int[] arr){
        for(int i = 0; i < arr.length; i++)
            arr[i] = i;
    }

    /**
     * print array
     * @param arr Array to be printed
     */
    public static void printLabeling(int[] arr){
        for(int i = 0; i < arr.length; i++)
            System.out.println("Vertex " + (i+1) + " is labeld with " + arr[i]);
    }

    /*
    public static void main(String[] args){
    int[] a = {0, 1, 2};
    for(int i = 1; i <= 64; i++){
    System.out.println("Test " + i);
    if(nextCombination(a, 4, false))
    printLabeling(a);
    }
    }
     */
    /**
     * return first n fibonacii numbers
     * @param n first n fibonacci numbers
     */ 
    public static int[] fibonaciis(int n){
        int[] arr = new int[n];
        arr[0] = 0;
        arr[1] = 1; // first two fibonacci
        for(int i = 2; i < n; i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr;
    }
}