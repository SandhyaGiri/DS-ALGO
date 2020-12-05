package datastructures.lists;

// https://leetcode.com/problems/merge-in-between-linked-lists/
public class MergeBetweenLists {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * You are given two linked lists: list1 and list2 of sizes n and m respectively.

    Remove list1's nodes from the ath node to the bth node, and put list2 in their place.

    The blue edges and nodes in the following figure incidate the result:
    input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
    Output: [0,1,2,1000000,1000001,1000002,5]
    Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
     
    time: O(N + M)
    * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // linear scan and maintain prev node for a and curr node for b
        ListNode curr = list1, prev = null;
        ListNode breakPoint1 = null, breakPoint2 = null;
        int count = 0;
        while(curr != null){
            if(count == a){ // a is always found first (a <= b)
                breakPoint1 = prev;
            }
            if(count == b){
                breakPoint2 = curr.next;
                break; // stop searching on seeing b
            }
            prev = curr;
            curr = curr.next;
            count += 1;
        }
        if(breakPoint1 != null){
            breakPoint1.next = list2;
        }
        // go to end of list2
        ListNode temp = list2;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = breakPoint2;
        return list1;
    }
}
