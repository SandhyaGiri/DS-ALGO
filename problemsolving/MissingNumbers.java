package problemsolving;

import java.io.*;
import java.util.*;

public class MissingNumbers {

    static int findMin(int[] brr) {
        int min = brr[0];
        for(int i=1;i<brr.length;i++) {
            if(brr[i] < min) {
                min = brr[i];
            }
        }
        return min;
    }
    // Complete the missingNumbers function below.
    static int[] missingNumbers(int[] arr, int[] brr) {
        int min = findMin(brr);
        int[] crr = new int[100];
        for(int i=0;i<brr.length;i++) {
            crr[brr[i]-min]+= 1;
        }
        for(int j=0;j<arr.length;j++) {
            if(arr[j]-min < crr.length) {
                crr[arr[j]-min]--;
            }
        }
        List<Integer> missing = new ArrayList<Integer>();
        for(int k=0;k<crr.length;k++) {
            if(crr[k] != 0) {
                missing.add(k+min);
            }
        }
        int[] result = new int[missing.size()];
        for(int k=0;k<missing.size();k++) {
            result[k] = missing.get(k);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] brr = new int[m];

        String[] brrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrItems[i]);
            brr[i] = brrItem;
        }

        int[] result = missingNumbers(arr, brr);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
