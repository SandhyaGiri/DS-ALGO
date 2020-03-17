package algorithms.dp;

public class MaxCircularSubarraySum {

    /**
     * Returns the maximum contiguous postive sum in the array.
     * 
     * Time: O(n)
     * @param arr
     * @return
     */
    public static int kadane(int[] arr) {
        int max_so_far = Integer.MIN_VALUE;
        int max_ending_here=0;

        for(int i=0;i<arr.length;i++) {
            max_ending_here += arr[i];
            if(max_ending_here < 0) {
                max_ending_here = 0;
            }
            if(max_ending_here > max_so_far) {
                max_so_far = max_ending_here;
            }
        }
        return max_so_far;
    }

    public static int circularSum(int[] arr) {
        int maxSumUnwrapped = kadane(arr);
        int maxSumWrapped = 0;
        for(int i=0;i<arr.length;i++) {
            maxSumWrapped += arr[i];
            arr[i] = -1 * arr[i];
        }
        maxSumWrapped = maxSumWrapped + kadane(arr); // array-sum - (-max subarray sum of inverted array) 
        return Math.max(maxSumUnwrapped, maxSumWrapped);
    }

    public static void main(String args[]) {
        int a[] =  {8, -8, 9, -9, 10, -11, 12};
        System.out.println("Maximum subarray sum (Kadane): " + kadane(a));
        System.out.println("Maximum circular subarray sum: " + circularSum(a));
    }
}