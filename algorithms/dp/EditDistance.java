package algorithms.dp;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        // both words are empty
        if((word1 == null || word1.length() == 0) && (word2 == null || word2.length() == 0)) {
            return 0;
        }
        if(word1 == null || word1.length() == 0) // only word1 is empty
            return word2.length();
        else if(word2 == null || word2.length() == 0) // only word2 is empty
            return word1.length();
        
        // both words are non-empty
        int m = word1.length();
        int n = word2.length();
        int[][] editDis = new int[m+1][n+1];
        
        // Initialize first row, col
        for(int j=0;j<=n;j++) {
            editDis[0][j] = j; // need to insert j chars to empty string, to get curr string
        }
        for(int i=0;i<=m;i++) {
            editDis[i][0] = i; // need to delete i chars from the string, to get empty string
        }
        
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                int ins_cost = editDis[i][j-1] + 1;
                int del_cost = editDis[i-1][j] + 1;
                int sub_cost = editDis[i-1][j-1] + (word1.charAt(i-1) != word2.charAt(j-1) ? 1 : 0);
                editDis[i][j] = Math.min(ins_cost, Math.min(del_cost, sub_cost));
            }
        }
        
        return editDis[m][n];
    }
}