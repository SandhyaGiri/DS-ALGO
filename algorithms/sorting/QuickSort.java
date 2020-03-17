package algorithms.sorting;

public class QuickSort {
    
    private static int partition(int[] arr, int l,int r) {
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
        return i+1;
    }

    private static void sort(int[] arr, int l, int r) {
        if(l<r) {
            // Fixes the pivot position and paritions the array
            int p = partition(arr,l,r);
            // What is left is sorting the two partitions
            sort(arr,l,p-1);
            sort(arr,p+1,r);
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