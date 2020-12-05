package problemsolving;

import java.util.*;

public class LargestNumWithCost {
    /**
     * Form Largest Integer With Digits That Add up to Target
        Given an array of integers cost and an integer target. Return the maximum integer you can paint under the following rules:

        The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
        The total cost used must be equal to target.
        Integer does not have digits 0.
        Since the answer may be too large, return it as string.

        If there is no way to paint any integer given the condition, return "0".

        

        Example 1:

        Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
        Output: "7772"
        Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3. Then cost("7772") = 2*3+ 3*1 = 9. You could also paint "997", but "7772" is the largest number.
        Digit    cost
        1  ->   4
        2  ->   3
        3  ->   2
        4  ->   5
        5  ->   6
        6  ->   7
        7  ->   2
        8  ->   5
        9  ->   5
        Example 2:

        Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
        Output: "85"
        Explanation: The cost to paint the digit '8' is 7, and the digit '5' is 5. Then cost("85") = 7 + 5 = 12.
        Example 3:

        Input: cost = [2,4,6,2,4,6,4,4,4], target = 5
        Output: "0"
        Explanation: It's not possible to paint any integer with total cost equal to target.
        Example 4:

        Input: cost = [6,10,15,40,40,40,40,40,40], target = 47
        Output: "32211"
        

        Constraints:

        cost.length == 9
        1 <= cost[i] <= 5000
        1 <= target <= 5000

     */

    public String largestNumber(int[] cost, int target) {
        Map<Integer, List<Integer>> costValueRatioMap = new TreeMap<>();
        for(int i=0;i<cost.length;i++){
            int currcost = cost[i];
            if(costValueRatioMap.get(currcost) != null){
                costValueRatioMap.get(currcost).add(i+1);
            } else {
                List<Integer> digits = new ArrayList<>();
                digits.add(i+1);
                costValueRatioMap.put(currcost, digits);
            }
            
        }
        System.out.println(costValueRatioMap.entrySet());
        StringBuilder 
        return "";
    }
}

// successful submission -> someone else's in C++
string largestNumber(vector<int>& c, int m) {
    const int maxd = 9;
    vector<int> dp(m + 1, INT_MIN);
    dp[0] = 0;
    for(int i = 1; i <= m; ++i)
        for(int j = 0; j < maxd; ++j)
            if(i >= c[j])
                dp[i] = max(dp[i], dp[i - c[j]] + 1);
    if(dp[m] < 0)
        return "0";
    string ret = "";
    while(m > 0) {
        int pos = -1;
        for(int i = maxd - 1; i >= 0; --i)
            if(m >= c[i] && (pos == -1 || dp[m - c[i]] > dp[m - c[pos]]))
                pos = i;
        assert(pos != -1);
        ret += '1' + pos;
        m -= c[pos];
    }
    return ret;
}