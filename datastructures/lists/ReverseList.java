package datastructures.lists;

public class ReverseList {
    // https://leetcode.com/problems/reverse-linked-list-ii/
    /**
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

        Example 1:


        Input: head = [1,2,3,4,5], left = 2, right = 4
        Output: [1,4,3,2,5]
        Example 2:

        Input: head = [5], left = 1, right = 1
        Output: [5]

        Idea: Use the three pointer solution to reverse a list only starting from left, until then only adjust prev and curr pointers.
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode prev = null, curr= head, next = null;
        int currentPos = 1;
        while(currentPos < left){
            prev = curr;
            curr = curr.next;
            currentPos++;
        }
        boolean isHeadChanged = false;
        ListNode beforeReverseStart = prev;
        ListNode lastNode = curr;
        while(currentPos <= right){
            if(currentPos == 1){
                isHeadChanged = true;
            }
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            currentPos++;
        }
        if(lastNode != null){
            lastNode.next = curr;
        }
        if(beforeReverseStart != null){
            beforeReverseStart.next = prev;
        }
        return isHeadChanged ? prev : head;
    }
}
