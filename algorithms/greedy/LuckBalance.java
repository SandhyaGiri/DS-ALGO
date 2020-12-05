package algorithms.greedy;

import java.io.*;
import java.util.*;

// 2d array sorting
class ContestComparator implements Comparator<int[]> {
    public int compare(int[] contest1, int[] contest2) {
        if(contest1[1] != contest2[1]) {
            return contest1[1] == 1 ? -1 : 1;
        } else {
            return Integer.valueOf(contest2[0] + "").compareTo(contest1[0]);
        }
    }
}
public class LuckBalance {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        Arrays.sort(contests, new ContestComparator());
        int contestCnt = contests.length;
        // System.out.println(contests[0][0] + " " + contests[0][1]);
        int luck = 0;
        for(int i=0;i<contestCnt;i++) {
            if(i<k && contests[i][1] == 1){
                // Keep losing k important games to accumulate luck
                luck+=contests[i][0];
                continue;
            }
            if(contests[i][1] == 1) {
                // Win the remaining important games and reduce luck
                luck-=contests[i][0];
            } else {
                // Lose all unimportant games to accumulate luck
                luck+=contests[i][0];
            }
        }
        return luck;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
