package algorithms.sorting;

public class InsertionSort {

    private static void sort(int[] arr) {
        int n = arr.length;
        for(int i=1;i<=n-1;i++) {
            // pick the ith card
            int card = arr[i];
            int j=i-1;
            // for every card before this, if ith card is smaller keep pushing the cards backwards
            while(j>=0 && card < arr[j]) {
                // push larger number back
                arr[j+1] = arr[j];
                j--;
            }
            // Insert the ith card at its right position (as all elements after i are all greater than i)
            arr[j+1] = card;
        }
    }

    public static void main(String args[]) {
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        sort(arr);
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }
}