package datastructures.arrays;

import java.util.*;

/**
    Sample input:
    1 1 1 0 0 0
    0 1 0 0 0 0
    1 1 1 0 0 0
    0 0 0 0 0 0
    0 0 0 0 0 0
    0 0 0 0 0 0

Sample output: 19

 */
public class HourGlass {

    private static final Scanner scanner = new Scanner(System.in);

    private static int getHourglassSum(int startPosX, int startPosY, int[][] arr) {
        int sum = Integer.MIN_VALUE;
        if(startPosY+2 < arr[0].length && startPosX+2 < arr.length) {
            sum = 0;
            sum += arr[startPosX][startPosY] + arr[startPosX][startPosY+1] + arr[startPosX][startPosY+2];
            sum += arr[startPosX+1][startPosY+1];
            sum += arr[startPosX+2][startPosY] + arr[startPosX+2][startPosY+1] + arr[startPosX+2][startPosY+2];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int max_sum =Integer.MIN_VALUE;
        for (int i = 0; i < 6; i++) {
            for(int j=0;j<6;j++) {
                int sum = getHourglassSum(i,j,arr);
                if(sum > max_sum) {
                    max_sum = sum;
                }
            }
        }
        System.out.println(max_sum == Integer.MIN_VALUE ? 0 : max_sum);
        scanner.close();
    }
}
