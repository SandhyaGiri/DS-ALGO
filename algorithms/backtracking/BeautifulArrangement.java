package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/beautiful-arrangement/
public class BeautifulArrangement {
    int numArrangements =0;

    void dfs(int position, int n, Set<Integer> currPath){
        if(position > n){
            numArrangements += 1;
            return;
        }
        for(int i=1;i<=n;i++){
            if(!currPath.contains(i)){ // not already added
                if(i%position == 0 || position%i ==0){ // valid number at this position
                    currPath.add(i);
                    dfs(position+1, n, currPath);
                    // backtrack and remove it
                    currPath.remove(i);
                }
            }
        }
    }
    /**
     * Suppose you have n integers from 1 to n. We define a beautiful arrangement as an array that is constructed by these n numbers successfully if one of the following is true for the ith position (1 <= i <= n) in this array:

        The number at the ith position is divisible by i.
        i is divisible by the number at the ith position.
        Given an integer n, return the number of the beautiful arrangements that you can construct.

        

        Example 1:

        Input: n = 2
        Output: 2
        Explanation: 
        The first beautiful arrangement is [1, 2]:
        Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
        Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
        The second beautiful arrangement is [2, 1]:
        Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
        Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
        Example 2:

        Input: n = 1
        Output: 1
        
        Constraints:

        1 <= n <= 15
        
        Idea: think of this as a search problem, at any position i, we look at all numbers 1..n and see if they fit the valid
        description. If so add it to the path and move to next pos. if we reach the n+1 pos, then it is a valid/beautiful
        arrangement - increment a counter.

     * @param n
     * @return
     */
    public int countArrangement(int n) {
        dfs(1, n, new HashSet<>());
        return numArrangements;
    }
}
