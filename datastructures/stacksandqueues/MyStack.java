package datastructures.stacksandqueues;

import java.util.*;

/**
 * Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Example:

MyStack stack = new MyStack();

stack.push(1);
stack.push(2);  
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false
Notes:

You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively.
 */
public class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;
    Queue<Integer> activeQueue;
    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
        activeQueue = q1;
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        activeQueue.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    // this is a read-heavy implementation
    public int pop() {
        if(activeQueue == q1) {
            // empty q1 and transfer elements to q2, return last ele
            while(q1.size() > 1) {
                q2.add(q1.poll());
            }
            activeQueue = q2;
            return q1.poll();
        } else {
            // empty q2 and transfer elements to q1, return last ele
            while(q2.size() > 1) {
                q1.add(q2.poll());
            }
            activeQueue = q1;
            return q2.poll();
        }
    }
    
    /** Get the top element. */
    public int top() {
        if(activeQueue == q1) {
            // empty q1 and transfer elements to q2, return last ele
            while(q1.size() > 1) {
                q2.add(q1.poll());
            }
            activeQueue = q2;
            int ele = q1.peek();
            q2.add(q1.poll());
            return ele;
        } else {
            // empty q2 and transfer elements to q1, return last ele
            while(q2.size() > 1) {
                q1.add(q2.poll());
            }
            activeQueue = q1;
            int ele = q2.peek();
            q1.add(q2.poll());
            return ele;
        }
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return activeQueue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

 /** Write-heavy implementation 
  * 
  
This method makes sure that newly entered element is always at the front of ‘q1’, so that pop operation just dequeues from ‘q1’. ‘q2’ is used to put every new element at front of ‘q1’.

push(s, x) operation’s step are described below:
1. Enqueue x to q2
2. One by one dequeue everything from q1 and enqueue to q2.
3. Swap the names of q1 and q2 // make q2 as activeQueue
pop(s) operation’s function are described below:
Dequeue an item from q1 and return it.
 */
 