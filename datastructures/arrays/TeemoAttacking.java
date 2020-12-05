package datastructures.arrays;

// https://leetcode.com/problems/teemo-attacking/
public class TeemoAttacking {
    
    /**
     * Given time series of times when Teemo atatcks Ashe, return total time Ashe remains poisoned.
     * 
     * Idea: Maintain start, end of poison duration. Initially start =-1, end=-1. If t_i <= end, then
     * extend the interval by setting end=t_i+duration. Otheriwise new time is past the poison duration
     * signifying start of new interval, add previous end-start to total poison duration.
     * 
     * Time: O(n)
     * Space: O(1)
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int poisonEndTime = -1;
        int totalPoisonedDuration = 0;
        int start =-1;
        for(int i=0;i<timeSeries.length;i++){
            if(start == -1){
                start = timeSeries[i];
                poisonEndTime = start + duration -1;
            }
            else if(timeSeries[i] <= poisonEndTime) {
                poisonEndTime = timeSeries[i] + duration -1;
            } else if (poisonEndTime != -1){
                totalPoisonedDuration += (poisonEndTime-start+1);
                start = timeSeries[i];
                poisonEndTime = start + duration -1;
            }
        }
        // last attack
        if(poisonEndTime != -1){
            totalPoisonedDuration += (poisonEndTime-start+1);
        }
        return totalPoisonedDuration;
    }
}
