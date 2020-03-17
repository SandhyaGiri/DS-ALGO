package datastructures.graphs;

import java.util.*;

public class MinHeightTrees {

    /**
     * https://leetcode.com/problems/minimum-height-trees/
     * 
     * For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

        Format
        The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

        You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

        Idea: given a graph traverse from both ends of the graph (from leaves which have degree of 1 - connected to one other node)
        When the graph is split in the middle, we get min height for the resulting tree. If there are odd levels in graph, then middle level nodes are possible roots, which result in balanced trees.
        When there are even levels in the graph, m+1, m level nodes are possible roots as they both result in same height (unbalanced).

        Do a form of toposort (degree = 1 indicates leaf node which can explored next). this is same as traversing the graph from both ends.
        last level nodes traversed in this fashion are the roots. keep clearing the level nodes as long as there are some nodes to be explored.
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int degree[] = new int[n];
        List<List<Integer>> adjList = new LinkedList<>();
        for(int i=0;i<n;i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for(int i=0;i<edges.length;i++) { // undirected graph
            int src = edges[i][0];
            int dst = edges[i][1];
            adjList.get(src).add(dst);
            adjList.get(dst).add(src);
            degree[dst]++;
            degree[src]++;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<n;i++) {
            if(degree[i] == 1) { // indicates leaf node.
                q.add(i);
            }
        }
        List<Integer> roots = new ArrayList<>();
        while(!q.isEmpty()) {
            int levelSize = q.size();
            for(int i=0;i<levelSize;i++) {
                int node = q.poll();
                roots.add(node);
                for(Integer neigh:adjList.get(node)){
                    degree[neigh]--;
                    if(degree[neigh] == 1){
                        q.add(neigh);
                    }
                }   
            }
            // clear the roots as next level nodes are possible roots
            if(!q.isEmpty()){
                roots.clear();
            }
        }
        return roots.isEmpty() ? List.of(0) : roots;
    }
}