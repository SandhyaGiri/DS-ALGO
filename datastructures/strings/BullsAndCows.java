package datastructures.strings;

import java.util.*;

// https://leetcode.com/problems/bulls-and-cows/
public class BullsAndCows {
    /**
     * 
     * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

        Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

        Please note that both secret number and friend's guess may contain duplicate digits.

        Example 1:

        Input: secret = "1807", guess = "7810"

        Output: "1A3B"

        Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
        Example 2:

        Input: secret = "1123", guess = "0111"

        Output: "1A1B"

        Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
        Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

        Input: secret = '1122', guess = '2211'
        Output: "0A4B"

        Explanation: though no bulls, 2 at pos 0 is a cow for 2 in pos 2, 2 at pos 1 -> 2 at pos 3. similar other two 1s.
        So overall 4 cows.

        Idea: for secret and guess, store a hashmap with digit as key and indices where it appears as the value (set)
        Now for each digit in guess, check if it exists in secret map. If so, get both set of indices
        bulls -> cardinality of intersection of two sets
        cows -> min(setA_size - bulls, setB_size - bulls) - because remaining indices are in wrong positions but we can only count
        as many extra guesses or as many original extra digits.
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        Map<Integer, Set<Integer>> secretDigits = new HashMap<>();
        for(int i=0;i<secret.length();i++){
            int x = Character.getNumericValue(secret.charAt(i));
            if(secretDigits.containsKey(x)){
                secretDigits.get(x).add(i); // new index
            } else{
                Set<Integer> indices = new HashSet<>();
                indices.add(i);
                secretDigits.put(x, indices);
            }
        }

        Map<Integer, Set<Integer>> guessDigits = new HashMap<>();
        for(int i=0;i<guess.length();i++){
            int y = Character.getNumericValue(guess.charAt(i));
            if(guessDigits.containsKey(y)){
                guessDigits.get(y).add(i); // new index
            } else{
                Set<Integer> indices = new HashSet<>();
                indices.add(i);
                guessDigits.put(y, indices);
            }
        }
        
        for(int digit: guessDigits.keySet()){
            if(secretDigits.containsKey(digit)){
                Set<Integer> secretIndices = secretDigits.get(digit);
                Set<Integer> guessIndices = guessDigits.get(digit);
                Set<Integer> bullsEye = new HashSet<>(secretIndices);
                bullsEye.retainAll(guessIndices);
                bulls += bullsEye.size();
                cows += Math.min(secretIndices.size()-bullsEye.size(), guessIndices.size()-bullsEye.size());
            }
        }
        return bulls + "A" + cows + "B";
    }
}
