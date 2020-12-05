package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/bag-of-tokens
public class BagOfTokens {
    /**
     * You have an initial power of P, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).

        Your goal is to maximize your total score by potentially playing each token in one of two ways:

        If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
        If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
        Each token may be played at most once and in any order. You do not have to play all the tokens.

        Return the largest possible score you can achieve after playing any number of tokens.

     * Idea: Since playing token face up leads to reduced power, do this with smallest token value. Playing down
     * leads to increased power, so do this with highest token value. Maintain two heaps.
     * 
     * At any point, check if top of minHeap is <= power, if so play it to gain score.
     * Check if top of maxHeap leads to a power >= top of minHeap (so that we can later lose the gained power to gain score), if so play it to loose score.
     * If no moves are available, then break;
     * 
     * Time: O(N*logN), [max n poll operations when playing all tokens, each of O(logN) heap adjustment]
     * Space: O(N+N) - two heaps
     * @param tokens
     * @param P
     * @return
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<tokens.length;i++){
            minHeap.add(tokens[i]);
            maxHeap.add(tokens[i]);
        }
        int power = P;
        int score = 0;
        while(!minHeap.isEmpty() || !maxHeap.isEmpty()){
            // can we lose power to gain score?
            // lose only smallest power possible 
            if(!minHeap.isEmpty() && minHeap.peek() <= power){
                power -= minHeap.poll();
                score += 1;
            }
            // should we lose score to gain power?
            // can we gain maximum gainable power?
            // but we loose score, so do this move only when there are some more tokens in minHeap which can be played up to gain score later on.
            else if(score > 0 && !maxHeap.isEmpty() && !minHeap.isEmpty() && (power + maxHeap.peek()) >= minHeap.peek()){
                power += maxHeap.poll();
                score -= 1;
            } else {
                break; // no more moves.
            }
        }
        return score;
    }

    /**
     * Avoid unnecessary usage of 2 heaps, by an in place sort of the array.
     * 
     * @param tokens
     * @param P
     * @return
     */
    public int bagOfTokensScore_lessStorage(int[] tokens, int P) {
        // in place sorting, used left and right end as heap pointers
        Arrays.sort(tokens);
        int power = P;
        int score = 0;
        int l=0, r=tokens.length-1;
        while(l<=r){
            // can we lose power to gain score?
            if(tokens[l] <= power){
                power -= tokens[l];
                l+=1;
                score += 1;
            }
            // should we lose score to gain power?
            // l<r so that we are not comparing same element and have atleast one score gain possibility later on.
            else if(score > 0 && l<r && (power + tokens[r]) >= tokens[l]){
                power += tokens[r];
                r-=1;
                score -= 1;
            } else {
                break; // no more moves.
            }
        }
        return score;
    }
}
