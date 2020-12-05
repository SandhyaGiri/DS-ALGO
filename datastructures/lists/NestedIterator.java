package datastructures.lists;

import java.util.*;

interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
    
         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();
    
       // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    List<Integer> numberList;
    int currIndex;
    public NestedIterator(List<NestedInteger> nestedList) {
        numberList = flattenList(nestedList);
        currIndex = 0;
    }
    
    /**
     * Simple recursive method to flatten the list and return it.
     * @param nestedList
     * @return
     */
    List<Integer> flattenList(List<NestedInteger> nestedList){
        List<Integer> result = new java.util.LinkedList<Integer>();
        for(int i=0;i<nestedList.size();i++){
            NestedInteger node = nestedList.get(i);
            if(node.isInteger()){
                result.add(node.getInteger());
            } else {
                result.addAll(flattenList(node.getList()));
            }
        }
        return result;
    }
    @Override
    public Integer next() {
        int value = numberList.get(currIndex);
        currIndex += 1;
        return value;
    }

    @Override
    public boolean hasNext() {
        return currIndex < numberList.size();
    }
}

/**
 * Complicated way of traversing the nested list as the iterator moves using hasNext, next.
 * But because of [] empty lists, hasNext needs to be changed to keep track of whether a next
 * node exists or not.
 */
class NestedListIterator2 {
    public class NestedIterator implements Iterator<Integer> {
    
        Stack<List<NestedInteger>> listStack;
        Stack<Integer> indexStack; // index where we left off
        
        List<NestedInteger> currList;
        NestedInteger curr; int currIndex;
        public NestedIterator(List<NestedInteger> nestedList) {
            currList = nestedList;
            currIndex = 0;
            curr = currList.size() > 0 ? currList.get(currIndex) : null;
            listStack = new Stack<>();
            indexStack = new Stack<Integer>();
        }
        
        int processCurrNode(){
            if(!curr.isInteger()){
                // we step down to a lower level list
                while(curr!= null && !curr.isInteger()){
                    if(curr.getList().size() > 0){
                        indexStack.push(currIndex);
                        listStack.push(currList);
                        currList = curr.getList();
                        currIndex = 0;
                        curr = currIndex < currList.size() ? currList.get(currIndex) : null;   
                    }else{
                        currIndex += 1;
                        curr = currIndex < currList.size() ? currList.get(currIndex) : null;
                    }
                }
            }
            int value = curr.getInteger();
            currIndex += 1;
            curr = currList != null && currIndex < currList.size() ? currList.get(currIndex) : null;   
            return value;
        }
        @Override
        public Integer next() {
            Integer value = null;
            if(curr == null && !listStack.empty()){
                currList = listStack.pop();
                currIndex = indexStack.pop() + 1;
                curr = currIndex < currList.size() ? currList.get(currIndex) : null;
            }
            value = processCurrNode();
            return value;
        }
    
        @Override
        public boolean hasNext() {
            boolean nextExists = curr != null && (curr.isInteger() || curr.getList().size() > 0 || currIndex < currList.size());
            if(!nextExists && !listStack.empty()){
                while(!listStack.empty() && listStack.peek().size()-1 == indexStack.peek()){
                    listStack.pop();
                    indexStack.pop();
                }
                nextExists = !listStack.empty();
            }
            return nextExists;
        }
    }
}