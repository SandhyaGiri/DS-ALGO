package javaproblems;

import java.util.*;

// https://leetcode.com/problems/peeking-iterator/
// decorator pattern - implements and also holds an instance of the type.
public class PeekingIterator implements Iterator<Integer> {
    Integer nextInt = null;
    Iterator<Integer> normalIterator;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
        normalIterator = iterator;
	    if(normalIterator.hasNext()){
            nextInt = normalIterator.next();
        }
	}
	
    // Returns the next element in the iteration without advancing the iterator.
    /**
     * Idea: stay ahead of the internal iterator by atleast one stride.
     * @return
     */
	public Integer peek() {
        return nextInt;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer result = nextInt;
        if(normalIterator.hasNext()){
            nextInt = normalIterator.next();
        } else {
            nextInt = null;
        }
        return result;
	}
	
	@Override
	public boolean hasNext() {
	    return nextInt != null;
	}
}