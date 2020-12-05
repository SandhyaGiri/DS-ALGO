package algorithms.sorting;

public class DutchNationalFlag {
    
    private static int[] partition(int[] arr, int l,int r) {
        int pivot = arr[r];
        int i = l-1; // maintains the index of the left subarray containing elements lesser than pivot
        
        for(int j=l;j<r;j++) {
            if(arr[j] < pivot) {
                i++;
                // Swap arr[j] , arr[i] i.e move the element smaller than pivot to ith pos
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[r]/pivot
        int temp = arr[i+1];
        arr[i+1] = pivot;
        arr[r] = temp;

        int k = i+1;
        for(int j=k+1;j<r;j++) {
            if(arr[j] == pivot) {
                k++;
                // Swap arr[j] , arr[k] i.e move the element same as the pivot to ith pos
                arr[j] = arr[k];
                arr[k] = pivot;
            }
        }
        return new int[]{i+1, k};
    }

    private static void sort(int[] arr, int l, int r) {
        if(l<r) {
            // Fixes the pivot position and paritions the array into 3 portions, middle portion is all elements which are same as the pivot (equals)
            int[] p = partition(arr,l,r);
            // What is left is sorting the left and right partitions
            sort(arr,l,p[0]-1);
            sort(arr,p[1]+1,r);
        }
    }
    public static void main(String[] args) {
        int arr[] = {4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4}; 
        sort(arr,0,arr.length-1);
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }

    // Related problem: Sort Colors
    // https://leetcode.com/problems/sort-colors/
    /**
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

        Note: You are not suppose to use the library's sort function for this problem.

        Example:

        Input: [2,0,2,1,1,0]
        Output: [0,0,1,1,2,2]
        Follow up:

        A rather straight forward solution is a two-pass algorithm using counting sort.
        First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
        Could you come up with a one-pass algorithm using only constant space?
     * @param arr
     * @param i
     * @param j
     */
    void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void sortColors(int[] nums) {
        int l=-1, r=nums.length;
        int i=0;
        // first set all 0's towards the front of the array
        while(i< nums.length){
            if(nums[i] == 0){
                l++;
                swap(nums, l, i);
            }
            i++;
        }
        // start from end, place all 2's at the end of the array
        i=nums.length-1;
        while(i> l){
           if(nums[i] == 2){
               r--;
               swap(nums, r, i);
           }
            i--;
        }
        // now all 1's are in the middle of the array.
    }
}