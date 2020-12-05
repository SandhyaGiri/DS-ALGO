package datastructures.lists;

// https://leetcode.com/problems/reorder-list/
public class ReorderList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
        reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

        You may not modify the values in the list's nodes, only nodes itself may be changed.

        Example 1:

        Given 1->2->3->4, reorder it to 1->4->2->3.
        Example 2:

        Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

        Idea: add all nodes to a double ended queue
        poll nodes from front and back alternatively and link them to previous node.
     * @param head
     */
    public void reorderList(ListNode head) {
        // add nodes to a double ended queue
        java.util.LinkedList<ListNode> dqueue = new java.util.LinkedList<>();
        ListNode curr = head;
        while(curr != null){
            dqueue.add(curr);
            curr = curr.next;
        }
        ListNode prev = null;
        boolean front = true; // if false, then polls an ele from end
        while(!dqueue.isEmpty()){
            ListNode node = front ? dqueue.pollFirst() : dqueue.pollLast();
            if(prev == null){
                prev = node;
                head = node;
            } else{
                prev.next = node;
                prev = node;
            }
            node.next = null;
            front = !front;
        }
    }
}