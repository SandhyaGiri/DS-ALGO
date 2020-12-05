package datastructures.graphs;

import java.util.*;

// https://leetcode.com/problems/critical-connections-in-a-network/
// Tarjan's algorithm: https://www.geeksforgeeks.org/bridge-in-a-graph/
public class AllBridges {
    
    class Graph{
        int numNodes;
        // hashmap fairs well when compared to List<List<Integer>>
        Map<Integer, List<Integer>> adjList;
        List<List<Integer>> bridges;
        Graph(int n, List<List<Integer>> edges){
            adjList = new HashMap<>();
            for(int i=0;i<edges.size();i++){
                int src=edges.get(i).get(0);
                int dst=edges.get(i).get(1);
                if(adjList.containsKey(src)){
                    adjList.get(src).add(dst);
                }else{
                    List<Integer> neighbors = new ArrayList<>();
                    neighbors.add(dst);
                    adjList.put(src, neighbors);
                }
                if(adjList.containsKey(dst)){
                    adjList.get(dst).add(src);
                } else{
                    List<Integer> neighbors = new ArrayList<>();
                    neighbors.add(src);
                    adjList.put(dst, neighbors);
                }
            }
            numNodes = n;
            bridges = new ArrayList<>();
        }
        // Tarjan's algorithm. Time:O(V+E) same as DFS
        void bridgeCC(int u, boolean[] visited, int[] parent, int[] discovery, int[] low, int time){
            visited[u] = true;
            // set discovery time and smallest disc time of all nodes rooted at u (low)
            discovery[u] = time;
            low[u] = time;
            List<Integer> adjNodes = adjList.get(u);
            if(adjNodes != null){
                for(int v: adjNodes){
                    if(!visited[v]){
                        parent[v] = u; // v is visited via u
                        bridgeCC(v, visited, parent, discovery, low, time+1); // next level node
                        // Check if the subtree rooted with v has a 
                        // connection to one of the ancestors of u 
                        low[u]  = Math.min(low[u], low[v]); 
                        // earliest node's timestamp in v substree is greater than u's discovery
                        if(low[v] > discovery[u]){ // u-v is a bridge
                            List<Integer> edge = new ArrayList<>();
                            edge.add(u);
                            edge.add(v);
                            bridges.add(edge);
                        }
                    } else if(v != parent[u]){ // v was discovered before u
                        // update the low[u], if v has an earlier discovery time
                        low[u] = Math.min(low[u], discovery[v]);
                    }
                }
            }
        }
        List<List<Integer>> findAllBridges(){
            boolean[] visited = new boolean[numNodes];
            int[] parent = new int[numNodes];
            for(int i=0;i<numNodes;i++){
                parent[i] = -1;
            }
            int[] discovery = new int[numNodes];
            int[] low = new int[numNodes];
            for(int i=0;i<numNodes;i++){
                if(!visited[i]){
                    bridgeCC(i, visited, parent, discovery, low, 0);
                }
            }
            return bridges;
        }
    }
    /**
     * Naive idea: remove each edge and check if dfsConnectedNodes decrease
     * Time: O(E* (V+E)) => costly
     *
     * Tarjan's algorithm: Use DFS to find the bridges. In a DFS tree, u->v is a bridge
     * if there does not exist any other alternative to reach u or an ancestor of u from subtree rooted with v.
     * If the subtree rooted at v has a back edge to some ancestor of u, then current edge is not a bridge.
     * To do this, we maintain the first discovered times of all nodes in the current DFS tree.
     * For each child v, find the smallest such value (low[v]), if this is a timestamp after the time when u was
     * discovered, then all nodes in v subtree were discovered only via this edge -> bridge.
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph graph = new Graph(n, connections);
        return graph.findAllBridges();
    }
}
