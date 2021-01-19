package algorithms.dp;

import java.util.*;

class Solution {

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 
     * Say you have an array for which the ith element is the price of a given stock on day i.

        If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
        design an algorithm to find the maximum profit.

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

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

    // IDEA: extend previous one for 2 transactions
    // didn't work for 19 cases, as we do only local profits, there could be a bigger
    // profit sale later on even if price tipped now
    // Ex:[1,2,4,2,5,7,2,4,9,0] Expected: 13, answered: 12 
    public int maxProfit3Old(int[] prices) {
        int maxlocalprofit = 0;
        List<Integer> localProfits = new ArrayList<>();
        if(prices.length > 0) {
            int candidateBuy = prices[0];
            for(int i=1;i<prices.length;i++) {
                if(prices[i] > candidateBuy && prices[i]-candidateBuy > maxlocalprofit){
                    maxlocalprofit = prices[i]-candidateBuy;
                } else {
                    candidateBuy = prices[i];
                    System.out.println("Buying: "+ candidateBuy + ", old profit: " + maxlocalprofit);
                    localProfits.add(maxlocalprofit);
                    maxlocalprofit=0;
                }
            }
        }
        localProfits.add(maxlocalprofit);
        System.out.println("size:" + localProfits.size() + ", last profit: " + maxlocalprofit);
       
        // sort descending and pick top 2 profits (if any)
        Collections.sort(localProfits, Comparator.reverseOrder());
        // sum top 2 values
        int maxprofit=0;
        int x=0;
        while(x<2 && x< localProfits.size()){
            int profit = localProfits.get(x);
            System.out.println(profit);
            if(x>=2){
                break;
            }
            maxprofit += profit;
            x++;
        }
        return maxprofit;
    }
    /**
     * the idea behind using a state machine.

        To find the profit each transaction has 2 states

        State 1 -> Buying
        State 2 -> Selling

        When we buying, we use the profit. So profit = profit - stock price
        When we selling, we add the earning into profit. So profit = profit + stock price

        Using the above idea we create a state machine to find profit from at most 2 transactions (4 states).

        State machine will look like:

        transaction1 :- buy1 = max(buy1, 0 - stock) // for the 1st buy profit is 0
                        sell1 = max(sell1, buy1 + stock)
        For 2nd trasaction we use the profit accumulated from 1st transaction

        transaction2 :- buy2 = max(buy2, sell1 - stock)
                        sell2 = max(sell2, buy2 + stock)
                
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length < 1) return 0;
        int buy1 = -prices[0], sell1 = 0, buy2 = -prices[0], sell2 = 0;
        for(int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     * 
     * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

        You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

        Return the maximum profit you can make.

        Example 1:
        Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
        Output: 8
        Explanation: The maximum profit can be achieved by:
        Buying at prices[0] = 1
        Selling at prices[3] = 8
        Buying at prices[4] = 4
        Selling at prices[5] = 9
        The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

        Idea: DP: At the end of the i-th day, we maintain cash, the maximum profit we could have if we did not have a share of stock,
        and hold, the maximum profit we could have if we owned a share of stock.

        To transition from the i-th day to the i+1-th day, we either sell our stock cash = max(cash, hold + prices[i] - fee) or buy a stock
        hold = max(hold, cash - prices[i]). At the end, we want to return cash. We can transform cash first without using temporary variables
        because selling and buying on the same day can't be better than just continuing to hold the stock.
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfitWithFee(int[] prices, int fee) {
        int cash =0;// profit from selling
        int hold=-prices[0]; // profit from buying or holding a stock, initally no cash
        for(int i=1;i<prices.length;i++){
            cash = Math.max(cash, hold + prices[i] - fee); // sell at current price with fee
            hold = Math.max(hold, cash - prices[i]); // buy at current price
        }
        return cash; // our profit
    }
}