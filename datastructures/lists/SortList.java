package datastructures.lists;

public class SortList {
    class ListNode{
        int val;
        ListNode next;
    }
    // merge two sorted lists, with no additonal memory
    ListNode merge(ListNode list1, ListNode list2){
        ListNode curr1 = list1, curr2 = list2;
        ListNode prev = null;
        ListNode head = null;
        while(curr1 != null && curr2 != null){
            if(curr1.val < curr2.val){
                if(prev != null){
                    prev.next = curr1;
                    prev = curr1;
                }
                if(head == null){
                    head = curr1;
                    prev = curr1;
                }
                curr1 = curr1.next;
            } else {
                if(prev != null){
                    prev.next = curr2;
                    prev = curr2;
                }
                if(head == null){
                    head = curr2;
                    prev = curr2;
                }
                curr2 = curr2.next;
            }
        }
        if(curr1 != null && prev != null){
            prev.next = curr1;
        }
        if(curr2 != null && prev != null){
            prev.next = curr2;
        }
        return head;
    }
    // find middle pointer using slow and fast pointers.
    public ListNode getMiddleNode(ListNode head){
        ListNode slow=head, fast=head;
        while(slow != null && slow.next!= null && fast != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    /**
     * https://leetcode.com/problems/sort-list/
     * 
     * Do a merge sort for lists, as we don't need random access in merge sort.
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head != null && head.next!= null){
            // find middle node (two pointers)
            ListNode mid = getMiddleNode(head);
            System.out.println("Mid: " + mid.val);
            ListNode secondList = mid.next;
            mid.next = null; // needed so that while traversing first half, you don't tresspass into second half
            ListNode list1 = sortList(head);
            ListNode list2 = sortList(secondList);
            return merge(list1, list2);
        }
        return head;
    }

    // https://leetcode.com/problems/insertion-sort-list/
    public ListNode insertionSortList(ListNode head) {
        ListNode start = null, end = null, curr = head, prev = null,temp = null;
        while(curr != null){
            // insert curr node at right pos in start-> ..-> prev node to curr
            if(start == null){
                start = curr;
                end = curr;
            } else {
                temp=start;
                while(temp != curr && temp.val < curr.val){
                    prev = temp;
                    temp = temp.next;
                }
                if(temp == start){
                    // curr becomes the new start
                    end.next = curr.next;
                    curr.next = start;
                    start = curr;
                } else if(prev == end){ // prev could be end too
                    end = end.next;
                }
                else {
                    ListNode nextNode = prev.next;
                    prev.next = curr;
                    end.next = curr.next;
                    curr.next = nextNode;
                }
            }
            curr = end.next;
        }
        return start;
    }
}