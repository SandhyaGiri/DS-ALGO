package datastructures.stacksandqueues;

import java.util.*;
/**
To find max number of unique elements in all contiguous subarrays
    */
public class DequeMagic {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        long unique = 0;
        long max_unique = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if(deque.isEmpty()) {
                deque.add(num);
                unique += 1;
            } else if(deque.size() < m) {
                if(!deque.contains(num)) {
                    unique += 1;
                }
                deque.add(num);
                if(deque.size() == m) {
                    if(unique > max_unique) {
                        max_unique = unique;
                    }
                }
            } else {
                if(unique > max_unique) {
                    max_unique = unique;
                }
                if(!deque.contains(num)) {
                    unique +=1;
                }
                int eleRemoved = (int)deque.peekFirst();
                deque.addLast(num);
                deque.removeFirst();
                if(num != eleRemoved && !deque.contains(eleRemoved)) {
                    unique -=1;
                }
            }
        }
        System.out.println(max_unique == Long.MIN_VALUE ? 0 : max_unique);
        in.close();
    }
}