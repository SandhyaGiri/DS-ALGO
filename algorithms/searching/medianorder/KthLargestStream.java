package algorithms.searching.medianorder;

import java.util.*;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
public class KthLargestStream {

    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargestStream(int k, int[] nums) {
        this.k=k;
        minHeap = new PriorityQueue<>();
        for(int i=0;i<nums.length;i++){
            if(i<k){
                minHeap.add(nums[i]);
            } else if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }
    }
    /**
     * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

    Implement KthLargest class:

    KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
    int add(int val) Returns the element representing the kth largest element in the stream.
    

    Example 1:

    Input
    ["KthLargest", "add", "add", "add", "add", "add"]
    [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
    Output
    [null, 4, 5, 5, 8, 8]

    Explanation
    KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
    kthLargest.add(3);   // return 4
    kthLargest.add(5);   // return 5
    kthLargest.add(10);  // return 5
    kthLargest.add(9);   // return 8
    kthLargest.add(4);   // return 8
    

    Constraints:

    1 <= k <= 104
    0 <= nums.length <= 104
    -104 <= nums[i] <= 104
    -104 <= val <= 104
    At most 104 calls will be made to add.
    It is guaranteed that there will be at least k elements in the array when you search for the kth element.

    Idea: hold the k largest elements in a minHeap, and hence kth largest element is always at the top.
    In constructor, there might not be k elements in heap. so if heap size < k, add curr element. Otherwise
    compare if curr >= heap.peek() if so, we need to replace the top with the new element and heapify again.

    Though in a static array, we can find kth largest without a heap in O(n) using random select, here we have
    a dynamic array. and in future some elements below the heap top can become the kth largest (if we always enter a new
    ele > max in the heap). so we need to maintain rightmost k elements in a minHeap.
     * @param val
     * @return
     */
    public int add(int val) {
        if(minHeap.size() >= k){ //only when we have k elements in the heap, we compare
            int currKthLargest = minHeap.peek();
            if(val > currKthLargest){
                minHeap.poll();
                minHeap.add(val);
            }   
        } else{
            minHeap.add(val);
        }
        return minHeap.peek();
    }
}
