package datastructures.graphs;

import java.util.*;

public class BiPartiteCheck {
    // for each connected component, so that all edges are traversed
    public boolean isBipartititeCC(int v, int[][] graph, int[] vertexcolor) {
        Queue<Integer> nodelist = new LinkedList<Integer>();
        nodelist.add(v);
        boolean isRedLevel = true;
        boolean isBipartite = true;
        while(!nodelist.isEmpty()) {
            int level_size = nodelist.size();
            for(int i=0;i<level_size;i++) {
                int node = nodelist.poll();
                vertexcolor[node] = isRedLevel ? 1 : 2;
                int[] neighbors = graph[node];
                for(int n: neighbors) {
                    if(vertexcolor[n] == 0) {
                        nodelist.add(n);
                    }
                    else if(vertexcolor[node] == vertexcolor[n]) {
                        // neighbor is already colored with color same as current
                        isBipartite = false;
                        break;
                    }
                }
            }
            isRedLevel = !isRedLevel;
        }
        return isBipartite;
    }
    /**
     * Given an undirected graph, return true if and only if it is bipartite.

        Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

        The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

        Using BFS for m=2 coloring problem.
        Time: O(V+E) just like BFS because of adj list representation
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int[] vertexcolor = new int[graph.length];
        boolean isBipartite = true;
        for(int i=0;i<graph.length;i++) {
            if(vertexcolor[i] == 0 && !isBipartititeCC(i, graph, vertexcolor)) {
                isBipartite = false;
                break;
            }
        }
        return isBipartite;
    }
}