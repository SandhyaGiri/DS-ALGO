package datastructures.graphs;

import java.util.*;

// https://leetcode.com/problems/all-paths-from-source-to-target/
public class AllPaths {
    /**
     * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

        The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

        Example:
        Input: [[1,2], [3], [3], []] 
        Output: [[0,1,3],[0,2,3]] 
        Explanation: The graph looks like this:
        0--->1
        |    |
        v    v
        2--->3
        There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
        Note:

        The number of nodes in the graph will be in the range [2, 15].
        You can print different paths in any order, but you should keep the order of nodes inside one path.

     * @param graph
     * @param node
     * @param path
     * @param paths
     * @param n
     */
    void dfsUtil(int[][] graph, int node, List<Integer> path, List<List<Integer>> paths, int n){
        if(node == n){
            path = new ArrayList<>(path); // clone current path
            path.add(node);
            paths.add(path);
            //for(int x: path){
            //    System.out.print(x + "=>");
            //}
            return;
        }
        path.add(node);
        for(int i=0;i<graph[node].length;i++){
            dfsUtil(graph, graph[node][i], path, paths, n);
        }
        // after exploring remove the node (backtracking)
        path.remove(path.size()-1);
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        dfsUtil(graph, 0, new ArrayList<Integer>(), paths, graph.length-1);
        return paths;
    }
}