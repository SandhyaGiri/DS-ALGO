package datastructures.trees;

import java.util.*;

class TreeNode {
    int element;
    TreeNode left;
    TreeNode right;
    TreeNode(int element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    TreeNode root;

    public void insert(int element) {
        TreeNode node = new TreeNode(element);
        if(root == null) {
            root = node;
        } else {
            TreeNode curr = root;
            TreeNode prev = null;
            while(curr != null) {
                if(element > curr.element) {
                    prev = curr;
                    curr = curr.right;
                } else {
                    prev = curr;
                    curr = curr.left;
                }
            }
            if(element < prev.element) {
                prev.left = node;
            } else {
                prev.right = node;
            }
        }
    }

    public boolean search(int element) {
        TreeNode curr = root;
        while(curr != null) {
            if(curr.element == element) {
                return true;
            }
            if(element > curr.element) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return false;
    }
    public void getInorder(TreeNode node, List<Integer> nodes) {
        if(node == null) {
            return;
        }
        getInorder(node.left, nodes);
        nodes.add(node.element);
        getInorder(node.right, nodes);
    }

    public int getHeight(TreeNode node) {
        if(node == null){
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public void mirror(TreeNode node) {
        if(node == null) {
            return;
        }
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        mirror(node.left);
        mirror(node.right);
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if(root == null) {
            return levelOrder;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            List<Integer> nodes = new ArrayList<Integer>();
            int n = q.size();
            for(int i=0;i<n;i++) {
                TreeNode node = q.poll();
                nodes.add(node.element);
                if(node.left != null) {
                    q.add(node.left);
                }
                if(node.right != null) {
                    q.add(node.right);
                }
            }
            levelOrder.add(nodes);
        }
        return levelOrder;
    }

    /**
     * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

        The binary search tree is guaranteed to have unique values.
        Note:
            The number of nodes in the tree is at most 10000.
            The final answer is guaranteed to be less than 2^31.
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSum(TreeNode root, int L, int R) {
        if(root == null) {
            return 0;
        }
        if(root.element >=L && root.element <=R) {
            return root.element + rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
        }
        return rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
    }

    /**
     * Lowest common Ancestor for two nodes in a BST. Iterative solution.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = null;
        while(root != null) {
            if(root.element > p.element && root.element > q.element) {
                root = root.left;
            }
            else if(root.element < p.element && root.element < q.element) {
                root = root.right;
            }
            else {
                lca = root;
                break;
            }
        }
        return lca;
    }

    /**
     * Kth Smallest Element in a BST => kth node in the inorder traversal
     * 
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
     * 
     */
    class Node {
        int val;
        int index;
    }
    public void kthSmallestNode(TreeNode root, Node node, int k) {
        if(root != null) {
            kthSmallestNode(root.left, node, k);
            node.index+=1;
            if(node.index ==k) {
                node.val = root.element;
            }
            kthSmallestNode(root.right, node, k);
        }
    }
    public int kthSmallest(TreeNode root, int k) {
        Node kthNode = new Node();
        kthSmallestNode(root, kthNode, k);
        return kthNode != null ? kthNode.val : -1;
    }

    /**
     * Binary Search Tree to Greater Sum Tree
     * 
     * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
     * 
     * Given the root of a binary search tree with distinct values, 
     * modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.
     */
    int sum=0;
    void bstToGstUtil(TreeNode root){
        if(root == null){
            return;
        }
        bstToGst(root.right);
        root.element += sum;
        sum = root.element;
        bstToGst(root.left);
    }
    public TreeNode bstToGst(TreeNode root) {
        bstToGstUtil(root);
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(10);
        List<Integer> nodes = new ArrayList<>();
        tree.getInorder(tree.getRoot(),nodes);
        System.out.println(Arrays.toString(nodes.toArray()));
        tree.mirror(tree.getRoot());
        nodes = new ArrayList<>();
        tree.getInorder(tree.getRoot(),nodes);
        System.out.println(Arrays.toString(nodes.toArray()));
        System.out.println(tree.search(20));
        System.out.println(tree.getHeight(tree.getRoot()));
        List<List<Integer>> levelOrder = tree.levelOrder(tree.root);
        System.out.println(levelOrder.toString());
    }
}