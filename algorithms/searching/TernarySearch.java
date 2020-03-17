package algorithms.searching;

/**
 * Split into 3 parts and search in one of the blocks.
 * 
 */
public class TernarySearch {

    private static int search(int[] arr, int target) {
        int found = -1;
        int l =0;
        int r = arr.length-1;
        while(l<=r) {
            int portion = (int)Math.floor((r-l)/3);
            int mid1 = l + portion;
            int mid2 = r - portion;

            if(arr[mid1] == target) {
                found = mid1;
                break;
            } else if(arr[mid2] == target) {
                found = mid2;
                break;
            } else if(arr[mid1] > target) {
                r = mid1-1;
            } else if(target > arr[mid2]) {
                l = mid2+1;
            } else {
                l = mid1+1;
                r=mid2;
            }
        }
        return found;
    }

    public static void main(String args[]) {
        int arr[] = {2, 3, 4, 10, 40}; 
        int target = 4;
        System.out.println(search(arr, target));
    }
}