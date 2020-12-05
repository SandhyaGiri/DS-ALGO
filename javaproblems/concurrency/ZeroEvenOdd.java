package javaproblems.concurrency;

import java.util.function.*;

// https://leetcode.com/problems/print-zero-even-odd/
// https://www.baeldung.com/java-wait-notify - low level synzhornization techniques, high level - Locks
public class ZeroEvenOdd {
    private int n;
    private int currValue;
    
    /**
     * Suppose you are given the following code:

        class ZeroEvenOdd {
        public ZeroEvenOdd(int n) { ... }      // constructor
        public void zero(printNumber) { ... }  // only output 0's
        public void even(printNumber) { ... }  // only output even numbers
        public void odd(printNumber) { ... }   // only output odd numbers
        }
        The same instance of ZeroEvenOdd will be passed to three different threads:

        Thread A will call zero() which should only output 0's.
        Thread B will call even() which should only ouput even numbers.
        Thread C will call odd() which should only output odd numbers.
        Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.

        

        Example 1:

        Input: n = 2
        Output: "0102"
        Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
        Example 2:

        Input: n = 5
        Output: "0102030405"
     * @param num
     */
    public ZeroEvenOdd(int num) {
        this.n = num;
        this.currValue = 0;
    }

    private void moveToNextNumber(int lastPrinted){
        if(this.currValue != 0){ // zero
            this.currValue = 0;
        } else if(lastPrinted %2 == 0){ // one, even when lastPrintedValue was zero, we get index in the loop from 0..n-1, if even we need to print one method next, ow. two method.
            this.currValue = 1;
        } else { // two
            this.currValue = 2;
        }
    }
    
    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=0;i<n;i++){
            while(this.currValue != 0){
                // wait is called on the object, wakes up when some other thread calls notify on the same object.
                // wait works only within a synchronized context - thread has monitor control on the object
                wait(); 
            }
            printNumber.accept(0);
            moveToNextNumber(i);
            notifyAll(); // wake up 1 or 2 
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2;i<=n;i+=2){ // only even values
            while(this.currValue != 2){
                wait(); 
            }
            printNumber.accept(i);
            moveToNextNumber(i);
            notifyAll(); // wake up 0 or 1
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i+=2){ // only odd values
            while(this.currValue != 1){
                wait(); 
            }
            printNumber.accept(i);
            moveToNextNumber(i);
            notifyAll(); // wake up 0 or 2
        }
    }
}
