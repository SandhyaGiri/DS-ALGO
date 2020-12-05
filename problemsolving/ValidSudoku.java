package problemsolving;

//https://leetcode.com/problems/valid-sudoku/
public class ValidSudoku {
    /**
     * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

        A partially filled sudoku which is valid.

        The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

        Example 1:

        Input:
        [
        ["5","3",".",".","7",".",".",".","."],
        ["6",".",".","1","9","5",".",".","."],
        [".","9","8",".",".",".",".","6","."],
        ["8",".",".",".","6",".",".",".","3"],
        ["4",".",".","8",".","3",".",".","1"],
        ["7",".",".",".","2",".",".",".","6"],
        [".","6",".",".",".",".","2","8","."],
        [".",".",".","4","1","9",".",".","5"],
        [".",".",".",".","8",".",".","7","9"]
        ]
        Output: true

        Idea: maintain indicators for each row, col, subgrid - use bits to store presence of all numbers in a single int
        for a row or col etc. 
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[] rowIndicators = new int[9];
        int[] colIndicators = new int[9];
        int[] subgridIndicators = new int[9];
        boolean isValid = true;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] != '.'){
                    int num = Character.getNumericValue(board[i][j]);
                    int bitIndex = (int)Math.pow(2, num-1);
                    //System.out.println("num: "+ num + ", bitIndex: " + bitIndex);
                    // row
                    if((rowIndicators[i] & bitIndex) > 0){
                        isValid = false;
                        break;
                    } else {
                        rowIndicators[i] = rowIndicators[i] | bitIndex;
                    }
                    // col
                    if((colIndicators[j] & bitIndex) > 0){
                        isValid = false;
                        break;
                    }else{
                        colIndicators[j] = colIndicators[j] | bitIndex;
                    }
                    // sub grid
                    int gridIndex = (int)Math.floor(i/3) * 3 + (int)Math.floor(j/3);
                    //System.out.println("GridIndex: " + gridIndex);
                    if((subgridIndicators[gridIndex] & bitIndex) > 0){
                        isValid = false;
                        break;
                    } else {
                        subgridIndicators[gridIndex] = subgridIndicators[gridIndex] | bitIndex;
                    }
                }
            }
        }
        return isValid;
    }
}