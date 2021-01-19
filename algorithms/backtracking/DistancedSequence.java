package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
public class DistancedSequence {
    boolean checkConstruction(int[] result, int n, int index, Set<Integer> chosen){
        if(index >= result.length){
            return true;
        }
        if(result[index] != 0 ){ // already filled
            return checkConstruction(result, n, index+1, chosen);
        }
        boolean canConstruct = false;
        for(int i=n;i>=1;i--){ // highest num first
            if(!chosen.contains(i)){ // possible match (not already taken)
                boolean tried = false;
                //System.out.println(Arrays.toString(result));
                if(i!=1 && index+i < result.length && result[index+i] == 0){ // no conflict for non 1 numbers
                    result[index] =i;
                    result[index+i] =i;
                    chosen.add(i);
                    canConstruct = true;
                    tried = true;
                } else if(i==1){
                    result[index] =i;
                    chosen.add(i);
                    canConstruct = true;
                    tried = true;
                }
                if(!tried){
                    continue;
                }
                //System.out.println("added: "+ i);
                //System.out.println(Arrays.toString(result));
                canConstruct = canConstruct && checkConstruction(result, n, index+1, chosen);
                // if rest fails - backtrack
                if(!canConstruct){
                    chosen.remove(i);
                    result[index] = 0;
                    if(i!=1 && index+i < result.length){
                        result[index+i] =0;   
                    }
                }
                //System.out.println("backtracking: "+ i);
                //System.out.println(Arrays.toString(result));
                // try next best value
            }
        }
        return canConstruct;
    }
    /**
     * Given an integer n, find a sequence that satisfies all of the following:

        The integer 1 occurs once in the sequence.
        Each integer between 2 and n occurs twice in the sequence.
        For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
        The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.

        Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.

        A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.

        

        Example 1:

        Input: n = 3
        Output: [3,1,2,3,2]
        Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
        Example 2:

        Input: n = 5
        Output: [5,3,1,4,3,5,2,4,2]

        Idea: 
     * @param n
     * @return
     */
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[(n-1) * 2 + 1];
        // backtracking
        checkConstruction(result, n, 0, new HashSet<>());
        return result;
    }
}
