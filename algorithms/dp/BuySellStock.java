package algorithms.dp;

class Solution {

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 
     * Say you have an array for which the ith element is the price of a given stock on day i.

        If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

        Note that you cannot sell a stock before you buy one.
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // calculate the NGE for each element
        int days = prices.length;
        int nge[] = new int[days];
        int maxPrice = Integer.MIN_VALUE;
        for(int i=days-1;i>=0;i--) {
            if(prices[i] > maxPrice) {
                maxPrice = prices[i];
            }
            nge[i] = maxPrice;
        }
        // calculate max profit
        int maxProfit = 0;
        for(int i=0;i<days;i++) {
            int profit = nge[i] - prices[i];
            if(profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     * 
     * Say you have an array for which the ith element is the price of a given stock on day i.

        Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

        Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

        Example 1:

        Input: [7,1,5,3,6,4]
        Output: 7
        Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
                    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
        Example 2:

        Input: [1,2,3,4,5]
        Output: 4
        Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
                    Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
                    engaging multiple transactions at the same time. You must sell before buying again.
        Example 3:

        Input: [7,6,4,3,1]
        Output: 0
        Explanation: In this case, no transaction is done, i.e. max profit = 0.
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int maxlocalprofit = 0;
        int maxprofit=0;
        if(prices.length > 0) {
            int candidateBuy = prices[0];
            for(int i=1;i<prices.length;i++) {
                if(prices[i] > candidateBuy && prices[i]-candidateBuy > maxlocalprofit){
                    maxlocalprofit = prices[i]-candidateBuy;
                } else {
                    candidateBuy = prices[i];
                    maxprofit+=maxlocalprofit;
                    maxlocalprofit=0;
                }
            }
        }
        return maxprofit + maxlocalprofit;
    }
}