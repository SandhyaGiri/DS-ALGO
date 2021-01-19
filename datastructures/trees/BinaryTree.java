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

    // https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
    class Context{
        int maxDepth;
        TreeNode subtreeRoot;
        Context(){
            maxDepth =0;
            subtreeRoot = null;
        }
    }
    int leafDepth(TreeNode root, int level, Context context){
        if(root == null){
            return -1;
        }
        if(root.left == null && root.right == null){
            if(level >= context.maxDepth){
                context.maxDepth = level;
                context.subtreeRoot = root; //  node is its own LCA
            }
            return level;
        }
        int leftLeafDepth = leafDepth(root.left, level+1, context);
        int rightLeafDepth = leafDepth(root.right, level+1, context);
        // this check succeeds only at a higher up LCA (not leaf nodes)
        if(leftLeafDepth == rightLeafDepth && rightLeafDepth == context.maxDepth){
            context.subtreeRoot = root;
        } // don't update if it is not a candidate LCA
        return Math.max(leftLeafDepth, rightLeafDepth);
    }
    /**
     * Given the root of a binary tree, the depth of each node is the shortest distance to the root.

        Return the smallest subtree such that it contains all the deepest nodes in the original tree.

        A node is called the deepest if it has the largest depth possible among any node in the entire tree.

        The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.

        Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

        Idea:
        Same as LCA of given two nodes. But we need to know the depths of leaf nodes on left and right subtree.
        leaves with max depth are themselves LCA. At non-leaf nodes, whose left leaf and right leaf depth match
        the current max Depth are also candidate LCAs.
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Context context = new Context();
        leafDepth(root, 0, context);
        return context.subtreeRoot;
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
        return root.val + lexcess+ rexcess -1; // this node needs a 1 hence subtract 1
    }
    public int distributeCoins(TreeNode root) {
        moves =0;
        dfsUtil(root);
        return moves;
    }

    /**
     * Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
     * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
        Output: true
        Explanation: 
        The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure). 
        Other valid sequences are: 
        0 -> 1 -> 1 -> 0 
        0 -> 0 -> 0

        Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
        Output: false 
        Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
     * @param root
     * @param arr
     * @param index
     * @return
     */
    boolean dfsUtil(TreeNode root, int[] arr, int index){
        if(index == arr.length-1){
            return (root != null && root.val == arr[index] && root.left == null && root.right == null);
        }
        if(root == null || root.val != arr[index]){
            return false;
        }
        return dfsUtil(root.left, arr, index+1) || dfsUtil(root.right, arr, index+1);
    }
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfsUtil(root, arr, 0);
    }

    // Invert a binary tree (reverse left and right children bottom up)
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    // https://leetcode.com/problems/sum-of-left-leaves/
    /**
     * Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        // left leaf node
        int lSum = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            lSum = root.left.val;
        } else {
            lSum = sumOfLeftLeaves(root.left);
        }
        int rSum = sumOfLeftLeaves(root.right);
        return lSum + rSum;
    }

    int getLeafSumAtDepth(TreeNode root, int level, int currLevel){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){ // leaf
            return currLevel == level ? root.val : 0;
        }
        return getLeafSumAtDepth(root.left, level, currLevel+1) + getLeafSumAtDepth(root.right, level, currLevel+1);
    }

    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    /**
     * Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

          Idea: In the final tree, there is no left pointer. Make a flattened left subtree as right subtree for current
          node. Then attach the flattened right subtree to end of this newly atatched left subtree (only traverse right
          pointers). If no left subtree exists, the flattend right subtree becomes the new right child for current node.
          Also elimate the already existing left node pointer.
     * @param root
     * @return
     */
    TreeNode getFlatTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode flatLeft = getFlatTree(root.left);
        TreeNode flatRight = getFlatTree(root.right);
        // get last node in flatLeft
        TreeNode prev = null, curr=flatLeft;
        while(curr != null){
            prev = curr;
            curr = curr.right; // only right pointers in the flat subtree
        }
        if(prev != null){
            prev.right = flatRight;
        }
        // if left tree didn't exist, flattened right tree is our next right.
        root.right = flatLeft == null ? flatRight : flatLeft;
        root.left = null; // cut the already existing left subtree
        return root;
    }
    public void flatten(TreeNode root) {
        getFlatTree(root);
    }

    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    int rootIndex = 0;
    TreeNode buildTreeUtil(int[] preorder, int[] inorder, int l, int r){
        if(l > r || rootIndex > preorder.length-1){
            return null;
        }
        TreeNode root = new TreeNode(preorder[rootIndex]);
        rootIndex+=1;
        if(l==r){
            return root;
        }
        // find root index in inorder array (can use bsearch only for BST as inorder is sorted)
        int index=l;
        for(;index<=r;index++){
            if(inorder[index] == root.val){
                break;
            }
        }
        root.left = buildTreeUtil(preorder, inorder, l, index-1);
        root.right = buildTreeUtil(preorder, inorder, index+1, r);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeUtil(preorder, inorder, 0, inorder.length-1);
    }

    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    int longestPathLen = 0;
    void dfs(TreeNode root, Boolean fromLeft, int pathLength){
        if(root == null){
            return;
        }
        if(pathLength > longestPathLen){
            longestPathLen = pathLength;
        }
        if(fromLeft == null) { // initial
            dfs(root.left, false, 1);
            dfs(root.right, true, 1);
        } else if(fromLeft){ // from left, go left extend path
            dfs(root.left, false, pathLength+1);
            dfs(root.right, true, 1); // reset path on right
        } else { // from right, go right and extend path
            dfs(root.left, false, 1);
            dfs(root.right, true, pathLength+1);
        }
    }
    /**
     * Given a binary tree root, a ZigZag path for a binary tree is defined as follow:

        Choose any node in the binary tree and a direction (right or left).
        If the current direction is right then move to the right child of the current node otherwise move to the left child.
        Change the direction from right to left or right to left.
        Repeat the second and third step until you can't move in the tree.
        Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

        Return the longest ZigZag path contained in that tree.
    
     * @param root
     * @return
     */
    public int longestZigZag(TreeNode root) {
        dfs(root, null, 0);
        return longestPathLen;
    }

    // https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
    int numPaths;
    boolean canRearrangeAsPalindromeSequence(Map<Integer, Integer> occurences){
        // all even counts or one odd count
        boolean oddCountPresent = false;
        for(int key: occurences.keySet()){
            int count = occurences.get(key);
            if(count % 2 != 0){
                if(oddCountPresent){
                    return false;
                } else {
                    oddCountPresent = true;
                }
            }
        }
        return true;
    }
    void preOrder(TreeNode root, Map<Integer, Integer> occurences){
        if(root == null){
            return;
        }
        occurences.put(root.val, occurences.getOrDefault(root.val, 0)+1);
        if(root.left == null && root.right == null){
            if(canRearrangeAsPalindromeSequence(occurences)){
                numPaths+=1;
            }
        }
        preOrder(root.left, occurences);
        preOrder(root.right, occurences);
        // backtracking
        occurences.put(root.val, occurences.getOrDefault(root.val, 0)-1);
    }
    /**
     * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree
     * is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.

        Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

     * @param root
     * @return
     */
    public int pseudoPalindromicPaths (TreeNode root) {
        Map<Integer, Integer> occurences = new HashMap<>();
        preOrder(root, occurences);
        return numPaths;
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