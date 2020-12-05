package javaproblems.concurrency;

// https://leetcode.com/problems/print-foobar-alternately/

/**
 * Suppose you are given the following code:

    class FooBar {
    public void foo() {
        for (int i = 0; i < n; i++) {
        print("foo");
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
        print("bar");
        }
    }
    }
    The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.

    

    Example 1:

    Input: n = 1
    Output: "foobar"
    Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
    Example 2:

    Input: n = 2
    Output: "foobarfoobar"
    Explanation: "foobar" is being output 2 times.

 */
public class FooBar {
    private int n;
    private int foosPrinted =0;
    private int barsPrinted=0;
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(foosPrinted > barsPrinted){
                Thread.sleep(1);
            }
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            foosPrinted += 1;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(foosPrinted < barsPrinted + 1){
                Thread.sleep(1);
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            barsPrinted += 1;
        }
    }
}
