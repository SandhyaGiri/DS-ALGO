package problemsolving;

public class AngleBetweenHandsClock {
    // https://leetcode.com/problems/angle-between-hands-of-a-clock/
    /**
     * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.
     * 
     * Input: hour = 12, minutes = 30
     * Output: 165
     * 
     * Convert the hour to same scale as minutes , * 5, update the hour hand based on minutes, 60 mins divided over 5 bars => 12 mins per bar
     * simply minutes/60 * 5 to get the current hour hand position. then find degree of each bar, 180 on one side shared by 6 hours, 30 for each,
     * for each bar (/5), we have 6 degrees.
     * @param hour
     * @param minutes
     * @return
     */
    public double angleClock(int hour, int minutes) {
        double updatedHour = (hour * 5) % 60;
        // hour hand moves based on minutes
        updatedHour += ((double)minutes/60) * 5; 
        
        // one angle
        double diff = Math.abs(updatedHour - minutes);
        //System.out.println("Hour: "+ updatedHour +", diff: " + diff);
        double angle = Math.floor(diff)* 6; // 6 degree for each bar crossed
        angle += (diff - Math.floor(diff)) * 6;
        
        return Math.min(360-angle, angle);
    }
}