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
}