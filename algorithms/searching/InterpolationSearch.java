package algorithms.searching;

/**
 * Enhancement of Binary Search to find an element in a sorted array, given that the elements are uniformly distributed.
 * 
 * So, instead of always comparing the target with the middle element, this search finds an optimal position. This pos is closer to
 * l if target is closer to arr[l], and similarly this pos value is closer to r if target is closer to arr[r].
 * 
 * Time Complexity: If elements are uniformly distributed, then O(log log n)). In worst case it can take upto O(n).
 */
public class InterpolationSearch {

    public static int search(int[] arr, int target) {
        int l=0,r=arr.length-1;
        while(l<r) {
            // Find optimal pos instead of mid as in Binary Search
            int pos = l + (int)((target - arr[l]) * (((double)r-l) /(arr[r]-arr[l])));
            if(target == arr[pos]) {
                return pos;
            } else if(target > arr[pos]) {
                l = pos+1;
            } else {
                r = pos-1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        // Scanner scan = new Scanner(System.in);
        // int n = scan.nextInt();
        // int target = scan.nextInt();
        // int[] arr = new int[n];
        // for(int i=0;i<n;i++) {
        //     arr[i] = scan.nextInt();
        // }
        int arr[] = {10, 12, 13, 16, 18, 19, 20, 21, 
            22, 23, 24, 33, 35, 42, 47}; 
        int target = 18;
        System.out.println(search(arr, target));
    }
}