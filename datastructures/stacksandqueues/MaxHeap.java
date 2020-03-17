package datastructures.stacksandqueues;

import java.util.*;

class IntegerComparator implements Comparator<Integer> {
    public int compare(Integer i1, Integer i2) {
        return -1 * i1.compareTo(i2);
    }
}
public class MaxHeap {
    public static void main(String args[]) {
        // By default PriorityQueue maintains elements in ascending order (min Heap), we need to reverse comparison logic to get MaxHeap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new IntegerComparator());
        maxHeap.add(20);
        maxHeap.add(2);
        maxHeap.add(10);
        maxHeap.add(12);
        maxHeap.add(25);
        System.out.println("Top element: " + maxHeap.peek());
        while(!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}