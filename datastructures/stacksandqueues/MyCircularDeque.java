package datastructures.stacksandqueues;

/**
 * https://leetcode.com/problems/design-circular-deque/
 * 
 * Design your implementation of the circular double-ended queue (deque).

Your implementation should support following operations:

MyCircularDeque(k): Constructor, set the size of the deque to be k.
insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
getRear(): Gets the last item from Deque. If the deque is empty, return -1.
isEmpty(): Checks whether Deque is empty or not. 
isFull(): Checks whether Deque is full or not.
 

Example:

MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);			// return true
circularDeque.insertLast(2);			// return true
circularDeque.insertFront(3);			// return true
circularDeque.insertFront(4);			// return false, the queue is full
circularDeque.getRear();  			// return 2
circularDeque.isFull();				// return true
circularDeque.deleteLast();			// return true
circularDeque.insertFront(4);			// return true
circularDeque.getFront();			// return 4
 
 */
class MyCircularDeque {

    class Node{
        int val;
        Node next;
        Node prev;
        Node(int v){
            this.val = v;
            this.next = this.prev = null;
        }
    }
    Node head,tail;
    int size;
    int maxSize;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        head = null;
        tail = null;
        size =0;
        maxSize=k;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(!isFull()){
            size+=1;
            if(head == null){
                head = new Node(value);
                tail=head;
            } else {
                Node node = new Node(value);
                node.next = head;
                node.next.prev = node;
                head = node;
                
            }
            return true;
        }
        return false;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(!isFull()){
            size+=1;
            if(tail == null){
                tail = new Node(value);
                head = tail;
            } else {
                Node node = new Node(value);
                tail.next = node;
                tail.next.prev = tail;
                tail = node;
            }
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(!isEmpty()){
            size-=1;
            if(head.next != null){
                head.next.prev = null;
            }
            Node temp = head.next;
            head.next=null;
            head = temp;
            if(size == 0){
                tail = null;
            }
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(!isEmpty()){
            size-=1;
            Node temp = tail.prev;
            if(tail.prev != null){
                tail.prev.next = null;   
            }
            tail.prev = null;
            tail = temp;
            if(size == 0){
                head = null;
            }
            return true;
        }
        return false;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1: head.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : tail.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == maxSize;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */