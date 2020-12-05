package javaproblems.concurrency;

// https://leetcode.com/problems/print-in-order/
public class PrintOrder {
    /**
     * Suppose we have a class:

    public class Foo {
    public void first() { print("first"); }
    public void second() { print("second"); }
    public void third() { print("third"); }
    }
    The same instance of Foo will be passed to three different threads.
    Thread A will call first(), thread B will call second(), and thread C will call third().
    Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().

    Example 1:

    Input: [1,2,3]
    Output: "firstsecondthird"
    Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(),
    thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
    Example 2:

    Input: [1,3,2]
    Output: "firstsecondthird"
    Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second().
    "firstsecondthird" is the correct output.
     */
}
class Foo {
    boolean firstCompleted, secondCompleted;
    public Foo() {
        this.firstCompleted=false;
        this.secondCompleted=false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        this.firstCompleted =true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(!firstCompleted){
            Thread.sleep(1);// sleeps for 1 ms
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        this.secondCompleted = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(!firstCompleted || !secondCompleted){
            Thread.sleep(1);
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}