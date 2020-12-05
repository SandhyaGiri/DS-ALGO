package algorithms.backtracking;

import java.util.*;
import java.util.stream.*;

// https://leetcode.com/problems/largest-time-for-given-digits/
public class LargestTime {
    Set<String> getPermutations(int[] A, int n){
        if(n ==0){
            return new HashSet<>();
        }
        Set<String> permutations = getPermutations(A, n-1);
        if(permutations.isEmpty()){
            permutations.add(Integer.valueOf(A[n-1]).toString());
            return permutations;
        } else {
            Set<String> newPermutations = new HashSet<>();
            for(String oldPerm: permutations){
                StringBuilder s = new StringBuilder(oldPerm);
                // insert this digit at all positions
                for(int i=0;i<=oldPerm.length();i++){
                    s.insert(i, Integer.valueOf(A[n-1]).toString());
                    newPermutations.add(s.toString());
                    s.deleteCharAt(i);
                }
            }
            return newPermutations;
        }
    }
    int validateTime(String time, int maxSoFar){
        int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        int mins = (time.charAt(2) - '0') * 10 + (time.charAt(3) - '0');
        if(hour <= 23 && mins <=59 && (hour * 60 + mins) >= maxSoFar){
            return (hour * 60 + mins);
        }
        return -1;
    }
    /**
     * Given an array of 4 digits, return the largest 24 hour time that can be made.

        The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

        Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

        

        Example 1:

        Input: [1,2,3,4]
        Output: "23:41"
        Example 2:

        Input: [5,5,5,5]
        Output: ""
        

        Note:

        A.length == 4
        0 <= A[i] <= 9

        Idea: This is a combination problem - generate all combinations of the 4 digits given and check if its a validate
        time in 24 hrs format, hour < 24 and mins < 60. Keep track of max valid time seen so far - instead of the array convert
        the time to minutes to compare for max time.

     * @param A
     * @return
     */
    public String largestTimeFromDigits(int[] A){
        Set<String> combinations = getPermutations(A, A.length);
        int maxTimeInMins = 0;
        String result = "";
        for(String time: combinations){
            //System.out.println(time);
            int updatedMaxTime = validateTime(time, maxTimeInMins);
            if(updatedMaxTime >= maxTimeInMins){
                maxTimeInMins = updatedMaxTime;
                result = time;
            }
        }
        StringBuilder res = new StringBuilder(result);
        if(res.length() > 0){
            res.insert(2, ":");   
        }
        return res.toString();
    }

    // OWN APPROACH 
    public String largestTimeFromDigits_fails3(int[] A) {
        Arrays.sort(A);
        List<Integer> numbers= Arrays.stream(A).boxed().collect(Collectors.toList());
        int[] time = new int[4];
        int[] maxDigits = new int[]{2,3,5,9};
        for(int i=0;i<maxDigits.length;i++){
            int maxValue = maxDigits[i];
            if(i==1 && time[i-1] < maxDigits[i-1]){
                maxValue = 9;
            }
            boolean matchFound = false;
            while(maxValue >= 0){
                if(numbers.contains(maxValue)){ // exact match
                    time[i] = maxValue;
                    numbers.remove(new Integer(maxValue)); // so that next its not matched
                    matchFound = true;
                    break;
                }
                maxValue--;
            }
            if(!matchFound){
                System.out.println(i);
                return "";
            }
        }
        StringBuilder timeStr = new StringBuilder();
        for(int i=0;i<4;i++){
            timeStr.append(Integer.valueOf(time[i]).toString());
            if(i==1){
                timeStr.append(":");
            }
        }
        return timeStr.toString();
    }
}