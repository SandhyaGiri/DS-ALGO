package problemsolving;

import java.io.*;
import java.util.*;

public class CutTheSticks {

    // Complete the cutTheSticks function below.
    static int[] cutTheSticks(int[] arr) {
        int[] c= new int[1000];
        int distinctEle = 0;
        for(int i=0;i<arr.length;i++) {
            if(c[arr[i]] == 0) {
                distinctEle++;
            }
            c[arr[i]]++;
        }
        int sticks = arr.length;
        int[] cuts = new int[distinctEle];
        int k=0;
        for(int i=0;i<c.length;i++) {
            if(c[i]!=0) {
                cuts[k++]= sticks;
                sticks = sticks - c[i];
            }
        }
        return cuts;
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

        int[] result = cutTheSticks(arr);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
