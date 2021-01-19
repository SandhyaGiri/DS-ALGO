package datastructures.arrays;

import java.util.*;

// https://leetcode.com/problems/diagonal-traverse/
public class ZigZagMatrix {
    class IndexPair{
        int i;
        int j;
        IndexPair(int x, int y){
            this.i=x;
            this.j = y;
        }
    }
    void addNodeToQueue(LinkedList<IndexPair> nodes, IndexPair curr, boolean addFirst){
        if(curr != null){
            if(addFirst){
                nodes.addFirst(curr);
            } else {
                nodes.addLast(curr);
            }
        }
    }
    /**
     * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

        Example:

        Input:
        [
        [ 1, 2, 3 ],
        [ 4, 5, 6 ],
        [ 7, 8, 9 ]
        ]

        Output:  [1,2,4,7,5,3,6,8,9]

        Idea: Use the same idea as for zigzag traversal of a tree, except that here for (i,j), we have two children (i+1,j) and (i, j+1).
        But since common children exist, only add both children to the queue for the first node. for remaining nodes, it is enough to
        add only the right child (left->right) or left child (right->left).
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if(m==0){
            return new int[]{};
        }
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        // double ended queue
        LinkedList<IndexPair> nodes = new LinkedList<>();
        nodes.add(new IndexPair(0,0));
        boolean leftToRight = true;
        while(!nodes.isEmpty()){
            int levelSize = nodes.size();
            boolean firstNode = false;
            for(int i=0;i<levelSize;i++){
                IndexPair node = leftToRight ? nodes.pollFirst() : nodes.pollLast();
                
                int x = node.i;
                int y = node.j;
                result.add(matrix[x][y]);
                IndexPair left = x+1 < m ? new IndexPair(x+1, y): null;
                IndexPair right = y+1 < n ? new IndexPair(x, y+1):null;
                // children
                if(leftToRight){
                    if(!firstNode){
                        firstNode = true;
                        addNodeToQueue(nodes, left, false);
                    }
                    addNodeToQueue(nodes, right, false);
                } else {
                    if(!firstNode){
                        firstNode = true;
                        addNodeToQueue(nodes, right, true);
                    }
                    addNodeToQueue(nodes, left, true);
                }
            }
            leftToRight = !leftToRight;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
