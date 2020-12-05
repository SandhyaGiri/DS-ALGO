package datastructures.lists;

// https://leetcode.com/problems/add-two-numbers/
public class AddTwoLists {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     * 
     * you are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

        You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * 
     * Idea: Simple, traverse from left, add two values if they exist along with carry and create a new node with % 10 and accumulate
     * carry as / 10. At the end if carry is still there, append to end of new list.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // add two numbers from left
        ListNode head1 = l1, head2 = l2;
        ListNode head = null, prev = null;
        int carry=0;
        while(head1 != null || head2 != null){
            int val1 = head1 != null ? head1.val : 0;
            int val2 = head2 != null ? head2.val : 0;
            int val = val1 + val2 + carry;
            if(val >= 10){
                carry = val / 10;
                val = val%10;   
            } else {
                carry = 0;
            }
            ListNode node = new ListNode(val);
            if(head == null){
                head = node;
                prev = node;
            }else{
                prev.next = node;
                prev = node;
            }
            // move pointers
            if(head1 != null){
                head1 = head1.next;   
            }
            if(head2 != null){
                head2 = head2.next;   
            }
        }
        if(carry > 0){
            ListNode node = new ListNode(carry);
            prev.next = node;
        }
        return head;
    }
}

// https://leetcode.com/problems/add-two-numbers-ii/
class AddTwoListsRecursive {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    int countNodes(ListNode head){
        ListNode curr=head;
        int count = 0;
        while(curr != null){
            count += 1;
            curr = curr.next;
        }
        return count;
    }
    ListNode addTwoEqualLists(ListNode l1, ListNode l2){ // from right
        if(l1 == null && l2 == null){
            return null;
        }
        if(l1.next == null && l2.next == null){
            return new ListNode(l1.val + l2.val);
        }
        ListNode next = addTwoEqualLists(l1.next, l2.next); // recur first
        ListNode curr = new ListNode();
        if(next.val >= 10){
            curr.val = next.val / 10;
            next.val = next.val % 10;
        }
        curr.val += l1.val + l2.val;
        curr.next = next;
        return curr;
    }
    ListNode addCarryOver(ListNode head, int carry){ // adds a number to end of the list
        if(head == null){
            return null;
        }
        if(head.next == null){
            return new ListNode(head.val + carry);
        }
        ListNode next = addCarryOver(head.next, carry);
        ListNode curr = new ListNode();
        if(next.val >= 10){
            curr.val = next.val / 10;
            next.val = next.val % 10;
        }
        curr.val += head.val;
        curr.next = next;
        return curr;
    }
    /**
     * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
     * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

        You may assume the two numbers do not contain any leading zero, except the number 0 itself.

        Follow up:
        What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

        Example:

        Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 8 -> 0 -> 7

        Idea: Here we need to traverse the list from last to first. So we use recursion.
        First we traverse the larger of the two lists such that both lists are equal length after that.
        Now we call a fn to sum these two equal lists and return a new list, whose head still can have value >= 10.
        Then we find the carry from this, and add it first half of larger list which we skipped earlier (if one exists).
        Also check finalHead for carry and create a new node and append to front of the list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int numNodes1 = countNodes(l1);
        int numNodes2 = countNodes(l2);
        System.out.println(numNodes1 +","+ numNodes2);
        // traverse larger list for n-min(n1, n2)
        int commonNodes = Math.min(numNodes1, numNodes2);
        int n = Math.max(numNodes1, numNodes2);
        ListNode head1 = numNodes1 > numNodes2 ? l1 : l2;
        ListNode head2 = numNodes1 > numNodes2 ? l2 : l1;
        ListNode curr1 = head1, prev1=null;
        ListNode curr2 = head2;
        while(curr1 != null && n > commonNodes){
            prev1 = curr1;
            curr1 = curr1.next;
            n-=1;
        }
        System.out.println(curr1.val);
        // calculate sum of two equal sized lists curr1, curr2
        ListNode addedListHead = addTwoEqualLists(curr1, curr2);
        System.out.println(addedListHead.val);
        ListNode finalHead = null;
        if(addedListHead.val >= 10){
            if(numNodes1 != numNodes2){ // one list larger than other
                if(prev1 != null){
                    prev1.next = null;   
                }
                finalHead = addCarryOver(head1, addedListHead.val / 10); 
                addedListHead.val %= 10;
            } else {
                finalHead = addedListHead;
            }
            // traverse till end and add old list
            curr1 = finalHead;
            while(curr1 != addedListHead && curr1.next != null){
                curr1 = curr1.next;
            }
            if(curr1 != addedListHead){
                curr1.next = addedListHead;   
            }
            // final carry in head
            if(finalHead.val >= 10){
                ListNode temp = finalHead;
                finalHead = new ListNode(temp.val / 10);
                temp.val %= 10;
                finalHead.next = temp;
            }
        } else if(prev1 != null){ // attach to larger list
            prev1.next = addedListHead;
            finalHead = head1;
        } else { // addedList is the new list
            finalHead = addedListHead;
        }
        return finalHead;
    }
}
