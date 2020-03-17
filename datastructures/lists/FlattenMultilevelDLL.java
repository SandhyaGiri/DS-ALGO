package datastructures.lists;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
public class FlattenMultilevelDLL {
    /**
     * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
     * 
     * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

        Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

        Example 1:

        Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
        Output: [1,2,3,7,8,11,12,9,10,4,5,6]
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        Stack<Node> parents = new Stack<Node>();
        Node parent = null;
        Node curr = head;
        Node last = null;
        while(curr != null || parent != null){
            // find the node with children in this level
            while(curr != null && curr.child == null){
                last = curr;
                curr = curr.next;
            }
            if(curr == null){
                if(parent != null){
                    // no node with children in this level, ready for merge after parent if one exists
                    Node next = parent.next; // save next ptr
                    parent.next = parent.child;
                    parent.next.prev = parent;
                    last.next = next;
                    if(next != null){
                        next.prev = last;   
                    }
                    parent.child = null;
                    // move to next node in this level
                    curr = next;
                    // reset parent pointer
                    parent = parents.empty() ? null : parents.pop();   
                }
            } else {
                // node with child is now at curr
                if(parent == null){
                    parent = curr;
                } else {
                    parents.push(parent);
                    parent = curr;
                }
                curr = curr.child; // move to next level
            }
        }
        // head never changed as even if it has child, sub list will be inserted after head!
        return head; 
    }
}