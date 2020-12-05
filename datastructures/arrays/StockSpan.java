package datastructures.arrays;

import java.util.*;

// https://leetcode.com/problems/online-stock-span/
/**
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 */
public class StockSpan {
    List<Integer> stocks;
    List<Integer> spans;
    public StockSpan() {
        stocks = new LinkedList<Integer>();
        spans = new LinkedList<Integer>();
    }
    // don't look at all numbers, use information of previous spans
    // to jump backwards skipping eles which we know as 
    public int next(int price) {
        int span = 0;
        if(stocks.size() == 0){
            stocks.add(price);
            spans.add(span+1);
        } else {
            int prevIndex = stocks.size()-1;
            while(prevIndex >= 0){
                if(stocks.get(prevIndex) > price){
                    break;
                }
                span += spans.get(prevIndex);
                // System.out.println("prevIndex: " + prevIndex + ", span:" + span);
                prevIndex = prevIndex - spans.get(prevIndex);
            }
            stocks.add(price);
            spans.add(span+1);
        }
        return span + 1;
    }
}