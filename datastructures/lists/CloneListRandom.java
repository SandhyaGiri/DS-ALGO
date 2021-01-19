package datastructures.lists;

import java.util.*;

// https://leetcode.com/problems/copy-list-with-random-pointer/
public class CloneListRandom {
    class Node{
        int val;
        Node next;
        Node random;
        Node(int val){
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    Node getNodeIfExists(Map<Node, Node> oldToClonedNodes, Node curr){
        Node clonedNode = null;
        if(curr != null){
            if(oldToClonedNodes.containsKey(curr)){
                clonedNode = oldToClonedNodes.get(curr);
            } else {
                clonedNode = new Node(curr.val);
                // add newly created node to existing map
                oldToClonedNodes.put(curr, clonedNode);
            }
        }
        return clonedNode;
    }
    /**
     * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

        Return a deep copy of the list.

        The Linked List is represented in the input/output as a list of n nodes.

        Idea: Maintain a map of existing node, and newly cloned node. Use this to check if a clone is already made for
        the current node or its random pointer. If not, create a new node and update the map.
        Time: O(N)
        Space: O(N)
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        // 1. one pass to clone the nodes chained by next.
        // 2. second pass to link random pointers
        // OR
        // one pass creation and linking
        Map<Node, Node> oldToClonedNodes = new HashMap<>();
        Node prev = null, curr=head; // previous node in the linear chain
        Node clonedListHead = null;
        while(curr != null){
            // check if curr is already created
            Node clonedNode = getNodeIfExists(oldToClonedNodes, curr);
            if(prev != null){
                prev.next = clonedNode;
            } else {
                clonedListHead = clonedNode;
            }
            // check if random pointer is already existing
            Node randomNode = getNodeIfExists(oldToClonedNodes, curr.random);
            clonedNode.random = randomNode;
            // update prev
            prev = clonedNode; // cloned list
            curr = curr.next; // original list
        }
        return clonedListHead;
    }
}
