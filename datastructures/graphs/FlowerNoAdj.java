package datastructures.graphs;

import java.util.*;
import java.util.stream.*;

/**
 * You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

Example 1:

Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Example 2:

Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]

Idea: For each node only look at most 3 neighbors to assign a flower type to itself. Do this in BFS (as all children need to be checked in each iteration)
Greedy + BFS

 */
class FlowerNoAdj {
    
    // this was costly, because of traversing the Edgelist for each node O(V*E)
    public void doBFS(int v, int[][] paths, int[] planted) {
        Queue<Integer> nodes = new LinkedList<Integer>();
        nodes.add(v);
        while(!nodes.isEmpty()) {
            int currNode = nodes.poll();
            List<Integer> allowedFlowers = Stream.of(new Integer[]{4,3,2,1}).collect(Collectors.toList());
            // find neighbors and reduce color choices, add to queue
            for(int i=0;i<paths.length;i++) {
                if(paths[i][0]== currNode) {
                    if(planted[paths[i][1]-1] != 0)
                        allowedFlowers.remove(Integer.valueOf(planted[paths[i][1]-1]));
                    else
                        nodes.add(paths[i][1]);
                } else if(paths[i][1]== currNode) {
                    if(planted[paths[i][0]-1] != 0)
                        allowedFlowers.remove(Integer.valueOf(planted[paths[i][0]-1]));
                    else
                        nodes.add(paths[i][0]);
                }
            }
            planted[currNode-1] = allowedFlowers.remove(allowedFlowers.size()-1);
        }
    }
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] planted = new int[N];
        for(int i=1;i<=N;i++) {
            if(planted[i-1] == 0) {
                doBFS(i, paths, planted);   
            }
        }
        return planted;
    }

    // with adj lists time complexity of BFS is O(V+ 2E) (undirected)
    public void doBFS(int v, List<List<Integer>> adjList, int[] planted) {
        Queue<Integer> nodes = new LinkedList<Integer>();
        nodes.add(v);
        while(!nodes.isEmpty()) {
            int currNode = nodes.poll();
            List<Integer> allowedFlowers = new ArrayList<>(Arrays.asList(1,2,3,4));
            // find neighbors and reduce color choices, add to queue
            List<Integer> neighbors = adjList.get(currNode-1);
            for(Integer n: neighbors) {
                if(planted[n-1] == 0) {
                    nodes.add(n);
                } else {
                    allowedFlowers.remove(Integer.valueOf(planted[n-1]));
                }
            }
            planted[currNode-1] = allowedFlowers.remove(allowedFlowers.size()-1);
        }
    }
    public int[] gardenNoAdj2(int N, int[][] paths) {
        int[] planted = new int[N];
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for(int i=1;i<=N;i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for(int i=0;i<paths.length;i++) {
            adjList.get(paths[i][0]-1).add(paths[i][1]);
            adjList.get(paths[i][1]-1).add(paths[i][0]);
        }
        for(int i=1;i<=N;i++) {
            if(planted[i-1] == 0) {
                doBFS(i, adjList, planted);   
            }
        }
        return planted;
    }
}