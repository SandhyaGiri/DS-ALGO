package datastructures.trees;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
public class NaryTree {
    /**
     * https://leetcode.com/problems/n-ary-tree-preorder-traversal
     * 
     * Given an n-ary tree, return the preorder traversal of its nodes' values.
     * 
        Follow up:

        Recursive solution is trivial, could you do it iteratively?
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> nodes = new LinkedList<Integer>();
        if(root != null){
            Stack<Node> s = new Stack<Node>();
            s.push(root);
            while(!s.empty()){
                Node node = s.pop();
                nodes.add(node.val);
                if(node.children != null){
                    // process children from end
                    for(int i=node.children.size()-1;i>=0;i--){
                        s.push(node.children.get(i));
                    }   
                }
            }
        }
        return nodes;
    }

    /**
     * Maximum Depth of N-ary Tree
     * 
     * Given a n-ary tree, find its maximum depth.
    The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        int maxSubTreePath = 0;
        for(Node node: root.children){
            maxSubTreePath = Math.max(maxSubTreePath, maxDepth(node));
        }
        return maxSubTreePath+1;
    }
    
    /**
     * https://leetcode.com/problems/n-ary-tree-postorder-traversal
     * 
     * Given an n-ary tree, return the postorder traversal of its nodes' values.
     * 
     * @param root
     * @param nodes
     */
    void dfs(Node root, List<Integer> nodes){
        if(root == null){
            return;
        }
        if(root.children == null){
            nodes.add(root.val);
            return;
        }
        for(Node child:root.children){
            dfs(child, nodes);
        }
        nodes.add(root.val);
    }
    public List<Integer> postorder(Node root) {
        List<Integer> nodes = new ArrayList<Integer>();
        dfs(root, nodes);
        return nodes;
    }
}