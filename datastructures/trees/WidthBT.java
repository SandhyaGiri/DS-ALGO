package datastructures.trees;

import java.util.*;

// https://leetcode.com/problems/maximum-width-of-binary-tree/

public class WidthBT {
    /**
     * 
     * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

        The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

        Example 1:

        Input: 

                1
                /   \
                3     2
            / \     \  
            5   3     9 

        Output: 4
        Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

        Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        int width = Integer.MIN_VALUE;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        if(root != null){
            nodes.add(root);
            while(!nodes.isEmpty()){
                // prune left null nodes (so that we don't keep enqueing their null children)
                while(nodes.size()> 0 && nodes.get(0) == null){
                    nodes.pollFirst();
                }
                // prune on right side too
                while(nodes.size()> 0 && nodes.get(nodes.size()-1) == null){
                    nodes.pollLast();
                }
                int levelSize = nodes.size();
                //System.out.println(levelSize);
                if(levelSize > width){
                    width = levelSize;
                }
                for(int i=0;i<levelSize;i++){
                    TreeNode node = nodes.poll();
                    if(node == null){
                        nodes.add(null);
                        nodes.add(null);
                    }else{
                        nodes.add(node.left);
                        nodes.add(node.right);
                    }
                }
            }
            return width;
        }
        return 0;
    }
}