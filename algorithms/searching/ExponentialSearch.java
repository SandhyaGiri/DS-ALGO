package algorithms.searching;

/**
 * Each time l is doubled, as long as target is greater than l.
 * 
 * When this is violated, the target lies between l/2 and min(l,n). In this block we can do a binary search.
 */
public class ExponentialSearch {

    private static int binSearch(int[] arr, int l, int r, int target) {
        int found = -1;
        while(l<=r) {
            int mid = (int)Math.floor((l+r)/2);
            if(arr[mid] == target) {
                found = mid;
                break;
            } else if(arr[mid] < target) {
                l = mid+1;
            } else if(arr[mid] >target) {
                r = mid-1;
            }
        }
        return found;
    }

    public static int search(int[] arr, int target) {
        int n = arr.length;
        if(target == arr[0]) {
            return 0;
        }
        
        int l = 1;
        while(l < n && target >= arr[l]) {
            l = 2*l;
        }
        return binSearch(arr, l/2, Math.min(l,n), target);
    }

    public static void main(String args[]) {
        int arr[] = {2, 3, 4, 10, 40}; 
        int target = 40;
        System.out.println(search(arr, target));
    }
}