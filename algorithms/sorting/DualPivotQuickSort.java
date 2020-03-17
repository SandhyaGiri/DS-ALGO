package algorithms.sorting;

/**
 * Used in Arrays.sort(), has average complexity of O(nlogn). Faster than single pivot quick sort.
 */
public class DualPivotQuickSort {
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] partition(int[] arr, int l,int r) {
        if(arr[l] > arr[r]) {
            swap(arr,l,r);
        }
        int pivot1 = arr[l];
        int pivot2 = arr[r];
        System.out.println("Pivots " + pivot1 + "," + pivot2);
        int i = l-1; // maintains the index of the left subarray containing elements lesser than pivot1
        int k = r+1; // maintains the index of the right subarray containing elements greater than pivot2

        int j=l;
        // Reduction in comparisons
        while(j<k) {
            if(arr[j] < pivot1) {
                i++;
                swap(arr, i, j);
            } else if(arr[j] > pivot2) {
                k--;
                swap(arr, j, k);
            }
            j++;
        }
        // Insert pivots at right positions
        arr[i+1] = pivot1;
        swap(arr, k-1, r);
        System.out.println("Pivots End " + arr[i+1] + "," + arr[k-1]);
        return new int[]{i+1,k-1};
    }

    private static void sort(int[] arr, int l, int r) {
        if(l<r) {
            // Fixes the pivot position for 2 pivots and paritions the array into 3 portions
            int[] p = partition(arr,l,r);
            // What is left is sorting the three partitions
            sort(arr,l,p[0]-1);
            sort(arr,p[0]+1,p[1]-1);
            sort(arr,p[1]+1,r);
        }
    }
    public static void main(String[] args) {
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        sort(arr,0,arr.length-1);
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }
}