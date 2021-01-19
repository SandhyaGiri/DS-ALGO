package algorithms.greedy;

import java.util.*;

public class MaxMin {

    /**
     * You will be given a list of integers, , and a single integer . You must create an array of length  from elements of  such that its unfairness is minimized. Call that array . Unfairness of an array is calculated as

        Where:
        - max denotes the largest integer in subarr.
        - min denotes the smallest integer in subarr.

        As an example, consider the array [1,4,7,2] with a k of 2. Pick any two elements, test subarr=[4,7], unfairness = 7-4 =3.

        Testing for all pairs, the solution [1,2] provides the minimum unfairness (1).

        Note: Integers in arr may not be unique.

        Function Description

        Complete the maxMin function in the editor below. It must return an integer that denotes the minimum possible value of unfairness.

        maxMin has the following parameter(s):

        k: an integer, the number of elements in the array to create
        arr: an array of integers .
        Input Format

        The first line contains an integer , the number of elements in array .
        The second line contains an integer .
        Each of the next  lines contains an integer  where .


        Output Format

        An integer that denotes the minimum possible value of unfairness.

        Sample Input 0

        7 = N
        3 = K
        10
        100
        300
        200
        1000
        20
        30
        Sample Output 0

        20
        Explanation 0

        Here ; selecting the  integers , unfairness equals

        max(10,20,30) - min(10,20,30) = 30 - 10 = 20
     * @param k
     * @param arr
     * @return
     */
    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int minUnfairness = Integer.MAX_VALUE;
        // since arr is now sorted, all close elements lie continuously.
        int start=0;
        int end=start+k-1; // sliding window
        while(end < arr.length){
            int unfairness = arr[end] - arr[start];
            if(unfairness < minUnfairness){
                minUnfairness = unfairness;
            }
            // update the window
            start += 1;
            end += 1;
        }
        return minUnfairness;
    }
}
