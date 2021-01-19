package datastructures.lists;

import java.util.*;

public class LinkedList {
    ListNode head;

    class ListNode {
        int key;
        ListNode next;
        ListNode(int key) {
            this.key = key;
        }
    }

    /**
     * Delete the nth node from the end of the list, do it in 1 traversal.
     * 
     * @param head pointer
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || (head.next == null && n == 1)) {
            return null;
        }
        ListNode ref1 = head, ref2 = head;
        for(int i=0;i<n;i++) {
            if(ref2 == null) {
                // n > size case
            }
            ref2 = ref2.next;
        }
        while(ref2 != null && ref2.next != null) {
            ref1 = ref1.next;
            ref2 = ref2.next;
        }
        if(ref2 == null) { // n == size case
            head = ref1.next;
            ref1.next = null;
        } else { // n< size case
            ref1.next = ref1.next.next;   
        }
        return head;
    }

    /**
     * Remove all elements from a linked list of integers that have value val.
     * This could be starting element, the entire list could be val, or the list doesn't have val at all etc.
     * 
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = null, curr = head;
        while(curr != null) {
           while(curr!= null && curr.key != val) {
                prev = curr;
                curr = curr.next;
            }
            if(curr != null && curr.key == val) {
                if(prev != null){
                    prev.next = curr.next;
                    curr = prev.next;
                } else {
                    head = curr.next;
                    curr = head;
                }
            } 
        }
        return head;
    }

    /**
     * Given a linked list, rotate the list to the right by k places, where k is non-negative.
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) {
            return head;
        }
        // count nodes
        int count = 1;
        ListNode ref1=head, ref2 = head;
        while(ref2.next != null) {
            ref2 = ref2.next; 
            count++;
        }
        // no change if k == n
        if(k==count) {
            return head;
        }
        // put n-k elements from front behind (k<n), or put n-k%n elements from front behind (k>n)
        int ele = k>count ? count-k%count : count-k;
        while(ele > 1) {
            ref1 = ref1.next;
            ele--;
        }
        ref2.next= head;
        head = ref1.next;
        ref1.next = null;
        return head;
    }

    /**
     * https://leetcode.com/problems/partition-list/
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

        You should preserve the original relative order of the nodes in each of the two partitions.

        Example:

        Input: head = 1->4->3->2->5->2, x = 3
        Output: 1->2->2->4->3->5
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode start = null;
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null) {
            if(curr.key < x && (prev == null || start == prev)){
                start = curr;
                prev = curr;
                curr = curr.next; 
            }
            else if(curr.key < x && prev != null) {
                // delete the curr node from its position and insert it after start - to preserve order
                if(start == null) {
                    prev.next = curr.next;
                    curr.next = head;
                    head = curr;
                    start = head;
                    curr = prev.next;
                } else {
                    prev.next = curr.next;
                    curr.next = start.next;
                    start.next = curr;
                    start = curr;
                    curr = prev.next;
                }
            } else {
                prev = curr;
                curr = curr.next;   
            }
        }
        return head;
    }

    /**
     * Given a linked list, swap every two adjacent nodes and return its head.

        You may not modify the values in the list's nodes, only nodes itself may be changed.

 

        Example:

        Given 1->2->3->4, you should return the list as 2->1->4->3.
        Given [1,2,3,4,5], you should return the list as [2,1,4,3,5]
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode curr = head, prev=null, next =null;
        while(curr != null && curr.next != null) {
            next = curr.next;
            curr.next = next.next;
            next.next = curr;
            if(prev != null) {
                prev.next = next;
            } else {
                head = next;
            }
            prev = curr;
            curr = curr.next;
        }
        return head;
    }

    /**
     * https://leetcode.com/problems/reverse-nodes-in-k-group/
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

        k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

        Example:

        Given this linked list: 1->2->3->4->5

        For k = 2, you should return: 2->1->4->3->5

        For k = 3, you should return: 3->2->1->4->5

        Note:

        Only constant extra memory is allowed.
        You may not alter the values in the list's nodes, only nodes itself may be changed.
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head, prev=null, next=null, prevlocalhead=null, localhead=head;
        int n = 0;
        while(curr != null) {
            n++;
            curr = curr.next;
        }
        int numGroups = n/k;
        curr = head;
        for(int i=0;i<numGroups;i++) {
            for(int j=0;j<k;j++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            ListNode temp = prev;
            if(prevlocalhead != null) {
                prevlocalhead.next = prev;
            }
            prevlocalhead = localhead;
            localhead = curr;
            prev = null;
            if(i==0) {
                head = temp;
            }
        }
        if(prevlocalhead != null) {
            prevlocalhead.next = curr;
        }
        return head;
    }

    /**
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

        You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

        Example 1:

        Input: 1->2->3->4->5->NULL
        Output: 1->3->5->2->4->NULL
        Example 2:

        Input: 2->1->3->5->6->4->7->NULL
        Output: 2->3->6->7->1->5->4->NULL
        Note:

        The relative order inside both the even and odd groups should remain as it was in the input.
        The first node is considered odd, the second node even and so on
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode oddListPointer = head, end = head;
        while(end != null && end.next != null){
            end = end.next;
        }
        // move in pairs
        ListNode evenListPointer = end;
        ListNode evenListHead = null;
        while(oddListPointer != null && oddListPointer != end && oddListPointer != evenListHead){
            ListNode evenNode = oddListPointer.next;
            if(evenNode.next != null){
                oddListPointer.next = evenNode.next;
                System.out.println(evenListPointer.key);
                evenListPointer.next = evenNode;
                if(evenListHead == null){
                    evenListHead = evenNode;
                }
                evenNode.next = null;   
            }
            
            // set for next round
            oddListPointer = oddListPointer.next;
            evenListPointer = evenNode;
        }
        return head;
    }

    // https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(ListNode head) {
        int numNodes = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.next;
            numNodes+=1;
        }
        Stack<Integer> stack = new Stack<>();
        curr = head;
        int i = 1;
        while(i <= Math.floor(numNodes/2)){
            stack.push(curr.key);
            curr = curr.next;
            i+=1;
        }
        // System.out.println(numNodes + "," + stack.size());
        if(numNodes % 2 != 0){
            curr = curr.next; // skip middle node
            i+=1;
        }
        while(i <= numNodes && stack.peek() == curr.key){
            curr = curr.next;
            stack.pop();
            i+= 1;
        }
        return stack.empty();
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null, prev =null, curr1 = l1, curr2 = l2;
        while(curr1 != null && curr2 != null){
            if(curr1.key <= curr2.key){
                if(head == null){
                    head = curr1;
                    prev = curr1;
                } else{
                    prev.next = curr1;
                    prev = curr1;
                }
                curr1 = curr1.next;
            } else{
                if(head == null){
                    head = curr2;
                    prev = curr2;
                } else{
                    prev.next = curr2;
                    prev = curr2;
                }
                curr2 = curr2.next;
            }
        }
        if(curr1 != null){
            if(prev != null){ // not merged yet
                prev.next = curr1;   
            } else{
                head = curr1;
            }
        }
        if(curr2 != null){
            if(prev != null){
                prev.next = curr2;   
            } else{
                head = curr2;
            }
        }
        return head;
    }

    // https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean cycleCheckDone = false;
        while(slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            cycleCheckDone = true; // to make sure the pointers moved atleast once as they are both initialized to head
            if(slow == fast){
                break;
            }
        }
        return cycleCheckDone && slow == fast;
    }

    // https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head){
        ListNode slow = head, fast = head;
        boolean cycleCheckDone = false;
        while(slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            cycleCheckDone = true; // to make sure the pointers moved atleast once as they are both initialized to head
            if(slow == fast){
                break;
            }
        }
        if(!cycleCheckDone || slow != fast){ // no cycle found
            return null; // no start of the loop
        }
        // post process, set slow=head and move both slow, fast one at a time, they meet at start node
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-list
    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.

        Example 1:

        Input: 1->1->2
        Output: 1->2
        Example 2:

        Input: 1->1->2->3->3
        Output: 1->2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode curr=head;
        while(curr != null){
            if(prev != null && curr.key == prev.key){
                curr = curr.next;
                prev.next = null; // will be reattached when a new node is found
            } else if(prev == null){
                prev = curr;
            } else if(curr.key != prev.key){
                prev.next = curr;
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii
    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

        Return the linked list sorted as well.

        Example 1:

        Input: 1->2->3->3->4->4->5
        Output: 1->2->5
        Example 2:

        Input: 1->1->1->2->3
        Output: 2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode prevNew = null, prevOld = null; // newly created and old lists
        ListNode newHead = null, curr=head;
        while(curr != null){
            if((curr.next == null || curr.next.key != curr.key) && (prevOld == null || prevOld.key != curr.key)){ // distinct node
                ListNode newNode = new ListNode(curr.key);
                if(prevNew == null){
                    prevNew = newNode;
                    newHead = newNode;
                } else{
                    prevNew.next = newNode;
                    prevNew = newNode;
                }
            }
            prevOld = curr;
            curr = curr.next;
        }
        return newHead;
    }
}