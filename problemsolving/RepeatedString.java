package problemsolving;

import java.io.*;
import java.util.*;

public class RepeatedString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        int[] count = new int[s.length()];
        count[0] = s.charAt(0) == 'a' ? 1 : 0;
        for(int i=1;i<s.length();i++) {
            count[i] = s.charAt(i) == 'a' ? (count[i-1] + 1) : count[i-1];
        }
        int l = s.length();
        long fullStrings = n / l;
        int leftOutChars = (int)(n % l);
        return (fullStrings * count[l-1]) + (leftOutChars >0 ? count[leftOutChars-1] : 0);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
