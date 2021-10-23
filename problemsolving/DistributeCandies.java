package problemsolving;

import java.util.*;

public class DistributeCandies {
    /**
     * 
     * // https://leetcode.com/problems/distribute-candies-to-people
     * 
     * We distribute some number of candies, to a row of n = num_people people in the following way:

        We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.

        Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.

        This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).

        Return an array (of length num_people and sum candies) that represents the final distribution of candies.

        

        Example 1:

        Input: candies = 7, num_people = 4
        Output: [1,2,3,1]
        Explanation:
        On the first turn, ans[0] += 1, and the array is [1,0,0,0].
        On the second turn, ans[1] += 2, and the array is [1,2,0,0].
        On the third turn, ans[2] += 3, and the array is [1,2,3,0].
        On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
        Example 2:

        Input: candies = 10, num_people = 3
        Output: [5,2,3]
        Explanation: 
        On the first turn, ans[0] += 1, and the array is [1,0,0].
        On the second turn, ans[1] += 2, and the array is [1,2,0].
        On the third turn, ans[2] += 3, and the array is [1,2,3].
        On the fourth turn, ans[0] += 4, and the final array is [5,2,3].
        

        Constraints:

        1 <= candies <= 10^9
        1 <= num_people <= 1000

        Idea: compute number of full rounds, and distribute these candies
        for the incomplete round, we need to keep distributing until candies last.
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int rounds = 0;
        while(candies > 0){
            int partialSum = (num_people * (num_people +1))/2;
            int remSum = num_people * (rounds * num_people);
            int roundSum = partialSum + remSum;
            //System.out.println("Round: " + rounds);
            //System.out.println("RemSum sum: " + remSum);
            if(candies - roundSum >= 0){
                candies = candies - roundSum;
                rounds+=1;
                System.out.println("Remaining Candies: " + candies);
            } else {
                break; // incomplete round reached
            }
        }
        int fullRoundSumPrefix = 0;
        // find the sum of 1+n+2n+...+rounds*n
        // as this will be prefix for last complete round, to which we add 1+1+... or 2+2+...
        for(int i=0;i<rounds;i++){
            fullRoundSumPrefix += (i * num_people);
        }
        //System.out.println("Last Sum prefix: " + fullRoundSumPrefix);
        // distribute candies in full rounds
        for(int i=0;i<num_people;i++){
            result[i] = fullRoundSumPrefix + rounds* (i+1);
        }
        // last round (incomplete)
        //System.out.println("Candies left: " + candies);
        int index=0;
        while(candies > 0){
            int currCandies = rounds*num_people+(index+1);
            //System.out.println("incomplete round: " + currCandies);
            int candiesGiven = Math.min(currCandies, candies);
            result[index] += candiesGiven;
            candies-=candiesGiven;
            index++;
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/distribute-candies/
     * 
     * Alice has n candies, where the ith candy is of type candyType[i]. Alice noticed that she started to gain weight, so she visited a doctor.

        The doctor advised Alice to only eat n / 2 of the candies she has (n is always even). Alice likes her candies very much, and she wants to eat the maximum number of different types of candies while still following the doctor's advice.

        Given the integer array candyType of length n, return the maximum number of different types of candies she can eat if she only eats n / 2 of them.

        Example 1:

        Input: candyType = [1,1,2,2,3,3]
        Output: 3
        Explanation: Alice can only eat 6 / 2 = 3 candies. Since there are only 3 types, she can eat one of each type.
        Example 2:

        Input: candyType = [1,1,2,3]
        Output: 2
        Explanation: Alice can only eat 4 / 2 = 2 candies. Whether she eats types [1,2], [1,3], or [2,3], she still can only eat 2 different types.
        Example 3:

        Input: candyType = [6,6,6,6]
        Output: 1
        Explanation: Alice can only eat 4 / 2 = 2 candies. Even though she can eat 2 candies, she only has 1 type.

        Idea: count the distinct candy types, the answer is min of distinct types or n/2.

     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        Set<Integer> candyTypes = new HashSet<>();
        int numCandies = candyType.length;
        for(int i=0;i<numCandies;i++){
            if(!candyTypes.contains(candyType[i])){
                candyTypes.add(candyType[i]);
            }
        }
        return Math.min(candyTypes.size(), numCandies/2);
    }
}