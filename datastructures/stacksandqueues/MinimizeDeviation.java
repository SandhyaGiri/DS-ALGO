package datastructures.stacksandqueues;

// https://leetcode.com/problems/minimize-deviation-in-array/
public class MinimizeDeviation {
    /**
     * You are given an array nums of n positive integers.

        You can perform two types of operations on any element of the array any number of times:

        If the element is even, divide it by 2.
        For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
        If the element is odd, multiply it by 2.
        For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
        The deviation of the array is the maximum difference between any two elements in the array.

        Return the minimum deviation the array can have after performing some number of operations.

        Example 1:

        Input: nums = [1,2,3,4]
        Output: 1
        Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
        Example 2:

        Input: nums = [4,1,5,20,3]
        Output: 3
        Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
        Example 3:

        Input: nums = [2,10,8]
        Output: 3

        Idea: The problem gives two operations:

        If the element is even, we can divide it by 2.
        If the element is odd, we can multiply it by 2.
        Consequently, we have two insights:

        If the element is even, we can not increase it.
        If the element is odd, we can not decrease it.
        We can try to increase all numbers to their maximum and reduce them step by step.

     * @param nums
     * @return
     */
    public int minimumDeviation(int[] nums) {
        // max heap
        PriorityQueue<Integer> evenNumbers = new PriorityQueue<>(Comparator.reverseOrder());
        int min = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i] % 2 ==0){
                evenNumbers.add(nums[i]);
                min = Math.min(min, nums[i]);
            } else{// increase odd numbers
                evenNumbers.add(nums[i] *2);
                min = Math.min(min, nums[i]*2);
            }
        }
        int minDeviation = Integer.MAX_VALUE;
        while(!evenNumbers.isEmpty()){
            int num = evenNumbers.poll();
            int currDeviation = num - min;
            minDeviation = Math.min(minDeviation, currDeviation);
            if(num % 2 == 0){
                evenNumbers.add(num/2);
                min = Math.min(min, num/2);
            } else {// largest is odd, we can't reduce it
                break;
            }
        }
        return minDeviation;
    }

    // OWN SOLUTION
    /**
     * Maintain two heaps, min and max. At any time the deviation is given by the max-min values.
     * If the max value is even we can decrease it to reduce deviation, or if the min value is odd
     * then we can *2 to increase its value and thus decreasing the deviation. If two operations are not
     * possible we can stop. 
     */
    class NumPair implements Comparable<NumPair>{
        int num;
        int index;
        NumPair(int num, int index){
            this.num = num;
            this.index = index;
        }
        public boolean equals(Object other){
            if(other instanceof NumPair){
                return this.num == ((NumPair)other).num && this.index == ((NumPair)other).index;
            }
            return false;
        }
        public int compareTo(NumPair other){
            return Integer.compare(this.num, other.num);
        }
    }
    // somehow fails for [399,908,648,357,693,502,331,649,596,698] giving suboptimal value
    public int minimumDeviationOwn(int[] nums) {
        PriorityQueue<NumPair> minHeap = new PriorityQueue<>();
        PriorityQueue<NumPair> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0;i<nums.length;i++){
            minHeap.add(new NumPair(nums[i], i));
            maxHeap.add(new NumPair(nums[i], i));
        }
        Set<NumPair> seenIntegers = new HashSet<>();
        int minDeviation = Integer.MAX_VALUE;
        while(true){
            NumPair y = maxHeap.peek();
            NumPair x = minHeap.peek();
            System.out.println(seenIntegers.contains(x));
            System.out.println(seenIntegers.contains(y));
            if(seenIntegers.contains(x) && seenIntegers.contains(y)){
                System.out.println(x.num + "-" + x.index + "," + y.num + "-" + y.index);
                break;
            }
            int currDeviation = y.num - x.num;
            System.out.println(currDeviation + "->" + x.num + "," + y.num);
            if(currDeviation < minDeviation){
                minDeviation = currDeviation;
            }
            seenIntegers.add(x);
            seenIntegers.add(y);
            if(y.num % 2 == 0){ // larger one is even
                NumPair newY = new NumPair(y.num /2, y.index);
                minHeap.remove(y);
                maxHeap.poll();
                minHeap.add(newY);
                maxHeap.add(newY);
            } else if (x.num %2 != 0){ // smaller one is odd
                NumPair newX = new NumPair(x.num * 2, x.index);
                minHeap.poll();
                maxHeap.remove(x);
                minHeap.add(newX);
                maxHeap.add(newX);
            } else {
                break;
            }
        }
        return minDeviation;
    }
}
