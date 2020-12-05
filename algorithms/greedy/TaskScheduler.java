package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/task-scheduler/
public class TaskScheduler {
    /**
     * You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task. Tasks could be done without the original order of the array. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

You need to return the least number of units of times that the CPU will take to finish all the given tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.
Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 

Constraints:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
     */
    class Pair implements Comparable<Pair>{
        char task;
        int count;
        int validTime;
        Pair(char x, int y){
            this.task = x;
            this.count = y;
        }
        Pair(char x, int y, int time){
            this.task = x;
            this.count = y;
            this.validTime = time;
        }
        public int compareTo(Pair other){
            return other.count - this.count;
        }
    }
    class PairTimeComparator implements Comparator<Pair>{
        public int compare(Pair a, Pair b){
            return a.validTime - b.validTime;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskCounts = new HashMap<>();
        for(char c: tasks){
            taskCounts.put(c, taskCounts.getOrDefault(c, 0)+1);
        }
        LinkedList<Pair> queue = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry: taskCounts.entrySet()){
            queue.add(new Pair(entry.getKey(), entry.getValue()));
        }
        // sort the list based on decreasing task counts, so we always pick the task that
        // has to be performed more frequently first
        Collections.sort(queue);

        // P.Queue with time based sorting
        PriorityQueue<Pair> heap = new PriorityQueue<>(new PairTimeComparator());
        // add pairs to the heap, in increasing time steps
        int i=1;
        for(Pair p: queue){
            heap.add(new Pair(p.task, p.count, i++)); // assign increasing time units, as cpu can do only one task at a time.
        }
        int timeUnits = 0;
        while(!heap.isEmpty()){
            Pair ele = heap.poll();
            System.out.println(ele.task + ","+ ele.count + "," + ele.validTime);
            // if a task is scheduled for future time, reset curr time to future (in betweeen we were idle)
            // otherwise this task takes 1 unit of time, increment the curr time
            timeUnits = ele.validTime > timeUnits ? ele.validTime : timeUnits+1;
            if(ele.count == 1){
                continue; // need not insert it back to the heap
            } else {
                ele.count-=1;
                ele.validTime += n+1;
                heap.add(ele);
            }
        }
        return timeUnits;
    }
}