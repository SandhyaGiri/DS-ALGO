package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/surrounded-regions/
public class EnemyCapture {
    enum Direction{
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    boolean isAlly(char[][] board, int x, int y){
        return board[x][y] == 'X';
    }
    int[][] getNeighbors(char[][] board){
        int m = board.length;
        int n = board[0].length;
        int[][] neighbors = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == 'X'){
                    neighbors[i][j] = -1;
                } else {
                    int numNeighbors = 0;
                    if(i-1 >= 0 && isAlly(board, i-1,j)){
                        numNeighbors += 1;
                    }
                    if(i+1 < m && isAlly(board, i+1, j)){
                        numNeighbors += 1;
                    }
                    if(j-1 >= 0 && isAlly(board, i,j-1)){
                        numNeighbors += 1;
                    }
                    if(j+1 < n && isAlly(board, i, j+1)){
                        numNeighbors += 1;
                    }
                    neighbors[i][j] = numNeighbors;
                }
            }
        }
        return neighbors;
    }
    int[] getNextNode(int i, int j, Direction dir){
        int[] nextPos = new int[2];
        
        if(dir == Direction.DOWN){
            nextPos[0] = i+1;
            nextPos[1] = j;
        }
        else if(dir == Direction.UP){
            nextPos[0] = i-1;
            nextPos[1] = j;
        }else if(dir == Direction.RIGHT){
            nextPos[0] = i;
            nextPos[1] = j+1;
        }else if(dir == Direction.LEFT){
            nextPos[0] = i;
            nextPos[1] = j-1;
        }
        return nextPos;
    }
    List<Direction> getValidMoves(char[][] board, int i, int j){
        int m = board.length;
        int n = board[0].length;
        // boundary positions - no further moves
        if(i==m-1 || i==0 || j==n-1 || j==0){
            return null;
        }
        List<Direction> moves = new ArrayList<>();
        if(i+1 < m && !isAlly(board, i+1,j)){
            moves.add(Direction.DOWN);
        }
        if(i-1 >=0 && !isAlly(board, i-1,j)){
            moves.add(Direction.UP);
        }
        if(j+1 < n && !isAlly(board, i,j+1)){
            moves.add(Direction.RIGHT);
        }
        if(j-1 >= 0 && !isAlly(board, i,j-1)){
            moves.add(Direction.LEFT);
        }
        return moves;
    }
    boolean checkConversion(int i, int j, char[][] result, boolean[][] explored){
        result[i][j] = 'X'; // convert now
        explored[i][j]= true;
        
        List<Direction> moves = getValidMoves(result, i, j);
        if(moves == null){
            return false; // boundary reached
        } else if(moves.size() ==0){
            return true; // we have successfully converetd a bunch of O's to X's
        }
        boolean isPossible = true;
        for(Direction dir: moves){
            int[] nextPos = getNextNode(i, j, dir);
            if(!checkConversion(nextPos[0], nextPos[1], result, explored)){
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }
    /**
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

        A region is captured by flipping all 'O's into 'X's in that surrounded region.

        Example:

        X X X X
        X O O X
        X X O X
        X O X X
        After running your function, the board should be:

        X X X X
        X X X X
        X X X X
        X O X X
        Explanation:

        Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
        Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
        Two cells are connected if they are adjacent cells connected horizontally or vertically.

        Idea: start converting O's to X and terminate on reaching edge or no more moves. In first case, these O's cannot be converted
        while in second, these O's are totally surrounded by allies hence can be captured/turned to X's.
        (DFS)
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        if(m >0){
            int n = board[0].length;
            int[][] neighbors = getNeighbors(board);
            boolean[][] explored = new boolean[m][n];
            // so that reference from incoming call for board array is not changed
            char[][] duplicateBoard = board;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(duplicateBoard[i][j] == 'O' && neighbors[i][j] >=2 && !explored[i][j]){
                        char[][] candidateResult = new char[m][n];
                        for(int x=0;x<m;x++){
                            candidateResult[x] = duplicateBoard[x].clone();
                        }
                        System.out.println("Candidate i:"+i+",j:"+j);
                        if(checkConversion(i, j, candidateResult, explored)){
                            System.out.println("possible");
                            duplicateBoard = candidateResult;
                        }
                    }
                }
            }
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    board[i][j] = duplicateBoard[i][j];
                }
            }   
        }
    }
}