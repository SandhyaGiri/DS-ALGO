package algorithms.dp;

// https://leetcode.com/problems/number-of-longest-increasing-subsequence/
public class NumLIS {
    /**
     * Given an integer array nums, return the number of longest increasing subsequences. 

    Example 1:

    Input: nums = [1,3,5,4,7]
    Output: 2
    Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
    Example 2:

    Input: nums = [2,2,2,2,2]
    Output: 5
    Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.

        Idea: In addition to maintaining length of the incr. sub ending at each i, we should also maintain number of such sequences which
        have length = lis[i]. In order to calculate this, for each i, for all j < i whose value nums[j]<nums[i], find a j with max lis[j].
        There could be several j's, which means sequence ending at i can extend any of these j's to get the length lis[i]. So calculate
        nlis[i] = sum_j(nlis[j])  - like num unqiue paths.

        Also maintain a global max lis, and sum all nlis for indices with same max lis.
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int[] lis = new int[nums.length];
        // maintains number of ways of generating the particular length sequence
        int[] nlis = new int[nums.length];
        int maxLength = Integer.MIN_VALUE;
        int numMaxLength = 0;
        for(int i=0;i<nums.length;i++){
            int currMaxLength = -1;
            int numCurrMaxLength = 0;
            for(int j=i-1;j>=0;j--){
                if(nums[j] < nums[i]){
                    // just like numPaths problems, sum the number of ways of reaching maxLength candidates. or simply number of sequences they extend.
                    if(lis[j] > currMaxLength){
                        currMaxLength = lis[j];
                        numCurrMaxLength = nlis[j];
                    } else if(lis[j] == currMaxLength){
                        numCurrMaxLength += nlis[j];
                    }
                }
            }
            if(currMaxLength != -1){ // candidate exists
                lis[i] = currMaxLength + 1;
                nlis[i] = numCurrMaxLength;
            } else { // start a new sequence
                lis[i] = 1;
                nlis[i] = 1;
            }
            // maintain overall values
            if(lis[i] > maxLength){ // new length encountered
                maxLength = lis[i];
                numMaxLength = nlis[i];
            } else if(lis[i] == maxLength){ // accumulate number of paths
                numMaxLength += nlis[i];
            }
        }
        //System.out.println(Arrays.toString(lis));
        //System.out.println(Arrays.toString(nlis));
        return numMaxLength;
    }
}
