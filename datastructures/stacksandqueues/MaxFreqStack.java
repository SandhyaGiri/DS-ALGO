package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/maximum-frequency-stack/
public class MaxFreqStack {
    class StackElement implements Comparable<StackElement>{
        int number;
        int latestTime;
        int freq;
        StackElement(int num, int t, int freq){
            this.number = num;
            this.latestTime = t;
            this.freq=freq;
        }
        public int compareTo(StackElement other){
            return this.freq == other.freq ? Integer.compare(other.latestTime, this.latestTime) : Integer.compare(other.freq, this.freq);
        }
        public boolean equals(Object other){
            if(other instanceof StackElement){
                return this.number == ((StackElement)other).number;
            }
            return false;
        }
    }
    
    /**
     * Implement FreqStack, a class which simulates the operation of a stack-like data structure.

        FreqStack has two functions:

        push(int x), which pushes an integer x onto the stack.
        pop(), which removes and returns the most frequent element in the stack.
        If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
        

        Example 1:

        Input: 
        ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
        [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
        Output: [null,null,null,null,null,null,null,5,7,5,4]
        Explanation:
        After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

        pop() -> returns 5, as 5 is the most frequent.
        The stack becomes [5,7,5,7,4].

        pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
        The stack becomes [5,7,5,4].

        pop() -> returns 5.
        The stack becomes [5,7,4].

        pop() -> returns 4.
        The stack becomes [5,7].
        

        Note:

        Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
        It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
        The total number of FreqStack.push calls will not exceed 10000 in a single test case.
        The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
        The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

        Idea: 
     */
    class FreqStack {
        
        int timestamp;
        PriorityQueue<StackElement> maxHeap;
        Map<Integer, Integer> occurences;
        Map<Integer, List<Integer>> timestamps;
        public FreqStack() {
            timestamp = 0;
            maxHeap = new PriorityQueue<>();
            occurences = new HashMap<>();
            timestamps = new HashMap<>();
        }
        
        public void push(int x) {
            timestamp++;
            if(occurences.containsKey(x)){
                occurences.put(x, occurences.get(x)+1);
                timestamps.get(x).add(timestamp);
                StackElement ele = new StackElement(x, timestamp, occurences.get(x));
                maxHeap.remove(ele); // removes old element
                maxHeap.add(ele);
            } else {
                occurences.put(x, 1);
                List<Integer> timeList = new ArrayList<>();
                timeList.add(timestamp);
                timestamps.put(x, timeList);
                StackElement ele = new StackElement(x, timestamp, occurences.get(x));
                maxHeap.add(ele);
            }
        }
        
        public int pop() {
            StackElement top = maxHeap.poll();
            occurences.put(top.number, occurences.get(top.number)-1);
            if(occurences.get(top.number) == 0){
                timestamps.remove(top.number);
                occurences.remove(top.number);
            } else {
                List<Integer> timeList = timestamps.get(top.number);
                timeList.remove(timeList.size()-1);
                int latestTime = timeList.get(timeList.size()-1);
                StackElement ele = new StackElement(top.number, latestTime, occurences.get(top.number));
                maxHeap.add(ele);
            }
            return top.number;
        }
    }
    
    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     */
}
