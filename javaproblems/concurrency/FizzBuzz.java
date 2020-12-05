package javaproblems.concurrency;

import java.util.function.*;

// https://leetcode.com/problems/fizz-buzz-multithreaded/
public class FizzBuzz {
    private int n;
    private volatile int currNumber;
    public FizzBuzz(int num) {
        n = num;
        currNumber = 1;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while(currNumber <= n){
            while(currNumber % 3 != 0 || currNumber % 5 == 0){
                // System.out.println("fizz waiting");
                wait();
                // after waking up check if limit exceeded and stop
                // otherwise this while will continue waiting forever
                if(currNumber > n){
                    return;
                }
            }
            printFizz.run();
            currNumber +=1;
            notifyAll();   
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while(currNumber <= n){
            while(currNumber % 3 == 0 || currNumber % 5 != 0){
                // System.out.println("buzz waiting");
                wait();
                if(currNumber > n){
                    return;
                }
            }
            printBuzz.run();
            currNumber +=1;
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(currNumber <= n){
            while((currNumber % 3 != 0 || currNumber % 5 != 0)){
                // System.out.println("fizzbuzz waiting");
                wait();
                if(currNumber > n){
                    return;
                }
            }
            printFizzBuzz.run();
            currNumber +=1;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while(currNumber <= n){
            while((currNumber % 3 == 0 || currNumber % 5 == 0)){
                // System.out.println("num waiting");
                wait();
                if(currNumber > n){
                    return;
                }
            }
            printNumber.accept(currNumber);
            currNumber +=1;
            System.out.println("curr num:" + currNumber);
            notifyAll();
        }
    }
}