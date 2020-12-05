package problemsolving;

import java.util.*;

public class PascalsTriangle {
    // https://leetcode.com/problems/pascals-triangle/
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<>();
        if(numRows > 0){
             List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            result.add(firstRow);
            for(int i=1;i<numRows;i++){
                List<Integer> row = new ArrayList<>();
                if(result.get(i-1).size() >= 2){
                    List<Integer> previousRow = result.get(i-1);
                    for(int j=0;j<previousRow.size()-1;j++){
                        row.add(previousRow.get(j) + previousRow.get(j+1));
                    }
                }
                row.add(0, 1);
                row.add(1);
                result.add(row);
            }
        }
        return result;
    }

    // https://leetcode.com/problems/pascals-triangle-ii/
    public List<Integer> getRow(int rowIndex) {
        List<Integer> previousRow = new ArrayList<>();
        previousRow.add(1);
        List<Integer> currentRow = new ArrayList<>();
        if(rowIndex > 0){
            for(int i=1;i<=rowIndex;i++){
                if(previousRow.size() >= 2){
                    for(int j=0;j<previousRow.size()-1;j++){
                        currentRow.add(previousRow.get(j) + previousRow.get(j+1));
                    }
                }
                currentRow.add(0, 1);
                currentRow.add(1);
                previousRow.clear();
                previousRow.addAll(currentRow);
                currentRow.clear();   
            }
        }
        return previousRow;
    }
}