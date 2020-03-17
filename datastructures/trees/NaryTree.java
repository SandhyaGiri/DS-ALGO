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