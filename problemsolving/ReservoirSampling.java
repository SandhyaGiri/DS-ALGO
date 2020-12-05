package problemsolving;

import java.util.*;

public class ReservoirSampling {
    
}

// https://leetcode.com/problems/random-pick-index/
class RandomPickIndex {
    Map<Integer, List<Integer>> indicesMap = new HashMap<>();
    public RandomPickIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(indicesMap.get(nums[i]) == null){
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                indicesMap.put(nums[i], indices);
            } else {
                indicesMap.get(nums[i]).add(i);
            }
        }
    }
    
    public int pick(int target) {
        List<Integer> indices = indicesMap.get(target);
        Random rand_gen = new Random();
        int index = rand_gen.nextInt(indices.size());
        return indices.get(index);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// https://leetcode.com/problems/linked-list-random-node/s
class RandomNode {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    List<Integer> nodes;
    public RandomNode(ListNode head) {
        nodes = new LinkedList<>();
        while(head != null){
            nodes.add(head.val);
            head = head.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int n = nodes.size();
        Random rand_generator = new Random();
        if(n > 1){
            int index = rand_generator.nextInt(n);
            return nodes.get(index);   
        }
        return n == 1? nodes.get(0) : 0;
    }
}


// Random Pick with Weight
// https://leetcode.com/problems/random-pick-with-weight/
class RandomPickWeighted {
    
    int[] originalArray;
    int totalWeight;
    /**
     * Idea: sum the weights up to an index. For weights between sum[i-1] and sum[i]
     * return the number as i. Now we just have to pick a random number from 0.. sum[n-1]
     * and find its closest number in sum array that is greater than the random number.
     * The index of this ceiling number (sum[i] > rand => i) is our number.
     */
    TreeMap<Integer, Integer> numbers = new TreeMap<Integer, Integer>();
    public RandomPickWeighted(int[] w) {
        originalArray = w;
        for(int i=0;i<w.length;i++){
            totalWeight += w[i];
            numbers.put(totalWeight, i);
        }
    }

    public int pickIndex() {
        Random randGen = new Random();
        int chosenWeight = randGen.nextInt(totalWeight) + 1;
        int ceilWeight = numbers.ceilingKey(chosenWeight);
        return numbers.get(ceilWeight);
    }
}

class RandomPickWeightedSelfSoln {
    
    class Number{
        int n;
        int weight;
        Number(int x, int w){
            n = x;
            weight = w;
        }
    }
    int[] originalArray;
    List<Number> numbers;
    public RandomPickWeightedSelfSoln(int[] w) {
        originalArray = w;
        populateNumbers();
    }
    void populateNumbers(){
        numbers = new ArrayList<>();
        for(int i=0;i<originalArray.length;i++){
            numbers.add(new Number(i, originalArray[i]));
        }
    }
    public int pickIndex() {
        Random randGen = new Random();
        int index = randGen.nextInt(numbers.size());
        Number chosen = numbers.get(index);
        chosen.weight -= 1;
        if(chosen.weight == 0){
            numbers.remove(index);
        }
            
        if(numbers.size() == 0){
            // repopulate the numbers
            System.out.println("re-populating");
            populateNumbers();
        }
        return chosen.n;
    }
}

// https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/
class SampleRectangleByArea {
    
    Random random;
    TreeMap<Integer,int[]> map;
    int areaSum = 0;
    
    /**
     * Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.

        Note:

        An integer point is a point that has integer coordinates. 
        A point on the perimeter of a rectangle is included in the space covered by the rectangles. 
        ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
        length and width of each rectangle does not exceed 2000.
        1 <= rects.length <= 100
        pick return a point as an array of integer coordinates [p_x, p_y]
        pick is called at most 10000 times.
        Example 1:

        Input: 
        ["Solution","pick","pick","pick"]
        [[[[1,1,5,5]]],[],[],[]]
        Output: 
        [null,[4,1],[4,1],[3,3]]

        Idea: points within rectangle need to be sampled uniformly. but if we choose a rectangle randomly then we might
        not be uniformly sampling wrt to the points, as some rectangles will have more points inside (area).
        1. So we sample one rectangle based on area (weighted sampling)
        2. Sampling along x coords and y coords in this rectangle.
        
     * @param rects
     */
    public SampleRectangleByArea(int[][] rects) {
        this.random = new Random();
        this.map = new TreeMap<>();
        
        for(int i = 0; i < rects.length; i++){
            int [] rectangeCoordinates = rects[i];
            int length = rectangeCoordinates[2] - rectangeCoordinates[0] + 1 ; // +1 as we need to consider edges also.
            int breadth = rectangeCoordinates[3] - rectangeCoordinates[1] + 1 ;
            
            areaSum += length * breadth;
            
            map.put(areaSum,rectangeCoordinates);
            
        }
        
    }
    
    public int[] pick() {
        int key = map.ceilingKey(random.nextInt(areaSum) + 1); //Don't forget to +1 here, because we need [1,area] while nextInt generates [0,area-1]
        
        int [] rectangle = map.get(key);
        
        int length = rectangle[2] - rectangle[0] + 1 ; // +1 as we need to consider edges also.
        int breadth = rectangle[3] - rectangle[1] + 1 ;
        
        int x = rectangle[0] + random.nextInt(length); //return random length from starting position of x
        int y = rectangle[1] + random.nextInt(breadth); // return random breadth from starting position of y
        
        return new int[]{x,y};
        
    }
}
