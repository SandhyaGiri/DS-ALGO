package datastructures.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
 */
public  class RecoverBST {
    class MinMax{
        TreeNode min;
        TreeNode max;
    }
    // IDea1: bottom up bubbling of minmax nodes, where a root should be greater than left subtree's max, and smaller than right subtree's min.
    // if a violation occurs swap then -- DID NOT WORK FOR ALL CASES! (O(1) space)
    MinMax swapMisMatch(TreeNode root){
        if(root == null){
            return null;
        }
        MinMax left = swapMisMatch(root.left);
        MinMax right = swapMisMatch(root.right);
        if(left != null && root.val<left.max.val){
            // swap
            int temp = left.max.val;
            left.max.val=root.val;
            root.val=temp;
        }
        if(right != null && root.val>right.min.val){
            // swap
            int temp = right.min.val;
            right.min.val=root.val;
            root.val=temp;
        }
        MinMax parent = new MinMax();
        parent.min = left != null ? left.min : root;
        parent.max = right != null ? right.max : root;
        return parent;
    }
    // IDea2: top down flow of min, max ranges (not just range but also node itself), once a node is found that violates the range(eitehr min or max)
    // swap the current node with min or max node. Recur on left subtree and right subtree. -- DID NOT WORK FOR ALL CASES! (WAS DOING MORE SWAPS)
    // [2,3,1] ==> [2,1,3] and not [1,2,3] as returned by this algo (O(1) space )
    void swapMisMatchTopDown(TreeNode root, TreeNode min, TreeNode max){
        if(root== null){
            return;
        }
        if(min != null && root.val<min.val){
            int temp = min.val;
            min.val = root.val;
            root.val = temp;
        }
        if(max != null && root.val>max.val) {
            int temp=max.val;
            max.val=root.val;
            root.val=temp;
        }
        swapMisMatchTopDown(root.left, min, root);
        swapMisMatchTopDown(root.right, root, max);
    }

    // IDea3: Simple solution. Get inorder of current tree, sort it, traverse the tree again and replace node values.
    void inorder(TreeNode root, List<Integer> nodes){
        if(root == null){
            return;
        }
        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
    }
    class Index{
        int val;
    }
    void changeBST(TreeNode root, List<Integer> nodes, Index index){
        if(root == null){
            return;
        }
        changeBST(root.left, nodes, index);
        root.val=nodes.get(index.val);
        index.val+=1;
        changeBST(root.right, nodes, index);
    }
    public void recoverTree(TreeNode root) {
        //swapMisMatchTopDown(root, null, null);
        List<Integer> nodes = new ArrayList<Integer>();
        inorder(root, nodes);
        Collections.sort(nodes);
        changeBST(root, nodes, new Index());
    }
}