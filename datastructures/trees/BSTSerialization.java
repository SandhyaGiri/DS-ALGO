package datastructures.trees;

import java.util.*;
import java.util.stream.*;

public class BSTSerialization {
    // Encodes a tree to a single string.
    // does iterative pre-order to persist the tree
    public String serialize(TreeNode root) {
        StringBuffer s = new StringBuffer();
        if(root != null) {
            Stack<TreeNode> nodes = new Stack<>();
            nodes.push(root);
            while(!nodes.empty()){
                TreeNode curr = nodes.pop();
                s.append(curr.val);
                s.append(" ");
                if(curr.right != null) {
                    nodes.push(curr.right);
                }
                if(curr.left != null) {
                    nodes.push(curr.left);
                }
            } 
            return s.toString();
        }
        return null;
    }
    
    public TreeNode buildTreeFromPreorder(int[] nodes, int l, int r) {
        if(l>r) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[l]));
        if(l==r) {
            return root;
        }
        // find nextgreater ele index
        int i=l+1;
        for(;i<=r;i++) {
            if(nodes[i]>root.val) {
                break;
            }
        }
        root.left = buildTreeFromPreorder(nodes, l+1,i-1);
        root.right = buildTreeFromPreorder(nodes, i, r);
        return root;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data != null) {
            String[] nodes = data.split(" ");
            int[] treenodes = Stream.of(nodes).mapToInt(Integer::valueOf).toArray();
            return buildTreeFromPreorder(treenodes, 0, treenodes.length-1);   
        }
        return null;
    }
}