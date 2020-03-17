package algorithms.searching.medianorder;

/**
 * https://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 * https://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/ 
 * https://www.geeksforgeeks.org/median-two-sorted-arrays-different-sizes-ologminn-m/
 */

public class MedianSortedArrays {

    /**
     * Always results in a final sorted array of size 2n => even, so median is average of middle two elements.
     * 
     * It is a sort of binary search on two sorted arrays.
     * 
     * @param arr1
     * @param arr2
     * @param n
     * @return
     */
    public static int sameSize(int arr1[], int arr2[], int n) {
        int l1=0,r1=n-1;
        int l2=0,r2=n-1;
        while(l1<r1-1 && l2<r2-1) {
            int mid1 = (l1+r1+1)/2;
            int mid2 = (l2+r2+1)/2;
            if(arr1[mid1] < arr2[mid2]) {
                l1 = mid1;
                r2 = mid2;
            } else {
                r1 = mid1;
                l2 = mid2;
            }
        }
        // Terminate when you have two elements in both arrays
        return (Math.max(arr1[l1],arr2[l2]) + Math.min(arr1[r1],arr2[r2])) / 2;
    }

    public double differentSize(int[] nums1, int[] nums2) {
        int M = nums1.length;
        int N = nums2.length;
        int[] A=nums1,B=nums2;
        if(M > N) { // exchange to have smaller array as A
            A = nums2;
            B = nums1;
            M = A.length;
            N = B.length;
        }
        int min_index =0, max_index = M;
        int i = (min_index + max_index) / 2;
        int j = (N+M+1) /2 - i;
        int median = 0;
        while(min_index <= max_index) {
            i = (min_index + max_index) / 2;
            j = (N+M+1) /2 - i;
            if(i<M && j>0 && B[j-1] > A[i]) {
                min_index = i+1;
            } else if(i>0 && j<N && B[j] < A[i-1]) {
                max_index = i-1;
            } else {
                if(i==0) {
                    median = B[j-1];
                } else if(j==0) {
                    median = A[i-1];
                } else {
                    median = Math.max(A[i-1],B[j-1]);
                }
                break;
            }
        }
        if((N+M) % 2 == 0) {
            if(i==M) {
                return (median + B[j]) / 2.0; 
            }
            if(j==N) {
                return (median + A[i]) / 2.0;
            }
            return (median + Math.min(A[i],  
                                 B[j])) / 2.0;
        } else {
            return (double)median;
        }
    }

    public static void main(String[] args)  
    {  
        int ar1[] = {1, 2, 3, 6};  
        int ar2[] = {4, 6, 8, 10};  
        int arr1[] = {1, 12, 15, 26, 38};
        int arr2[] = {2, 13, 17, 30, 45};
        int n1 = ar1.length;  
        int n2 = ar2.length;  
        if (n1 == n2)  
            System.out.println("Median is " + sameSize(ar1, ar2, n1));  
        else
            System.out.println("Doesn't work for arrays "+ "of unequal size");  
    } 
}