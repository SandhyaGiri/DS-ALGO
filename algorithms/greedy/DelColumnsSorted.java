package algorithms.greedy;

public class DelColumnsSorted {
    // https://leetcode.com/problems/delete-columns-to-make-sorted/
    /**
     * We are given an array A of N lowercase letter strings, all of the same length.

        Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

        For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"], and the remaining columns of A are ["b","v"], ["e","y"], and ["f","z"].  (Formally, the c-th column is [A[0][c], A[1][c], ..., A[A.length-1][c]]).

        Suppose we chose a set of deletion indices D such that after deletions, each remaining column in A is in non-decreasing sorted order.

        Return the minimum possible value of D.length.

        

        Example 1:

        Input: A = ["cba","daf","ghi"]
        Output: 1
        Explanation: 
        After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
        If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
        Example 2:

        Input: A = ["a","b"]
        Output: 0
        Explanation: D = {}
        Example 3:

        Input: A = ["zyx","wvu","tsr"]
        Output: 3
        Explanation: D = {0, 1, 2}
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        int n = A.length;
        int m = A[0].length(); // string length
        int numDeletions =0;
        for(int i=0;i<m;i++){ // each char across all strings
            Character prev = null;
            for(int j=0;j<n;j++){
                char c = A[j].charAt(i);
                // System.out.println(prev + "->" + c);
                if(prev != null && c < prev){
                    //System.out.println("Del col:" + i);
                    numDeletions+= 1; // delete this column
                    break;
                }
                prev = c;
                // as long >= prev char go on
            }
        }
        return numDeletions;
    }

    // https://leetcode.com/problems/delete-columns-to-make-sorted-ii/
    /**
     * We are given an array A of N lowercase letter strings, all of the same length.

        Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

        For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].

        Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

        Return the minimum possible value of D.length.

        
        Example 1:

        Input: ["ca","bb","ac"]
        Output: 1
        Explanation: 
        After deleting the first column, A = ["a", "b", "c"].
        Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
        We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
        Example 2:

        Input: ["xc","yb","za"]
        Output: 0
        Explanation: 
        A is already in lexicographic order, so we don't need to delete anything.
        Note that the rows of A are not necessarily in lexicographic order:
        ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
        Example 3:

        Input: ["zyx","wvu","tsr"]
        Output: 3
        Explanation: 
        We have to delete every column.
        
        Input: ["xycwb","xybzh","xyuza"]
        Output: 2
        Explanaition: del 3rd colum, then w,z,z is fine. delete last column.

        Idea: In contrast to previous problem, where we only columns (char seqs) need to be in non-decreasing
        order, here we need final strings in A to be in sorted order.

        This is similar to sorting a list of strings with additional constraints - no change in position of the strings,
        violating chars across all strings can be deleted.
        
        So we have to keep check each char across all strings until a strictly increasing set of chars is found. (a<b<c)
        because after this no matter the order of chars, the strings are already sorted => tie is broken.
        As long as there are some equal chars ( x,x,x,z) in this sequence, we need to accumulate the chars and we delete 
        a column when chars violate our non-decreasing principle. This violation can happen on first column or later when strings
        seen so far were equal but this particular char breaks the tie. 
        
        After a deletion, next set of chars are only compared with chars before the deleted column.
     * @param A
     * @return
     */
    public int minDeletionSize2(String[] A) {
        int n = A.length;
        int m = A[0].length(); // string length
        int numDeletions =0;
        StringBuilder[] stringsSeen = new StringBuilder[n];
        for(int i=0;i<n;i++){
            stringsSeen[i] = new StringBuilder();
        }
        for(int i=0;i<m;i++){ // each char across all strings
            Character prev = null;
            boolean isStritclyIncreasing = true;
            boolean colDeleted = false;
            for(int j=0;j<n;j++){
                char c = A[j].charAt(i);
                if(prev != null){
                    // if curr char is violating, check is string accumulated so far
                    // were equal, only then this col should be deleted.
                    if(c < prev && stringsSeen[j].toString().equals(stringsSeen[j-1].toString())){
                        //System.out.println("Del col:" + i);
                        isStritclyIncreasing = false;
                        colDeleted = true;
                        numDeletions+= 1; // delete this column
                        break;
                    }
                    else if(c == prev){
                        isStritclyIncreasing = false;
                    }
                }
                prev = c;
            }
            if(isStritclyIncreasing){
                break; // no tie any further (char sequences can be in any order)
            }
            // otherwise all chars are in non decreasing order (may have equal chars)
            else if(!colDeleted){
                for(int j=0;j<n;j++){
                    char c = A[j].charAt(i);
                    stringsSeen[j].append(c);
                }
            }
        }
        return numDeletions;
    }
}
