package datastructures.trees;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TwoSumBST {
    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }
    
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        inorderTraversal(root, list);
        int l=0,r=list.size()-1;
        while(l<r) {
            int left = list.get(l);
            int right = list.get(r);
            if(left + right == k) {
                return true;
            } else if(left + right < k) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }
}