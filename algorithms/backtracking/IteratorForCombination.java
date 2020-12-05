package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/iterator-for-combination/
public class IteratorForCombination {
    class CombinationIterator {

        TreeSet<Integer> combinations;
        Iterator<Integer> iterator;
        String characters;
        int charLength;
        void generateCombinations(int currCombination, int currCharLength, int currSkipCount, int charLength, int combinationLength, int skipCount){
            if(currCharLength == combinationLength){
                combinations.add(currCombination);
                return;
            }
            //System.out.println("Skipcount:" + skipCount);
            if(currSkipCount < skipCount){ // can skip
                //System.out.println("Skipping at " + currCharLength);
                generateCombinations(currCombination, currCharLength, currSkipCount+1, charLength, combinationLength, skipCount);
            }
            // can't skip -> pick the char, i.e set the bit corresponding to the char chosen.
            //System.out.println("Choosing char" + Math.pow(2, charLength-currCharLength-currSkipCount-1));
            currCombination = currCombination | (int)Math.pow(2, charLength-currCharLength-currSkipCount-1);
            //System.out.println(currCombination);
            generateCombinations(currCombination, currCharLength+1, currSkipCount, charLength, combinationLength, skipCount);
        }
        
        /**
         * Design an Iterator class, which has:

            A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
            A function next() that returns the next combination of length combinationLength in lexicographical order.
            A function hasNext() that returns True if and only if there exists a next combination.
            

            Example:

            CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

            iterator.next(); // returns "ab"
            iterator.hasNext(); // returns true
            iterator.next(); // returns "ac"
            iterator.hasNext(); // returns true
            iterator.next(); // returns "bc"
            iterator.hasNext(); // returns false
            

            Constraints:

            1 <= combinationLength <= characters.length <= 15
            There will be at most 10^4 function calls per test.
            It's guaranteed that all calls of the function next are valid.

            Hint: Use bit masking to generate all the combinations.

            Idea: This is a DFS problem. Each character can be taken or skipped, until the numchars reaches the combination length.
            We can only skip chars if we have enough chars to be skipped. skipCount = characters.length() - combinationLength
            i.e all chars outside the combination are skippable.
            Bitlogic -> abcd, combination abc can be represented as 1110 (14)
                        abcd, combination abd can be represented as 1101 (13)
                        abcd, combination acd can be represented as 1011 (11)
                        abcd, combination bcd can be represented as 0111 (7)
            In order for maintaining lexicographical order, we can sort the resulting integer values of binary representation
            in descending order to get lexicographically sorted list of combinations.
         * @param characters
         * @param combinationLength
         */
        public CombinationIterator(String characters, int combinationLength) {
            combinations = new TreeSet<>(Comparator.reverseOrder());
            charLength = characters.length();
            generateCombinations(0, 0, 0, charLength, combinationLength, charLength - combinationLength);
            //for(int x: combinations){
            //    System.out.println(x);
            //}
            this.characters = characters;
            iterator = combinations.iterator();
        }
        
        public String next() {
            int binaryEncoding = iterator.next();
            StringBuilder result = new StringBuilder();
            for(int i=charLength-1;i>=0;i--){ // binary bit index
                // System.out.println((int)Math.pow(2, i));
                if((binaryEncoding & (int)Math.pow(2, i)) > 0){
                    result.append(this.characters.charAt(charLength-i-1) + "");
                }
                // System.out.println(result.toString());
            }
            return result.toString();
        }
        
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}