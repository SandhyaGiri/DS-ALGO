package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/maximum-number-of-eaten-apples/
public class MaxApplesEaten {
    class AppleExpiry implements Comparable<AppleExpiry>{
        int numApples;
        int expiryDay;
        AppleExpiry(int numApples, int expirationDay){
            this.numApples = numApples;
            this.expiryDay = expirationDay;
        }
        public int compareTo(AppleExpiry other){
            // sorted in asc order of expiryDay
            return Integer.compare(this.expiryDay, other.expiryDay);
        }
    }
    /**
     * There is a special kind of apple tree that grows apples every day for n days. On the ith day, the tree grows apples[i] apples that will rot after days[i] days, that is on day i + days[i] the apples will be rotten and cannot be eaten. On some days, the apple tree does not grow any apples, which are denoted by apples[i] == 0 and days[i] == 0.

        You decided to eat at most one apple a day (to keep the doctors away). Note that you can keep eating after the first n days.

        Given two integer arrays days and apples of length n, return the maximum number of apples you can eat.

        Example 1:

        Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
        Output: 7
        Explanation: You can eat 7 apples:
        - On the first day, you eat an apple that grew on the first day.
        - On the second day, you eat an apple that grew on the second day.
        - On the third day, you eat an apple that grew on the second day. After this day, the apples that grew on the third day rot.
        - On the fourth to the seventh days, you eat apples that grew on the fourth day.
        Example 2:

        Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
        Output: 5
        Explanation: You can eat 5 apples:
        - On the first to the third day you eat apples that grew on the first day.
        - Do nothing on the fouth and fifth days.
        - On the sixth and seventh days you eat apples that grew on the sixth day.
        

        Constraints:

        apples.length == n
        days.length == n
        1 <= n <= 2 * 104
        0 <= apples[i], days[i] <= 2 * 104
        days[i] = 0 if and only if apples[i] = 0.

        Idea: Greedy (always eat on day if there are some unexpired apples to maximize the apples eaten.)
        Maintain a minheap (pq sorted in asc order of apple expiry day), so that we have soon to be expired apples
        at the top which can be eaten. During the start of the day, also prune the heap by removing any apples which
        have already expired (expiryDay <= day). And then add any newly generated apples with apt expiry. Finally pick
        a soon to be expired apple to be eaten.

     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        int day=0;
        int n=apples.length;
        int applesEaten = 0;
        PriorityQueue<AppleExpiry> minHeap = new PriorityQueue<>();
        while(day<n || minHeap.size() > 0){ // we can keep eating until we have some non-expired apples to be eaten.
            // prune any expired apples
            while(minHeap.size() > 0 && minHeap.peek().expiryDay <= day){
                minHeap.poll();
            }
            if(day < n){ // add any newly grown apples to our heap
                int applesGrown = apples[day];
                if(applesGrown>0){ // otherwise we will have invalid values in the heap
                    int expiresOn = day + days[day];
                    minHeap.add(new AppleExpiry(applesGrown, expiresOn));
                }  
            }
            // eat for this day
            if(minHeap.size() > 0){
                applesEaten++;
                AppleExpiry currApples = minHeap.peek();
                currApples.numApples -=1;
                if(currApples.numApples == 0){
                    minHeap.poll();
                }
            }
            day+=1;
        }
        return applesEaten;
    }
}
