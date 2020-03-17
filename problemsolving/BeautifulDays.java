package problemsolving;

import java.io.*;
import java.util.*;

public class BeautifulDays {

    static int reverse(int i) {
        int reverse = 0;
        // System.out.printf("%d %d\n",i, reverse);
        while(i > 0) {
            int rem = i%10;
            i = i/10;
            reverse = reverse * 10 + rem;
        }
        // System.out.printf("%d %d\n",i, reverse);
        return reverse;
    }
    // Complete the beautifulDays function below.
    static int beautifulDays(int i, int j, int k) {
        int beautifulDays = 0;
        for(int g=i;g<=j;g++) {
            int rev = reverse(g);
            if(Math.abs(g-rev) % k == 0) {
                beautifulDays += 1;
            }
        }
        return beautifulDays;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] ijk = scanner.nextLine().split(" ");

        int i = Integer.parseInt(ijk[0]);

        int j = Integer.parseInt(ijk[1]);

        int k = Integer.parseInt(ijk[2]);

        int result = beautifulDays(i, j, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
