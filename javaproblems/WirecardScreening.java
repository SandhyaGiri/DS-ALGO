package javaproblems;

import java.util.stream.*;
import java.util.function.*;
import java.util.*;
/*
Given a collection of numbers write a Java program to calculate the sum and product of all even numbers.
How can the program be changed to support a general aggregation of the numbers (like the sum) selected by a general criterion (like the even numbers).
*/
public class WirecardScreening {
    public static Optional<Integer> aggregateStream(Stream<Integer> numbers, Predicate<Integer> filterCriteria, BinaryOperator<Integer> aggregator) {
        return numbers.filter(filterCriteria).reduce(aggregator);
    }
    public static void main(String args[]) {
        Stream<Integer> numbers = Stream.of(new Integer[]{1,3,5,7,9});
        
        Optional<Integer> sum = aggregateStream(numbers, num -> num%2 != 0, (x,y) -> x+y);
        
        numbers = Stream.of(new Integer[]{1,3,5,7,9});
        Optional<Integer> prod = aggregateStream(numbers, num -> num%2 != 0, (x,y) -> x*y);
        
        System.out.println("Sum of odd numbers: " + (sum.isPresent() ? sum.get() : null));
        System.out.println("Product of odd numbers: " + (prod.isPresent() ? prod.get() : null));
    }
}
