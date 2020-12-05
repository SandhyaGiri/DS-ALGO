package datastructures.arrays;

// https://leetcode.com/problems/rotate-image/
public class MatrixRotation {
    /**
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

        You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
        DO NOT allocate another 2D matrix and do the rotation.

        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [[7,4,1],[8,5,2],[9,6,3]]

        Idea: The rotation can be performed in layers, where you perform a cyclic swap on the edges on each layer.
        In the first for loop, we rotate the first layer (outermost edges) We rotate the edges by doing a four-way swap
        first on the corners, then on the element clockwise from the edges, then on the element three steps away
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n=matrix.length; // square
        for(int layer=0;layer< n/2;layer++){
            int first = layer;
            int last = n-1-layer;
            for(int i=first;i<last;i++){
                int offset = i-first;
                int top = matrix[first][i]; // saves 1
                // left -> top (saves 7 in top spot)
                matrix[first][i] = matrix[last-offset][first];
                // bottom -> left (saves 9 in bottom left spot)
                matrix[last-offset][first] = matrix[last][last-offset];
                // right -> bottom (saves 3 in bottom right spot)
                matrix[last][last-offset] = matrix[i][last];
                // top -> right (saves 1 in top right corner spot)
                matrix[i][last] = top;
            }
        }
    }
}
