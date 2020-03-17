package problemsolving;

import java.util.stream.*;
import java.util.*;

public class SmallestPositiveMissingNumber {
    /**
     * Write a function:

        class Solution { public int solution(int[] A); }

        that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

        For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

        Given A = [1, 2, 3], the function should return 4.

        Given A = [−1, −3], the function should return 1.

        Write an efficient algorithm for the following assumptions:

        N is an integer within the range [1..100,000];
        each element of array A is an integer within the range [−1,000,000..1,000,000].
     * @param A
     * @return
     */
    public int solution(int[] A) {
        // write your code in Java SE 8
        int smallestPosNum = 0;
        IntStream s = Arrays.stream(A);
        int[] postiveA = s.filter(num -> num > 0).toArray();
        Arrays.sort(postiveA);
        for(int i=0;i<postiveA.length;i++) {
            if(postiveA[i] == smallestPosNum) {
                continue;
            } else if(postiveA[i] == smallestPosNum + 1){
                smallestPosNum++;
            } else {
                break;
            }
        }
        return smallestPosNum == 0 ? 1 : smallestPosNum + 1;
    }
}