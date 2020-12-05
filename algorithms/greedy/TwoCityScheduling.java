package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/two-city-scheduling/
public class TwoCityScheduling {
    /**
     * Two City Scheduling
        There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

        Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

        

        Example 1:

        Input: [[10,20],[30,200],[400,50],[30,20]]
        Output: 110
        Explanation: 
        The first person goes to city A for a cost of 10.
        The second person goes to city A for a cost of 30.
        The third person goes to city B for a cost of 50.
        The fourth person goes to city B for a cost of 20.

        The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.

        Idea: (Greedy) sort by increasing difference between costA and costB, so elements with higher B values (hence negative diff) come first,
        then elements with higher A values come next. Now pick first N as candidates to be flown to city A and rest to city B.
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>(){
           public int compare(int[] a, int[] b){
               return Integer.compare(a[0]-a[1], b[0]-b[1]);
           } 
        });
        int costA = 0, costB =0;
        int n = costs.length /2;
        for(int i=0;i<costs.length;i++){
            if(n>0){
                costA += costs[i][0];
                n--;
            } else {
                costB += costs[i][1];
            }
        }
        return costA + costB;
    }
}