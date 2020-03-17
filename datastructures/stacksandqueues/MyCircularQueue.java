package datastructures.stacksandqueues;

/**
 * https://leetcode.com/problems/design-circular-queue/
 * 
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

    One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

    Your implementation should support following operations:

    MyCircularQueue(k): Constructor, set the size of the queue to be k.
    Front: Get the front item from the queue. If the queue is empty, return -1.
    Rear: Get the last item from the queue. If the queue is empty, return -1.
    enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
    deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
    isEmpty(): Checks whether the circular queue is empty or not.
    isFull(): Checks whether the circular queue is full or not.

 */
public class MyCircularQueue {
    int[] elements;
    int head;
    int tail;
    int size;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        elements = new int[k];
        head = -1;
        tail = -1;
        size =0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        boolean success = false;
        if(!isFull()){
            success = true;
            if(head == -1){// empty queue
                head++;
                elements[head] = value;
                tail = head;
                size+=1;
            } else {
                tail = (tail+1)%elements.length;
                elements[tail] = value;
                size+=1;
            }   
        }
        return success;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        boolean success = false;
        if(!isEmpty()){
            head = (head+1) % elements.length;
            size-=1;
            success = true;
            if(size ==0){ // reset
                head = -1;
                tail = -1;
            }
        }
        return success;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : elements[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : elements[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == elements.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */