package datastructures.lists;

import java.util.*;

// https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKSortedLists {
    class ListNodeComparator implements Comparator<ListNode>{
        public int compare(ListNode first, ListNode second){
            return Integer.compare(first.val, second.val);
        }
    }
    /**
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

        Merge all the linked-lists into one sorted linked-list and return it.

        

        Example 1:

        Input: lists = [[1,4,5],[1,3,4],[2,6]]
        Output: [1,1,2,3,4,4,5,6]
        Explanation: The linked-lists are:
        [
        1->4->5,
        1->3->4,
        2->6
        ]
        merging them into one sorted list:
        1->1->2->3->4->4->5->6
        Example 2:

        Input: lists = []
        Output: []
        Example 3:

        Input: lists = [[]]
        Output: []

        Idea: At any point, we need to maintain the smallest value node of all lists, and then move the pointer on this list.
        Min heap is useful here to always maintain the smallest node, once polled, we can add its next pointer to heap and
        also nullify the curr node's next pointer. Similar to merge two sorted lists, where we directly compare list heads to find
        the smallest one (here we need to traverse k lists each time!)  
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode mergedListHead = null, prev =null;
        PriorityQueue<ListNode> nodes = new PriorityQueue<>(new ListNodeComparator());
        for(int i=0;i<lists.length;i++){
            if(lists[i] != null){
                nodes.add(lists[i]);   
            }
        }
        while(!nodes.isEmpty()){
            ListNode minNode = nodes.poll();
            // System.out.println(minNode.val);
            if(prev == null){
                prev = minNode;
                mergedListHead = minNode;
            } else {
                prev.next = minNode;
                prev = minNode;
            }
            if(minNode.next != null){
                nodes.add(minNode.next);
                minNode.next = null;   
            }
        }
        return mergedListHead;
    }
}
