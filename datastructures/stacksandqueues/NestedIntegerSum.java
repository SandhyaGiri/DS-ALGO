package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/nested-list-weight-sum/
public class NestedIntegerSum {
    public interface NestedInteger {
             // Constructor initializes an empty nested list.
             public NestedInteger();
        
             // Constructor initializes a single integer.
             public NestedInteger(int value);
        
             // @return true if this NestedInteger holds a single integer, rather than a nested list.
             public boolean isInteger();
        
             // @return the single integer that this NestedInteger holds, if it holds a single integer
             // Return null if this NestedInteger holds a nested list
             public Integer getInteger();
        
             // Set this NestedInteger to hold a single integer.
             public void setInteger(int value);
        
             // Set this NestedInteger to hold a nested list and adds a nested integer to it.
             public void add(NestedInteger ni);
        
             // @return the nested list that this NestedInteger holds, if it holds a nested list
             // Return empty list if this NestedInteger holds a single integer
             public List<NestedInteger> getList();
         }
    /**
     * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.

Return the sum of each integer in nestedList multiplied by its depth.

 

Example 1:


Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
Example 2:


Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
Example 3:

Input: nestedList = [0]
Output: 0

    Idea: easier solution would have been a recursive solution to do a DFS
    Current: stack based iterative solution!!
     */
    public int depthSum(List<NestedInteger> nestedList) {
        Stack<List<NestedInteger>> seenIntegers = new Stack<>();
        Stack<Integer> lastPosition = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        int sum = 0;
        int depth =1;
        if(nestedList.size() > 0){
            seenIntegers.push(nestedList);
            lastPosition.push(0);
            depths.push(depth);
        }
        while(seenIntegers.size() > 0){
            List<NestedInteger> numbers = seenIntegers.pop();
            int pos = lastPosition.pop();
            depth = depths.pop();
            while(pos < numbers.size() && numbers.get(pos).isInteger()){
                sum += depth * numbers.get(pos).getInteger();
                System.out.println(depth + "-" + numbers.get(pos).getInteger());
                pos+=1;
            }
            if(pos < numbers.size() && !numbers.get(pos).isInteger()){
                if(pos+1 < numbers.size()){
                    seenIntegers.push(numbers);
                    lastPosition.push(pos+1);
                    depths.push(depth);
                }
                seenIntegers.push(numbers.get(pos).getList());
                lastPosition.push(0);
                depths.push(depth+1);
            }
        }
        return sum;
    }
}
