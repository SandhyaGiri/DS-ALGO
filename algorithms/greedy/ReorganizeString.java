package algorithms.greedy;

import java.util.*;
// https://leetcode.com/problems/reorganize-string/
public class ReorganizeString {
    class CharCount implements Comparable<CharCount>{
        char c;
        int numOccurences;
        CharCount(char c, int counts){
            this.c=c;
            this.numOccurences = counts;
        }
        public int compareTo(CharCount other){
            // sorted desc order of count
            return Integer.compare(other.numOccurences, this.numOccurences);
        }
    }
    /**
     * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

        If possible, output any possible result.  If not possible, return the empty string.

        Example 1:

        Input: S = "aab"
        Output: "aba"
        Example 2:

        Input: S = "aaab"
        Output: ""

        (Similar to Task Scheduler with cooldowntime=1 - but consider top two entries in the heap for two chars which are not equal.)

        Idea: a greedy approach that tries to write the most common letter (that isn't the same as the previous letter written) will work.
        A heap is a natural structure to repeatedly return the current top 2 letters with the largest remaining counts. We pop the top two elements from the heap 
        and write their chars and decrement the counts and add them back to the heap. Now finally there might be one char in the heap, whose count must be 1
        for a valid reorder - otherwise return "" string.

        Time: O(n * log|distinct_chars|)
        Space: O(|distinct_chars|)
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        Map<Character, Integer> countMap = new HashMap<>();
        for(int i=0;i<S.length();i++){
            char currChar = S.charAt(i);
            countMap.put(currChar, countMap.getOrDefault(currChar, 0)+1);
        }
        PriorityQueue<CharCount> maxHeap = new PriorityQueue<>();
        countMap.entrySet().forEach(entry -> maxHeap.add(new CharCount(entry.getKey(), entry.getValue())));
        //System.out.println(maxHeap.size()+ "-" + S.length());
        StringBuilder result = new StringBuilder();
        while(maxHeap.size() >= 2){
            CharCount firstChar = maxHeap.poll();
            CharCount secondChar = maxHeap.poll();
            result.append(firstChar.c);
            result.append(secondChar.c);
            firstChar.numOccurences -=1;
            secondChar.numOccurences -=1;
            if(firstChar.numOccurences > 0){
                maxHeap.add(firstChar);
            }
            if(secondChar.numOccurences > 0){
                maxHeap.add(secondChar);
            }
        }
        
        if(maxHeap.size() > 0){
            if(maxHeap.peek().numOccurences > 1){
                return "";
            }
            result.append(maxHeap.poll().c);
        }
        return result.toString();
    }
}

// https://leetcode.com/problems/rearrange-string-k-distance-apart/
class ReorganizeStringII {
    class CharCount{
        char c;
        int count;
        int scheduledPos;
        CharCount(char c, int count){
            this.c=c;
            this.count = count;
        }

    }
    
    class CountComparator implements Comparator<CharCount>{
        public int compare(CharCount first, CharCount second){
            return Integer.compare(second.count, first.count);
        }
    }
    class PositionComparator implements Comparator<CharCount>{
        // asc order of time, if two eles have same time, then the one
        // which was added second will come behind the first one.
        public int compare(CharCount first, CharCount second){
            return first.scheduledPos == second.scheduledPos ? Integer.compare(second.count, first.count): Integer.compare(first.scheduledPos, second.scheduledPos);
        }
    }
    
    /**
     * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

        All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

        Example 1:

        Input: s = "aabbcc", k = 3
        Output: "abcabc" 
        Explanation: The same letters are at least distance 3 from each other.
        Example 2:

        Input: s = "aaabc", k = 3
        Output: "" 
        Explanation: It is not possible to rearrange the string.
        Example 3:

        Input: s = "aaadbbcc", k = 2
        Output: "abacabcd"
        Explanation: The same letters are at least distance 2 from each other.

        Idea: Very similar to Task scheduler (here pos instead of time) and we don't compute time taken but
        acutually form the string.

        1. compute char counts and sort them desc order of counts
        2. Now schedule the distinct chars with their counts and a tentative pos starting from 0 (0-most frequent char)
        3. Heap is sorted in asc order of pos and if two chars have same pos, then we sort them by desc order of count.
        4. for each poll from heap, its scheduled pos can be <= curr pos, then append to result otherwise, we can only
        add this char after some gaps -> not good. Add it back on the heap if count > 0

     * @param s
     * @param k
     * @return
     */
    public String rearrangeString(String s, int k) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char currChar = s.charAt(i);
            charCountMap.put(currChar, charCountMap.getOrDefault(currChar, 0)+1);
        }
        List<CharCount> entries = new ArrayList<>();
        charCountMap.entrySet().forEach(entry -> entries.add(new CharCount(entry.getKey(), entry.getValue())));
        Collections.sort(entries, new CountComparator()); // sorted by natural order defined by comparable
        
        // position based ordering using pq
        // heap has only distinct chars at any time,
        // each time they are removed and added newly (with updated count and pos)
        PriorityQueue<CharCount> heap = new PriorityQueue<>(new PositionComparator());
        int position = 0;
        for(CharCount cc: entries){
            cc.scheduledPos = position;
            heap.add(cc);
            position += 1;
        }
        StringBuilder result = new StringBuilder();
        position = 0;
        while(!heap.isEmpty()){
            CharCount currChar = heap.poll();
            if(currChar.scheduledPos > position){
                return ""; // gap exists we cannot reorganize
            }
            result.append(currChar.c);
            currChar.count -=1;
            if(currChar.count > 0){
                currChar.scheduledPos = position + k;
                heap.add(currChar);
            }
            position += 1;
        }
        return result.toString();
    }
}