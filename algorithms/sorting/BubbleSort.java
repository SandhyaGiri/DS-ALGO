package algorithms.sorting;

/**
 * In computer graphics it is popular for its capability to detect a very small error
 * (like swap of just two elements) in almost-sorted arrays and fix it with just linear complexity (2n). 
 * For example, it is used in a polygon filling algorithm, where bounding lines are sorted by their x 
 * coordinate at a specific scan line (a line parallel to x axis) and with incrementing y their order 
 * changes (two elements are swapped) only at intersections of two lines
 */
public class BubbleSort {

    private static void sort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for(int i=0;i<n-1;i++) { // all bubbles of size 2
            // Last bubble is already sorted
            // we can track number of swaps in each iteration, and if no swaps then terminate.
            swapped = false;
            for(int j=0;j<n-i-1;j++) {
                if(arr[j] > arr[j+1]) {
                    // Swap the elements
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }

            if(!swapped) {
                break;
            }
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