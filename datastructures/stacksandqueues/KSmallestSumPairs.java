package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
public class KSmallestSumPairs {
    class Pair implements Comparable<Pair>{
        int a;
        int b;
        Pair(int x, int y){
            a = x;
            b = y;
        }
        public int getSum(){
            return this.a+this.b;
        }
        public int compareTo(Pair other){
            // sorted in descending order of sums
            return (other.getSum()) -(this.getSum());
        }
    }
    /**
     * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

        Define a pair (u,v) which consists of one element from the first array and one element from the second array.

        Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

        Example 1:

        Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
        Output: [[1,2],[1,4],[1,6]] 
        Explanation: The first 3 pairs are returned from the sequence: 
                    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
        Example 2:

        Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
        Output: [1,1],[1,1]
        Explanation: The first 2 pairs are returned from the sequence: 
                    [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
        Example 3:

        Input: nums1 = [1,2], nums2 = [3], k = 3
        Output: [1,3],[2,3]
        Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]

        Idea: Use a max heap to store first k pairs - ordered by their sum, for each other pair check if its sum is smaller than
        the top(), then replace it with this new pair and heapify.
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>();
        // put first k pairs in maxHeap
        int pairsToInsert = k;
        int m = nums1.length;
        int n = nums2.length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
               if(pairsToInsert > 0){
                    maxHeap.add(new Pair(nums1[i], nums2[j]));
                    pairsToInsert-=1;
               }else{
                    Pair maxSumPair = maxHeap.peek();
                   int currSum = nums1[i] + nums2[j];
                    if(currSum < maxSumPair.getSum()){
                        maxHeap.poll();
                        maxHeap.add(new Pair(nums1[i], nums2[j]));
                    }
               }
            }
        }
        // finally maxHeap has k smallest sum pairs
        LinkedList<List<Integer>> result = new LinkedList<>();
        while(!maxHeap.isEmpty()){
            Pair pair = maxHeap.poll();
            result.addFirst(List.of(pair.a, pair.b)); // as heap gives us the largest sum pair first
        }
        return result;
    }
}
