package algorithms.searching.medianorder;

/**
 * Time: O(n) - linear time search, better than sorting and retrieving kth element O(nlgn)
 */
public class KthLargest {
    private int randomPartition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l-1;
        for(int j=l;j<r;j++) {
            if(arr[j]< pivot) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // set the pivot at right position
        int temp = arr[i+1];
        arr[i+1] = pivot;
        arr[r] = temp;
        return i+1;
    }
    public int findKthLargest(int[] nums, int k) {
        int l = 0;
        int r = nums.length-1;
        int kthLargest = 0;
        while(l<=r) {
            if(l==r) {
                kthLargest = nums[l];
                break;
            } else {
                int q = randomPartition(nums, l, r);
                int largeElements = r-q+1;
                if(k == largeElements) {
                    kthLargest = nums[q]; // curr pivot is kthlargest element
                    break;
                } else if (k > largeElements) {
                    // search on left subarray
                    r = q-1;
                    k = k -largeElements;
                } else {
                    // search on right subarray
                    l = q+1;
                }
            }
        }
        return kthLargest;
    }
}