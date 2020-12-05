package datastructures.graphs;

import java.util.*;

//https://leetcode.com/problems/possible-bipartition/
/**
 * Idea: same as BiPartite check, done using DFS with alternating group numbers.
 */
public class DislikeBipartition {
    public boolean isBiPartitionable(int v, Map<Integer, List<Integer>> adjMap, int[] groupNum, int currGroup){
        groupNum[v-1] = currGroup;
        System.out.println("Assigned "+ v + " to group "+ currGroup);
        boolean canPart = true;
        int nextExpectedGroup = currGroup == 1 ? 2 : 1;
        for(int neighbor: adjMap.get(v)){
            System.out.println("Neighbor: "+ neighbor + ", for v: "+ v);
            if(groupNum[neighbor-1] == 0 && !isBiPartitionable(neighbor, adjMap, groupNum, nextExpectedGroup)){
                canPart = false;
                break;
            } else if(groupNum[neighbor-1] != nextExpectedGroup){
                canPart = false;
                break;
            }
        }
        return canPart;
    }
    /**
     * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

        Each person may dislike some other people, and they should not go into the same group. 

        Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

        Return true if and only if it is possible to split everyone into two groups in this way.

        

        Example 1:

        Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
        Output: true
        Explanation: group1 [1,4], group2 [2,3]
        Example 2:

        Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
        Output: false
        Example 3:

        Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
        Output: false
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int i=1;i<=N;i++){
            adjMap.put(i, new ArrayList<>());
        }
        for(int i=0;i<dislikes.length;i++){
            int src = dislikes[i][0];
            int dst = dislikes[i][1];
            adjMap.get(src).add(dst);
            adjMap.get(dst).add(src);
        }
        int[] groupNum = new int[N]; // 1,2 - assigned, 0- not assigned yet
        for(int i=0;i<N;i++){
            if(groupNum[i] == 0 && !isBiPartitionable(i+1, adjMap, groupNum, 1)){
                return false;
            }
        }
        return true;
    }
}