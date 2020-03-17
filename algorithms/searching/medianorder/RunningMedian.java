package algorithms.searching.medianorder;

import java.io.*;
import java.util.*;

class IntegerComparator implements Comparator<Integer>{
    public int compare(Integer a, Integer b) {
        return -1*a.compareTo(b);
    }
}
public class RunningMedian {

    private static PriorityQueue<Integer> h1 = new PriorityQueue<Integer>();
    private static PriorityQueue<Integer> h2 = new PriorityQueue<Integer>(10,new IntegerComparator());

    static void removeOldNode(int ele) {
        int comp = Integer.valueOf(h1.size()).compareTo(h2.size());
        double currMedian = 0;
        switch(comp) {
            case 1:
                currMedian = h1.peek();
                if(ele < currMedian) {
                    h1.remove(ele);
                } else {
                    h2.add(h1.poll());
                    h2.remove(ele);
                }
                break;
            case 0:
                currMedian = (h1.peek()+h2.peek())/2.0;
                if(ele < currMedian) {
                    h1.remove(ele);
                } else {
                    h2.remove(ele);
                }
                break;
            case -1:
                currMedian = h2.peek();
                if(ele < currMedian) {
                    h1.add(h2.poll());
                    h1.remove(ele);
                } else {
                    h2.remove(ele);
                }
                break;
        }
    }

    static double getMedian(int ele) {
        if(h1.isEmpty() && h2.isEmpty()) {
            h1.add(ele);
            return ele;
        }
        int comp = Integer.valueOf(h1.size()).compareTo(h2.size());
        double currMedian = 0;
        switch(comp) {
            case 1:
                currMedian = h1.peek();
                if(ele < currMedian) {
                    h2.add(h1.poll());
                    h1.add(ele);
                } else {
                    h2.add(ele);
                }
                currMedian = (h1.peek()+h2.peek())/2.0;
                break;
            case 0:
                currMedian = (h1.peek()+h2.peek())/2.0;
                if(ele < currMedian) {
                    h1.add(ele);
                    currMedian = h1.peek();
                } else {
                    h2.add(ele);
                    currMedian = h2.peek();
                }
                break;
            case -1:
                currMedian = h2.peek();
                if(ele < currMedian) {
                    h1.add(ele);
                } else {
                    h1.add(h2.poll());
                    h2.add(ele);
                }
                currMedian = (h1.peek()+h2.peek())/2.0;
                break;
        }
        return currMedian;
    }
    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        double currMedian = 0;
        int alerts = 0;
        for(int i=0;i<expenditure.length;i++) {
            if(i<d){
                currMedian = getMedian(expenditure[i]);
                System.out.println(currMedian);
                continue;
            }
            if(expenditure[i] >= 2*currMedian) {
                System.out.printf("%.2f %d\n",currMedian,expenditure[i]);
                alerts++;
            }
            removeOldNode(expenditure[i-d]);
            currMedian = getMedian(expenditure[i]);
            System.out.println(currMedian);
        }
        return alerts;
    }
    public static void main(String[] args) throws IOException {
        int n = 9;

        int d = 5;

        int[] expenditure = new int[]{2,3,4,2,3,6,8,4,5};

        int result = activityNotifications(expenditure, d);

        System.out.println(result);
    }
}