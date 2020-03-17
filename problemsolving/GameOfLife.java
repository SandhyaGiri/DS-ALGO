package problemsolving;

import java.math.BigDecimal;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

 */
class GameOfLife {

    public static int getLiveNeighborCount(int i, int j, int[][] board) {
        int m = board.length, n = board[0].length;
        int liveNeighbors = 0;
        if(i+1 < m && board[i+1][j] == 1) {
            liveNeighbors++;
        }
        if(i-1>=0 && board[i-1][j] == 1) {
            liveNeighbors++;
        }
        if(j+1 < n && board[i][j+1] == 1) {
            liveNeighbors++;
        }
        if(j-1>=0 && board[i][j-1] == 1) {
            liveNeighbors++;
        }
        if(j-1>=0 && i-1>=0 && board[i-1][j-1] == 1) {
            liveNeighbors++;
        }
        if(j-1>=0 && i+1<m && board[i+1][j-1] == 1) {
            liveNeighbors++;
        }
        if(i-1>=0 && j+1<n && board[i-1][j+1] == 1) {
            liveNeighbors++;
        }
        if(j+1<n && i+1<m && board[i+1][j+1] == 1) {
            liveNeighbors++;
        }
        return liveNeighbors;
    }

    public static void bruteForce(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] output = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int liveNeighbors = getLiveNeighborCount(i,j,board);
                if(board[i][j] == 1) {
                    if(liveNeighbors < 2 || liveNeighbors > 3) {
                        output[i][j] = 0;
                    } else {
                       output[i][j] = 1; 
                    }
                } else {
                    if(liveNeighbors == 3) {
                        output[i][j] = 1;
                    }
                }
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                board[i][j] = output[i][j];
            }
        }
    }

    private static void printDetails(BigDecimal bd) {
        System.out.println("Precision: " + bd.precision());
        System.out.println("Scale: " + bd.scale());
        System.out.println("Signum: " + bd.signum());
    }

    public static void main(String args[]) {
        int board[][] = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int m = board.length, n = board[0].length;
        bruteForce(board);
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        BigDecimal bd1 = new BigDecimal("1.0");
        printDetails(bd1);
        BigDecimal bd2 = new BigDecimal("1.00");
        printDetails(bd2);
        System.out.println(bd1.compareTo(bd2)); // compares without scale, i.e only the unscaled value is compared
        System.out.println(bd1.equals(bd2)); // compares both scale and unscaled value
    }
}