package datastructures.trees;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ModeBST {
    public void getInorder(TreeNode root, Map<Integer, Integer> nodes){
        if(root == null) {
            return;
        }
        getInorder(root.left, nodes);
        if(nodes.containsKey(root.val)){
            nodes.put(root.val, nodes.get(root.val)+1);
        } else {
            nodes.put(root.val, 1);
        }
        getInorder(root.right, nodes);
    }
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> nodes = new HashMap<Integer, Integer>();
        getInorder(root, nodes);
        
        // we have a count map, we need to find mode
        Set<Integer> modeset = new HashSet<Integer>();
        int MAX_count = Integer.MIN_VALUE;
        
        for(Map.Entry<Integer,Integer> entry: nodes.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if(val > MAX_count){
                modeset.clear();
                modeset.add(key);
                MAX_count = val;
            } else if(val == MAX_count) {
                modeset.add(key);
            }
        }
        
        return modeset.stream().mapToInt(Number::intValue).toArray();
    }
}