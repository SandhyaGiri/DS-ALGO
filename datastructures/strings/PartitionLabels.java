package datastructures.strings;

import java.util.*;

// https://leetcode.com/problems/partition-labels/
public class PartitionLabels {
    /**
     * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

        Example 1:
        Input: S = "ababcbacadefegdehijhklij"
        Output: [9,7,8]
        Explanation:
        The partition is "ababcbaca", "defegde", "hijhklij".
        This is a partition so that each letter appears in at most one part.
        A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

        Input: S = "abayuiu"
        Output: [3,1,3]
        "aba", "y", "uiu" - max 3 partitions

        Idea: Try to greedily choose the smallest partition that includes the first letter.
        If you have something like "abaccbdeffed", then you might need to add b.
        You can use an map like "last['b'] = 5" to help you expand the width of your partition.
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> partitionLengths = new LinkedList<>();
        Map<Character, Integer> lastIndexMap = new HashMap<Character, Integer>();
        for(int i=0;i<S.length();i++){
            char c = S.charAt(i);
            if(lastIndexMap.get(c) == null){
                lastIndexMap.put(c, i);
            } else {
                lastIndexMap.put(c, Math.max(i, lastIndexMap.get(c)));
            }
        }
        int start =0, end=-1;
        Character currEndChar = null;
        int i=0;
        while(i < S.length() && start < S.length()){
            if(i > end && end != -1){
                // partition has ended
                partitionLengths.add(end - start + 1);
                start = i;
                end = -1;
            }
            if(end == -1){
                currEndChar = S.charAt(i);
                end = lastIndexMap.get(currEndChar);
            } else{
                char c = S.charAt(i);
                int currEndIndex = lastIndexMap.get(c);
                if(c != currEndChar && currEndIndex > end){ // extend the interval
                    end = currEndIndex;
                    currEndChar = c;
                }
            }
            i++;
        }
        partitionLengths.add(end - start+1); // last one
        return partitionLengths;
    }
}