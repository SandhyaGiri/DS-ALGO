package datastructures.graphs;

import java.util.*;

public class CheapestFlightKhops {
    class DestNode implements Comparable<DestNode>{
        int v;
        int cost;
        int hops;
        DestNode(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        DestNode(int v, int cost, int hops) {
            this.v = v;
            this.cost = cost;
            this.hops = hops;
        }
        public int compareTo(DestNode node) {
            return (this.cost - node.cost);
        }
    }
    /**
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/
     * 
     * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

        Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
    * @param n
    * @param flights
    * @param src
    * @param dst
    * @param K
    * @return
    */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(flights.length > 0) {
            // get adj list representation
            Map<Integer, List<DestNode>> adjMap = new HashMap<Integer, List<DestNode>>();
            for(int i=0;i<flights.length;i++) {
                int source = flights[i][0];
                int dest = flights[i][1];
                int cost = flights[i][2];
                if(adjMap.get(source) == null) {
                    List<DestNode> neighbors = new ArrayList<DestNode>();
                    neighbors.add(new DestNode(dest, cost));
                    adjMap.put(source, neighbors);
                } else {
                    adjMap.get(source).add(new DestNode(dest, cost));
                }
            }
            // do Best frist search with given cost function (a normal BFS will blindly open nodes without computing any cost fn), so that we always explore min cost paths first.
            // also save hops for comparing with K
            // The worst case time complexity for Best First Search is O(n * Log n) where n is number of nodes. In worst case, we may have to visit all nodes before we reach goal. Note that priority queue is implemented using Min(or Max) Heap, and insert and remove operations take O(log n) time.
            PriorityQueue<DestNode> queue = new PriorityQueue<DestNode>();
            boolean[] visited = new boolean[n];
            queue.add(new DestNode(src, 0, 0));
            int minCost = Integer.MAX_VALUE;
            while(!queue.isEmpty()) {
                DestNode node = queue.poll();
                visited[node.v] = true;
                if(node.v == dst) {
                    // System.out.println(node.v + " " + node.cost + " "+ node.hops);
                    if(node.cost < minCost && node.hops <= K+1) {
                        // System.out.println(node.v + " " + node.cost );
                        minCost = node.cost;
                    }
                } else {
                    // expand and add nodes to queue, with additional cost added
                    if(adjMap.get(node.v) != null) {
                        for(DestNode neigh: adjMap.get(node.v)){
                            if(!visited[neigh.v] || neigh.v == dst) { // you need to visit dst node again, to find the minimal cost
                                queue.add(new DestNode(neigh.v, neigh.cost + node.cost, node.hops + 1));
                                // System.out.println(neigh.v + " " + (neigh.cost + node.cost));
                            }
                        }
                    }   
                }
            }
            return minCost == Integer.MAX_VALUE ? -1 : minCost;
        }
        return -1;
    }
}