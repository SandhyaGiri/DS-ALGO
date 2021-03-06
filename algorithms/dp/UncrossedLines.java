package algorithms.dp;

public class UncrossedLines {
    // recursive solution - may be exponential in m or n
    int dpUtil(int[] A, int[] B, int i, int j){
        if(i>= A.length || j>=B.length){ // reached the end
            return 0;
        }
        if(A[i] == B[j]){
            return 1+ dpUtil(A, B, i+1, j+1);
        }
        return Math.max(dpUtil(A, B, i+1, j), dpUtil(A, B, i, j+1));
    }
    /**
     * We write the integers of A and B (in the order they are given) on two separate horizontal lines.

        Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

        A[i] == B[j];
        The line we draw does not intersect any other connecting (non-horizontal) line.
        Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

        Return the maximum number of connecting lines we can draw in this way.

        

        Example 1:


        Input: A = [1,4,2], B = [1,2,4]
        Output: 2
        Explanation: We can draw 2 uncrossed lines as in the diagram.
        We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
        Example 2:

        Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
        Output: 3
        Example 3:

        Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
        Output: 2
        

        Note:

        1 <= A.length <= 500
        1 <= B.length <= 500
        1 <= A[i], B[i] <= 2000

        Base idea: line crossing two elements at same index will always be uncrossing. So we just have to move the indices in A, B differently.
        Similar to longest common subsequence problem.

        Think dynamic programming. Given an oracle dp(i,j) that tells us how many lines A[i:], B[j:] [the sequence A[i], A[i+1], ... 
        and B[j], B[j+1], ...] are uncrossed, can we write this as a recursion?
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if(m > 0 && n > 0){
            // memoized solution Time: O(m*n)
            int[][] uncrossedLines = new int[m+1][n+1];
            for(int i=1;i<=m;i++){// traverse in A
                for(int j=1;j<=n;j++){ // traverse in B
                    if(A[i-1] == B[j-1]){
                        uncrossedLines[i][j] = 1 + uncrossedLines[i-1][j-1];
                    }else{
                        uncrossedLines[i][j] = Math.max(uncrossedLines[i-1][j], uncrossedLines[i][j-1]);
                    }
                }
            }
            return uncrossedLines[m][n];
        }
        return 0;
    }
}