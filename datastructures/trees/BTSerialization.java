package datastructures.trees;

import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class BTSerialization {
    /**
     * https://leetcode.com/problems/construct-string-from-binary-tree/
     * 
     * Construct String from Binary Tree
     * 
     * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".

Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        StringBuffer s = new StringBuffer();
        if(t!=null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            Set<TreeNode> visited = new HashSet<>();
            stack.push(t);
            while(!stack.empty()) {
                TreeNode node = stack.peek();
                if(visited.contains(node)) {
                    // end of a subtree, close )
                    s.append(")");
                    stack.pop();
                }
                else {
                    visited.add(node);
                    if(node.left == null && node.right == null) {
                        //leaf nodes reside within ()
                        s.append("(");
                        s.append(node.val);
                        s.append(")");
                        stack.pop();
                    } else if(node.left == null && node.right != null) {
                        // beginning of new subtree with empty left subtree
                        s.append("(");
                        s.append(node.val);
                        s.append("()");
                        stack.push(node.right);
                    } else {
                        // beginning of new subtree
                        s.append("(");
                        s.append(node.val);
                        if(node.right != null) {
                            stack.push(node.right);
                        }
                        if(node.left != null) {
                            stack.push(node.left);
                        }
                    }
                }
            }
            // deletes the first and last parenthesis for the whole tree
            s.deleteCharAt(0);
            s.deleteCharAt(s.length()-1);
        }
        return s.toString();
    }
    class Index{
        int val;
    }
    
    // just return root values 
    /**
     * https://leetcode.com/problems/find-duplicate-subtrees/
     * 
     * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

        Two trees are duplicate if they have the same structure with same node values.
     * @param root
     * @return
     */
    public List<Integer> findDuplicateSubtrees(TreeNode root) {
        List<Integer> duplicateRoots = new ArrayList<Integer>();
        String treeRep = tree2str(root);
        if(treeRep.length() > 0){
            Stack<Integer> startIndices = new Stack<Integer>();
            Set<String> duplicates = new HashSet<>();
            for(int i=0;i<treeRep.length();i++) {
                if(treeRep.charAt(i) == '(') {
                    startIndices.push(i);
                } else if(treeRep.charAt(i) == ')') {
                    int start = startIndices.pop();
                    String candidate = treeRep.substring(start,i+1);
                    if(!duplicates.contains(candidate) && i+1< treeRep.length()) {
                        String remaining = treeRep.substring(i+1);
                        if(remaining.contains(candidate)){
                            // add to set
                            duplicates.add(candidate);
                            // add to duplicate roots
                            int end = candidate.indexOf("(") < 0 ? candidate.indexOf(")"): candidate.length();
                            duplicateRoots.add(Integer.valueOf(candidate.substring(1, end)));
                        }
                    }
                }
            }
        }
        return duplicateRoots;
    }

    /**
     * Same problem, but return references to duplicate tree roots:
     * 
     * IN DFS once you get a representation for the node, keep counting the representations.
     * If it occurs twice, then it is a duplicate, add current node in final list.
     */
    public String dfsUtil(TreeNode root, List<TreeNode> duplicateRoots, Map<String, Integer> dupCount){
        if(root == null) {
            return "#";
        }
        String rep = root.val + "," + dfsUtil(root.left, duplicateRoots, dupCount) + "," + dfsUtil(root.right, duplicateRoots, dupCount);
        dupCount.put(rep, dupCount.getOrDefault(rep, 0) +1);
        if(dupCount.get(rep) == 2){ // add only the second time, any more its alraedy counted
            duplicateRoots.add(root);
        }
        return rep;
    }
    
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        List<TreeNode> duplicateRoots = new ArrayList<TreeNode>();
        Map<String, Integer> occurences = new HashMap<String, Integer>();
        dfsUtil(root, duplicateRoots, occurences);
        return duplicateRoots;
    }

    /**
     * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
     * 
     * We run a preorder depth first search on the root of a binary tree.

        At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

        If a node has only one child, that child is guaranteed to be the left child.

        Given the output S of this traversal, recover the tree and return its root.

        

        Example 1:
        Input: "1-2--3--4-5--6--7"
        Output: [1,2,5,3,4,6,7]
     * @param S
     * @param index
     * @param depth
     * @return
     */
    TreeNode buildTreeUtil(String S, Index index, int depth){
        int dashes =depth;
        int currIndex =index.val;
        while(dashes>0 && currIndex<S.length() && S.charAt(currIndex)== '-'){
            currIndex++;
            dashes--;
        }
        if(dashes !=0){
            // not expected depth dashes, so return
            return null;
        }
        int endIndex = S.indexOf('-',currIndex);
        if(endIndex == -1){
            endIndex = S.length();
        }
        // could have multiple digits
        TreeNode root = new TreeNode(Integer.valueOf(S.substring(currIndex, endIndex)));
        index.val=endIndex;
        root.left = buildTreeUtil(S, index, depth+1);
        root.right = buildTreeUtil(S, index, depth+1);
        return root;
    }
    public TreeNode recoverFromPreorder(String S) {
        return S.length() >0 ? buildTreeUtil(S, new Index(), 0) : null;
    }
}