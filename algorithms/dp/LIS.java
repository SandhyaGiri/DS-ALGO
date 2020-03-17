package algorithms.dp;

public class LIS {
    private static int lis(int[] arr, int n) {
        int[] ls = new int[n];
        int maxLength = 0;
        for(int i=0;i<n;i++) {
            int maxLengthSoFar = 0;
            for(int j=i;j>=0;j--) {
                if(arr[i] > arr[j] && ls[j] > maxLengthSoFar) {
                    maxLengthSoFar = ls[j];
                } 
            }
            ls[i] = 1 + maxLengthSoFar;
            if(ls[i] > maxLength) {
                maxLength = ls[i];
            }
        }
        return maxLength;
    }
    public static void main(String args[]) 
    { 
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 }; 
        int n = arr.length; 
        System.out.println("Length of lis is "
                           + lis(arr, n) + "\n"); 
    } 
}