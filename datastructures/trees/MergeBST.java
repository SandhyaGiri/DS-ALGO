package datastructures.trees;

public class MergeBST {

    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode small;
        TreeNode large;
        TreeNode(int ele) {
            this.data = ele;
            this.left = this.right = this.small = this.large = null;
        }
    }
    
    class BinarySearchTree {
        TreeNode root;
    
        TreeNode prev = null;
        TreeNode listHead = null;
    
        public void insert(int element) {
            TreeNode node = new TreeNode(element);
            if(root == null) {
                root = node;
            } else {
                TreeNode curr = root;
                TreeNode prev = null;
                while(curr != null) {
                    if(element > curr.data) {
                        prev = curr;
                        curr = curr.right;
                    } else {
                        prev = curr;
                        curr = curr.left;
                    }
                }
                if(element < prev.data) {
                    prev.left = node;
                } else {
                    prev.right = node;
                }
            }
        }
        void treeToList(TreeNode curr) {
            if(curr == null) {
                return;
            }
            treeToList(curr.left);
            if(prev == null) {
                listHead = curr;
                curr.small = null;
            } else {
                prev.large = curr;
                curr.small = prev;
            }
            prev = curr;
            treeToList(curr.right);
        }
    
        void printList(TreeNode head) {
            TreeNode curr = head;
            while(curr != null) {
                System.out.println(curr.data);
                curr = curr.large;
            }
        }
    }

    static void merge(TreeNode head1, TreeNode head2) {
        TreeNode curr1 = head1, curr2 = head2;
        while(curr1 != null || curr2 != null) {
            if(curr1 != null && curr2 == null) {
                while(curr1 != null) {
                    System.out.printf("%d ", curr1.data);
                    curr1 = curr1.large;
                }
            } else if(curr1 == null && curr2 != null) {
                while(curr2 != null) {
                    System.out.printf("%d ", curr2.data);
                    curr2 = curr2.large;
                }
            } else {
                if(curr1.data < curr2.data) {
                    System.out.printf("%d ", curr1.data);
                    curr1 = curr1.large;
                } else {
                    System.out.printf("%d ", curr2.data);
                    curr2 = curr2.large;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeBST m = new MergeBST();
        BinarySearchTree bst1 = m.new BinarySearchTree();
        bst1.insert(8);
        bst1.insert(2);
        bst1.insert(1);
        bst1.insert(10);
        bst1.treeToList(bst1.root);
        bst1.printList(bst1.listHead);

        BinarySearchTree bst2 = m.new BinarySearchTree();
        bst2.insert(5);
        bst2.insert(3);
        bst2.insert(0);
        bst2.treeToList(bst2.root);
        bst2.printList(bst2.listHead);

        merge(bst1.listHead, bst2.listHead);
    }
}