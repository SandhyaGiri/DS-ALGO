package algorithms.dp;

import java.util.*;
import java.util.stream.*;

//https://leetcode.com/problems/minimum-cost-for-tickets/
// similar to CoinChange problem
public class MinCostTickets {

    /**
     * In a country popular for train travel, you have planned some train travelling one year in advance.
     * The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

        Train tickets are sold in 3 different ways:

        a 1-day pass is sold for costs[0] dollars;
        a 7-day pass is sold for costs[1] dollars;
        a 30-day pass is sold for costs[2] dollars.
        The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2,
        then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

        Return the minimum number of dollars you need to travel every day in the given list of days.

        

        Example 1:

        Input: days = [1,4,6,7,8,20], costs = [2,7,15]
        Output: 11
        Explanation: 
        For example, here is one way to buy passes that lets you travel your travel plan:
        On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
        On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
        On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
        In total you spent $11 and covered all the days of your travel.
        Example 2:

        Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
        Output: 17
        Explanation: 
        For example, here is one way to buy passes that lets you travel your travel plan:
        On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
        On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
        In total you spent $17 and covered all the days of your travel.
        

        Note:

        1 <= days.length <= 365
        1 <= days[i] <= 365
        days is in strictly increasing order.
        costs.length == 3
        1 <= costs[i] <= 1000

        IDEA: 
            Optimal substructure:
            mincost(j) = min( mincost(j-1) + 1 day ticket,
                              mincost(j-7) + 7 day ticket,
                              mincost(j-30) + 30 day ticket
                            ) if you have to travel on day j --> j in days array
                         otherwise mincost(j-1) as no travel needed on day j
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        int numDays = days.length;
        int lastDay = days[numDays-1];
        int[] minCosts = new int[lastDay+1];
        IntStream daysStream = Arrays.stream(days);
        // .boxed on IntStream converts it to a Stream<Integer>
        // or use mapToObj on IntStream to get Stream<Object>
        List<Integer> daysList = daysStream.boxed().collect(Collectors.toList());
        for(int i=1;i<=lastDay;i++){
            // cost to travel on ith day
            int localMinCost = 0;
            if(daysList.contains(i)){ // need to buy a ticket
                localMinCost = minCosts[i-1] + costs[0]; // 1 day ticket
                // if 7 days before is not valid, start from 0th day
                int prevIndex = i-7 >=0 ? i-7 : 0;
                if(minCosts[prevIndex] + costs[1] < localMinCost){
                    localMinCost = minCosts[prevIndex] + costs[1]; // 7 day ticket
                }
                // if 30 days before is not valid, start from 0th day
                prevIndex = i-30 >=0 ? i-30 : 0;
                if(minCosts[prevIndex] + costs[2] < localMinCost){
                    localMinCost = minCosts[prevIndex] + costs[2]; // 30 day ticket
                }
            } else {
                localMinCost = minCosts[i-1]; // just like previous day as no ticket needed
            }
            
            minCosts[i] = localMinCost;
            // System.out.println(Arrays.toString(minCosts));
        }
        
        return minCosts[lastDay];
    }
}