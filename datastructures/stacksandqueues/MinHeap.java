package datastructures.stacksandqueues;

import java.util.*;

public class MinHeap {
    public static void main(String args[]) {
        // By default PriorityQueue maintains elements in ascending order (min Heap)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        minHeap.add(20);
        minHeap.add(2);
        minHeap.add(10);
        minHeap.add(12);
        minHeap.add(25);
        System.out.println("Top element: " + minHeap.peek());
        while(!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }
}