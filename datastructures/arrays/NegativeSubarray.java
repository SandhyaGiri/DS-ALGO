package datastructures.arrays;

import java.util.*;

public class NegativeSubarray {

    private static Set<String> indices = new HashSet<String>();
    
    private static boolean isUniqueSubarray(int[] arr, int l, int r) {
        if(indices.isEmpty()) {
            indices.add(l + " " + r);
            return true;
        }
        boolean isPresent = indices.contains(l + " " + r);
        indices.add(l + " " + r);
        return !isPresent;
    }

    private static boolean isNegativeSubarray(int[] arr, int l, int r) {
        int sum = 0;
        for(int i=l;i<=r;i++) {
            sum +=arr[i];
        }
        return sum <0;
    }

    private static int sum(int[] arr, int l, int r) {
        if(l == r) {
            return arr[l] < 0 ? 1 : 0;
        }
        int sum_left = isUniqueSubarray(arr, l+1, r) ? sum(arr, l+1, r) : 0;
        int sum_right = isUniqueSubarray(arr, l, r-1) ? sum(arr, l, r-1) : 0;
        int new_find = isNegativeSubarray(arr, l, r) ? 1 : 0;
        return new_find + sum_left + sum_right;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = Integer.valueOf(scan.nextLine());
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scan.nextInt();
        }
        System.out.println(sum(arr, 0, n-1));
        scan.close();
    }
}

