package datastructures.graphs;

import java.util.*;

// https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
public class ReachableCitiesAtThreshold {
    class Node{
        int v;
        int weight;
        Node(int vertex, int edgeWeight){
            this.v = vertex;
            this.weight = edgeWeight;
        }
    }
    class PathNode implements Comparable<PathNode>{
        int v;
        int dist;
        PathNode(int vertex, int distance){
            this.v = vertex;
            this.dist = distance;
        }
        public int compareTo(PathNode other){
            return this.dist - other.dist;
        }
    }
    class Graph{ // weighted graph 0.. n-1 vertices
        int numNodes;
        Map<Integer, List<Node>> adjList;
        Graph(int n){
            this.numNodes = n;
            adjList = new HashMap<>();
        }
        private void addEdgeForSource(int source, Node neighbor){
            if(adjList.containsKey(source)){
                adjList.get(source).add(neighbor);
            } else {
                List<Node> adjNodes = new LinkedList<>();
                adjNodes.add(neighbor);
                adjList.put(source, adjNodes);
            }
        }
        void addEdge(int[] edge){
            int source = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            Node neighbor = new Node(dest, weight);
            Node backNeighbor = new Node(source, weight);
            addEdgeForSource(source, neighbor);
            addEdgeForSource(dest, backNeighbor);
        }
        int[] findShortestDistance(int source){
            // Djikstra's algo - adj list - O(E * logV)
            // returns shortest distances (path lengths) from source to all other vertices
            int[] dist = new int[this.numNodes];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;
            boolean[] sptSet = new boolean[this.numNodes];
            PriorityQueue<PathNode> closestNodes = new PriorityQueue<>();
            closestNodes.add(new PathNode(source, 0));
            while(!closestNodes.isEmpty()){
                PathNode node = closestNodes.poll();
                // add to sptSet
                sptSet[node.v] = true;
                // update distances to neighbors
                if(this.adjList.get(node.v) != null){
                    for(Node neighbor: this.adjList.get(node.v)){
                        if(!sptSet[neighbor.v]){ // yet to be picked
                            if((node.dist + neighbor.weight) < dist[neighbor.v]){
                                // System.out.println("old dist to" + neighbor.v+ ":" + dist[neighbor.v]);
                                // remove old path node
                                closestNodes.remove(new PathNode(neighbor.v, dist[neighbor.v]));
                                // update dist
                                dist[neighbor.v] = node.dist + neighbor.weight;
                                // System.out.println("new dist to " + neighbor.v+ ":" + dist[neighbor.v]);
                                // enqueue new shortest pathnode
                                closestNodes.add(new PathNode(neighbor.v, dist[neighbor.v]));
                            }
                        }
                    }   
                }
            }
            return dist;
        }
    }
    /**
    * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

    Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

    Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

    

    Example 1:



    Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
    Output: 3
    Explanation: The figure above describes the graph. 
    The neighboring cities at a distanceThreshold = 4 for each city are:
    City 0 -> [City 1, City 2] 
    City 1 -> [City 0, City 2, City 3] 
    City 2 -> [City 0, City 1, City 3] 
    City 3 -> [City 1, City 2] 
    Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
    Example 2:



    Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
    Output: 0
    Explanation: The figure above describes the graph. 
    The neighboring cities at a distanceThreshold = 2 for each city are:
    City 0 -> [City 1] 
    City 1 -> [City 0, City 4] 
    City 2 -> [City 3, City 4] 
    City 3 -> [City 2, City 4]
    City 4 -> [City 1, City 2, City 3] 
    The city 0 has 1 neighboring city at a distanceThreshold = 2.

    Idea: Since cities must lie within a threshold dis to be considered reachable, we have to find shortest path length to
    neighboring cities from source. Then filter based on path length <= t. Therefor run Djikstra's algo to find shortest path
    lengths from single source to all other v. Do this for each city, and find reachablecities. Then find largest indexed city with smallest
    reachable cities.
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // convert edges to adj list
        Graph graph = new Graph(n);
        for(int i=0;i<edges.length;i++){
            graph.addEdge(edges[i]);
        }
        // for each v, find shortest path lengths to other vertices
        // count cities/nodes which lie within the threshold
        int minReachableCities = Integer.MAX_VALUE;
        int cityWithMinReachableCities = -1;
        for(int i=0;i<n;i++){
            // i as source find dis to all other nodes
            int[] disToAllNodes = graph.findShortestDistance(i);
            // System.out.println(Arrays.toString(disToAllNodes));
            int reachableCities = 0;
            for(int j=0;j<n;j++){
                if(j!=i && disToAllNodes[j] <= distanceThreshold){
                    reachableCities+=1;
                }
            }
            // System.out.println(reachableCities);
            if(reachableCities < minReachableCities){
                minReachableCities = reachableCities;
                cityWithMinReachableCities = i;
            }
            else if(reachableCities == minReachableCities){
                cityWithMinReachableCities = Math.max(cityWithMinReachableCities, i);
            }
        }
        return cityWithMinReachableCities;
    }
}