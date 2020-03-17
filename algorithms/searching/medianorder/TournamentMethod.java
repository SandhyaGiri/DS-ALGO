package algorithms.searching.medianorder;

/**
 * Finding second maximum(min) in least number of comparisons.
 */
public class TournamentMethod {
    private static void printResults(int[][] arr) {
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }
    public static int getSecondMax(int[] arr) {
        int rounds = (int)Math.ceil(arr.length /2.0);
        int[][] results = new int[rounds][arr.length];
        results[0] = arr;
        for(int round=1;round<rounds;round++) {
            int n = (int)Math.ceil(results[round-1].length /2.0);
            results[round] = new int[n];
            for(int i=0;i<n;i++) {
                if(2*i+1 >=n) {
                    results[round][i] = results[round-1][2*i];
                } else {
                    results[round][i] = results[round-1][2*i] > results[round-1][2*i+1] ? results[round-1][2*i] : results[round-1][2*i+1];
                }
            }
        }
        printResults(results);
        int max = results[rounds-1][0];
        System.out.println("Maximum : " + results[rounds-1][0]);
        int second_max = Integer.MIN_VALUE;
        int i=0;
        for(int round=rounds-2;round>=0;round--) {
            // System.out.printf("Comparing %d and %d",results[round][2*i], results[round][2*i+1]);
            if(results[round][2*i] == max) {
                second_max = results[round][2*i+1] > second_max ? results[round][2*i+1] : second_max;
                i = 2*i;
            } else if(results[round][2*i+1] == max) {
                second_max = results[round][2*i] > second_max ? results[round][2*i] : second_max;
                i = 2*i+1;
            }
        }
        return second_max;
    }
    public static void main(String args[]) {
        int arr[] = {27,15,81,91,32,62,9};
        System.out.println("Second maximum: " + getSecondMax(arr));
    }
}