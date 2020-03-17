package algorithms.searching;

/**
 * Keep jumping by a step size m as long as target is greater than the postion we jump to.
 * Indices compared against target: l, l+m, l+2m, l+3m etc..
 * Once we violate this condition, stop jumping, and start a linear search in m-1 elements in the identified block.
 * 
 * The optimal size of a block to be jumped is (√ n): The total number of comparisons in the worst case(last ele is target)
 * will be ((n/m) + m-1). The value of the function ((n/m) + m-1) will be minimum when m = √n.
 * 
 * This makes the time complexity of Jump Search O(√ n).
 * The time complexity of Jump Search is between Linear Search ( ( O(n) ) and Binary Search ( O (Log n) ).
 */
public class JumpSearch {

    public static int search(int[] arr, int target) {
        int n = arr.length;
        int m = (int)Math.sqrt(n); // Optimal jump size
        int l =0;
        while(l+m < n && target > arr[l+m]) {
            l = l+m+1;
        }
        // System.out.printf("block %d, step size: %d", l, m);
        if(l<n) {
            for(int i=l;i<=l+m;i++) {
                if(arr[i] == target) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int arr[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 
            34, 55, 89, 144, 233, 377, 610 }; 
        int target = 1;
        System.out.println(search(arr, target));
    }
}