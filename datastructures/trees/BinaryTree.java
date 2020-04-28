package datastructures.trees;

import java.util.*;
import java.util.stream.Collectors;

class Node {
    int data;
    Node left;
    Node right;
    Node(int ele) {
        this.data = ele;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    static int index = 0;
    Node root = null;

    /**
     * Insertion into BT, needs to traverse the tree in level order.
     * The first node that we find with empty left or right child is where the new key will be inserted.
     * 
     * @param ele
     */
    public void insert(int ele){
        if(root == null) {
            root = new Node(ele);
            return;
        }
        Node newNode = new Node(ele);
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        boolean inserted = false;
        while(!q.isEmpty() && !inserted) {
            int levelSize = q.size();
            for(int i=0;i<levelSize;i++) {
                Node node = q.poll();
                if(node.left == null) {
                    inserted = true;
                    node.left = newNode;
                    break;
                } else if(node.right == null) {
                    inserted = true;
                    node.right = newNode;
                    break;
                } else {
                    q.add(node.left);
                    q.add(node.right);
                }
            }
        }
    }

    public int getCount(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + getCount(node.left) + getCount(node.right);
    }

    public void getInorder(Node node, int[] arr) {
        if(node == null) {
            return;
        }
        getInorder(node.left, arr);
        arr[index++] = node.data;
        getInorder(node.right, arr);
    }

    public void storeInorder(Node node, int[] arr) {
        if(node == null) {
            return;
        }
        storeInorder(node.left, arr);
        node.data = arr[index++];
        storeInorder(node.right, arr);
    }

    public List<Integer> getInorderIterative(Node root) {
        List<Integer> inorder = new ArrayList<Integer>();
        
        // Morris traversal - iterative in order traversal
        Node curr = root;
        while(curr != null) {
            if(curr.left == null) {
                inorder.add(curr.data);
                curr = curr.right;
            } else {
                // Find the inorder predecessor
                Node prev = curr.left;
                while(prev != null && prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if(prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    inorder.add(curr.data);
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return inorder;
    }

    /**
     * Returns the Lowest common ancestor in a Binary Tree (not BST - where complexity was O(h)).
     * 
     * Here complexity: O(n)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root == null) {
            return null;
        }
        if(root.data == p.data || root.data == q.data) {
            return root;
        }
        Node lca_left = lowestCommonAncestor(root.left, p, q);
        Node lca_right = lowestCommonAncestor(root.right, p, q);
        
        if(lca_left != null && lca_right != null) {
            return root;
        } else if(lca_left == null) {
            return lca_right;
        } else {
            return lca_left;
        }
    }


    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     * 
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>(); // important to store as LinkedList<List<Integer>>, as we want to use deque methods
        if(root != null){
            Queue<TreeNode> nodes = new LinkedList<>();
            nodes.add(root);
            while(!nodes.isEmpty()){
                int levelSize = nodes.size();
                List<Integer> levelNodes = new LinkedList<>();
                for(int i=0;i<levelSize;i++) {
                    TreeNode node = nodes.poll();
                    levelNodes.add(node.val);
                    if(node.left != null){
                        nodes.add(node.left);
                    }
                    if(node.right != null){
                        nodes.add(node.right);
                    }
                }
                result.addFirst(levelNodes); // deque interface methods implemented by LinkedList
            }   
        }
        return result;
    }

    /**
     * Print the tree in spiral form or zig zag level order.
     * 
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if(root == null) {
            return levelOrder;
        }
        Deque<TreeNode> q = new ArrayDeque<TreeNode>();
        boolean isLeftToRight = true;
        q.addLast(root);
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> nodes = new ArrayList<Integer>();
            for(int i=0;i<n;i++) {
                if(isLeftToRight) {
                    TreeNode node = q.removeFirst();
                    nodes.add(node.val);
                    if(node.left != null) {
                        q.addLast(node.left);
                    }
                    if(node.right != null) {
                        q.addLast(node.right);
                    }
                } else {
                   TreeNode node = q.removeLast();
                    nodes.add(node.val);
                    if(node.right != null) {
                        q.addFirst(node.right);
                    }
                    if(node.left != null) {
                        q.addFirst(node.left);
                    }
                }
            }
            isLeftToRight = !isLeftToRight;
            levelOrder.add(nodes);
        }
        return levelOrder;
    }

    /**
     * Given a binary tree, determine if it is a complete binary tree.

    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, 
    is completely filled, and all nodes in the last level are as far left as possible. 
    It can have between 1 and 2h nodes inclusive at the last level h.
     * @param root
     * @return
     */
    public boolean isValidNonFullNode(Node root) {
        return (root.left == null && root.right == null) || (root.left != null && root.right==null);
    }
    public boolean isCompleteTree(Node root) {
        Queue<Node> nodes = new LinkedList<Node>();
        nodes.add(root);
        boolean nonFullNodeSeen = false;
        boolean isComplete = true;
        while(!nodes.isEmpty()) {
            int size = nodes.size();
            for(int i=0;i<size;i++) {
                Node node = nodes.poll();
                if(nonFullNodeSeen && (node.left != null || node.right != null)) { // after a non-full node, all nodes seen should be leaf nodes
                    isComplete = false;
                    break;
                }
                if(node.left != null && node.right != null) { // full nodes directly go into queue
                    nodes.add(node.left);
                    nodes.add(node.right);
                } else if(isValidNonFullNode(node)){ // leaf nodes or left heavy nodes are valid non-full nodes.
                    nonFullNodeSeen = true;
                    if(node.left != null) {
                        nodes.add(node.left);
                    }
                } else {
                    isComplete = false;
                    break;
                }
            }
            if(!isComplete) {
                break;
            }
        }
        return isComplete;
    }

    boolean isComplete(Node root, int index, int number_nodes) 
    { 
        // An empty tree is complete 
        if (root == null)         
           return true; 
   
        // If index assigned to current node is more than 
        // number of nodes in tree, then tree is not complete 
        if (index >= number_nodes) 
           return false; 
   
        // Recur for left and right subtrees 
        return (isComplete(root.left, 2 * index + 1, number_nodes) 
            && isComplete(root.right, 2 * index + 2, number_nodes)); 
   
    }
    
    // using level order traversal, maintain first node and return last level's first node
    // https://leetcode.com/problems/find-bottom-left-tree-value/
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        int firstNode = 0;
        while(!nodes.isEmpty()) {
            int levelSize = nodes.size();
            for(int i=0;i<levelSize;i++) {
                TreeNode node = nodes.poll();
                if(i==0) {
                    firstNode = node.val;
                }
                if(node.left != null) {
                    nodes.add(node.left);
                }
                if(node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
        return firstNode;
    }

    /** https://leetcode.com/problems/symmetric-tree
     * symmetric about the root - ie whose left and right subtrees are mirror of each other.
     */
    public boolean isMirror(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return true;
        }
        if(root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if(root != null)
            return isMirror(root.left, root.right);
        return true;
    }
    
    /**
     * https://leetcode.com/problems/binary-tree-right-side-view/
     * 
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     * 
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> lastNodes = new LinkedList<Integer>();
        if(root != null) {
            Queue<TreeNode> nodes = new LinkedList<TreeNode>();
            nodes.add(root);
            while(!nodes.isEmpty()) {
                int levelSize = nodes.size();
                for(int i=0;i<levelSize;i++) {
                    TreeNode curr = nodes.poll();
                    // save the last node
                    if(i == levelSize-1) {
                        lastNodes.add(curr.val);
                    }
                    if(curr.left != null) {
                        nodes.add(curr.left);
                    }
                    if(curr.right != null) {
                        nodes.add(curr.right);
                    }
                }
            }
        }
        return lastNodes;
    }

    /**
     * https://leetcode.com/problems/leaf-similar-trees/
     * 
     * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     * @param root
     * @param leaves
     */
    void getLeafSequence(TreeNode root, List<Integer> leaves){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            leaves.add(root.val);
        }
        getLeafSequence(root.left, leaves);
        getLeafSequence(root.right, leaves);
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1= new LinkedList<Integer>();
        List<Integer> leaves2 = new LinkedList<Integer>();
        getLeafSequence(root1, leaves1);
        getLeafSequence(root2, leaves2);
        boolean isSimilar = true;
        if(leaves1.size() == leaves2.size()){
            for(int i=0;i<leaves1.size();i++) {
                if(leaves1.get(i) != leaves2.get(i)){
                    isSimilar= false;
                    break;
                }
            }
        } else {
            isSimilar = false;
        }
        return isSimilar;
    }

    /**
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     * 
     * Given a binary tree, find its minimum depth.

        The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

        Idea: min depth occurs at the shallowest leaf(goal) node. To find such node, BFS is helpful (faster than DFS), so use level order traversal
            and stop when first leaf node is found. level at that time is the min depth.
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root != null) {
            Queue<TreeNode> nodes = new LinkedList<TreeNode>();
            nodes.add(root);
            int level =0;
            boolean depthFound = false;
            while(!nodes.isEmpty() && !depthFound){
                int levelSize = nodes.size();
                level++;
                for(int i=0;i<levelSize;i++) {
                    TreeNode node = nodes.poll();
                    if(node.left == null && node.right == null){
                        depthFound=true;
                        break;
                    }
                    if(node.left != null){
                        nodes.add(node.left);
                    }
                    if(node.right != null){
                        nodes.add(node.right);
                    }
                }
            }
            return level;
        }
        return 0;
    }

    /**
     * https://leetcode.com/problems/distribute-coins-in-binary-tree/
     * 
     * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

        In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

        Return the number of moves required to make every node have exactly one coin.
     */
    int moves =0;
    int dfsUtil(TreeNode root){
        if(root==null){
            return 0;// no excess coins to spare, as no coins present
        }
        int lexcess = dfsUtil(root.left);
        int rexcess = dfsUtil(root.right);
        moves+= Math.abs(lexcess)+ Math.abs(rexcess);
        return root.val + lexcess+ rexcess -1;
    }
    public int distributeCoins(TreeNode root) {
        moves =0;
        dfsUtil(root);
        return moves;
    }
    
    /**
     * https://leetcode.com/problems/longest-univalue-path/
     * 
     * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

        The length of path between two nodes is represented by the number of edges between them.
     */
    int maxPathLen = Integer.MIN_VALUE;
    int longestUnivaluePathUtil(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) {
            // leaf node
            return 0;
        }
        int lenLeft = longestUnivaluePathUtil(root.left);
        int lenRight = longestUnivaluePathUtil(root.right);
        int maxLenLeft = 0, maxLenRight = 0;
        if(root.left != null && root.val == root.left.val){
            maxLenLeft += lenLeft +1;
        }
        if(root.right != null && root.val == root.right.val) {
            maxLenRight += lenRight +1;
        }
        maxPathLen = Math.max(maxPathLen, maxLenLeft+maxLenRight); // path going through this node (root)
        return Math.max(maxLenLeft, maxLenRight); // further you can only extend one of the paths left or right not both
    }
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathUtil(root);
        return maxPathLen == Integer.MIN_VALUE ? 0 : maxPathLen;
    }
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(2);
        tree.insert(7);
        tree.insert(8);
        tree.insert(4);
        int[] arr = new int[tree.getCount(tree.root)];
        tree.getInorder(tree.root, arr);
        System.out.println(Arrays.toString(arr));
        List<Integer> nodes = tree.getInorderIterative(tree.root);
        System.out.println(Arrays.toString(nodes.toArray()));
        Arrays.sort(arr);
        index = 0;
        tree.storeInorder(tree.root, arr);
        index = 0;
        arr = new int[tree.getCount(tree.root)];
        tree.getInorder(tree.root, arr);
        System.out.println(Arrays.toString(arr));
    }
}

class VerticalLevelOrderTraversal{
    /**
     * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
     * 
     * Given a binary tree, return the vertical order traversal of its nodes values.
     * Given a node, left child is at horizontal dis -1, and right child at horizontal dis +1.
     * Root node is at horizontal distance 0.
     */
    class Node implements Comparable<Node>{
        int val;
        int depth;
        Node(int value, int depth) {
            this.val = value;
            this.depth = depth;
        }
        public int compareTo(Node other){
            return this.depth == other.depth ? this.val -other.val : this.depth-other.depth;
        }
    }
    // within each hdis level, all nodes should be arranged in increasing order of their depths, if two nodes occur at the same depth, then
    // they need to be arrnaged based on increasing order of their values -> hence the comparable Node and the TreeSet
    void preOrder(TreeNode root, int hdis, int depth, Map<Integer, Set<Node>> nodes) {
        if(root == null) {
            return;
        }
        Set<Node> hdisNodes = nodes.get(hdis);
        if(hdisNodes == null) {
            hdisNodes = new TreeSet<Node>();
            hdisNodes.add(new Node(root.val, depth));
            nodes.put(hdis, hdisNodes);
        } else {
            hdisNodes.add(new Node(root.val, depth));
        }
        preOrder(root.left, hdis-1, depth+1, nodes);
        preOrder(root.right, hdis+1, depth+1, nodes);
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, Set<Node>> nodes = new HashMap<>();
        preOrder(root, 0, 0, nodes);
        List<List<Integer>> traversal = new LinkedList<>();
        Set<Integer> hdisLevels = new TreeSet<Integer>(nodes.keySet());
        for(int hdisLevel: hdisLevels){
            Set<Node> levelNodes = nodes.get(hdisLevel);
            traversal.add(levelNodes.stream().map(x -> x.val).collect(Collectors.toList()));
        }
        return traversal;
    }
}