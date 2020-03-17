package datastructures.trees;

import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


public class NextRightPointerBT {
    public void linkChildren(Node root) {
        if(root != null) {
            if(root.left != null && root.right != null) {
                root.left.next = root.right;
            }
            linkChildren(root.left);
            linkChildren(root.right);
        }
    }
    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
    // Space: O(1)
    public Node connect(Node root) {
        if(root != null) {
            linkChildren(root);
            Node curr = root;
            Node first = root.left != null ? root.left : root.right;
            while(curr != null || first != null) {
                if(first == null && curr != null) { //register next level's first node
                    first = curr.left != null ? curr.left : curr.right;
                }
                if(curr == null || curr.next == null) { // end of this level
                    curr = first; // first node in next level
                    first = null;
                } else {
                    // check if curr has children, then we need to link next pointer
                    Node child = null;
                    if(curr.right != null) {
                        // to find next ptr for right child
                        child = curr.right;
                    }
                    else if(curr.left != null) {
                        // to find next ptr for left child
                        child = curr.left;
                    }

                    if(child != null) { // any child to be linked
                        // find candidate next from next node
                        Node temp = curr.next;
                        while(temp!= null && temp.left == null && temp.right == null) {
                            temp = temp.next;
                        }
                        // stop when temp node has atleast one child which is our nextptr
                        if(temp != null) {
                            child.next = temp.left != null ? temp.left : temp.right;
                        }
                        curr = temp;
                    } else {
                        curr = curr.next;
                    }
                }
            }   
        }
        return root;
    }


    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    // Queue solution: extra space O(w)
    public Node connect2(Node root) {
        if(root != null) {
            Queue<Node> nodes = new LinkedList<Node>();
            nodes.add(root);
            while(!nodes.isEmpty()){
                int levelSize = nodes.size();
                Node prev = null;
                for(int i=0;i<levelSize;i++) {
                    Node curr = nodes.poll();
                    if(prev == null) { // first node in level
                        prev = curr;
                    } else {
                        prev.next = curr;
                        prev = curr;
                    }
                    // enqueue children nodes
                    if(curr.left != null) {
                        nodes.add(curr.left);
                    }
                    if(curr.right != null) {
                        nodes.add(curr.right);
                    }
                }
            }
        }
        return root;
    }
}