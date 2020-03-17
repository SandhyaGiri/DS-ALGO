package datastructures.graphs;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 */
class EulerCircuitDirected {
    /**
     * https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/
     * 
     * Returns euler circuit or path in a directed graph.
     * O(V+E)
     * @param startNode
     * @param adjList
     * @return
     */
    List<String> getEulerCircuit(String startNode, Map<String, List<String>> adjList) {
        Stack<String> currPath = new Stack<String>();
        currPath.push(startNode);
        
        List<String> circuit = new LinkedList<String>();
        String currNode = startNode;
        
        while(!currPath.isEmpty()) {
            if(adjList.get(currNode) != null && adjList.get(currNode).size() >= 1) {
                // take the first lexical exit
                String tmpNode = adjList.get(currNode).get(0);
                adjList.get(currNode).remove(0);
                currNode = tmpNode;
                currPath.push(currNode);
            } else {
                // backtrack, pop last visited node from path and put it in circuit
                circuit.add(currPath.pop());
                if(!currPath.isEmpty()) {
                    currNode = currPath.peek();   
                }
            }
        }
        // reverse the list
        Collections.reverse(circuit);
        return circuit;
    }

    /**
     * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

        Note:

        If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
        All airports are represented by three capital letters (IATA code).
        You may assume all tickets form at least one valid itinerary.
        Example 1:

        Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
        Example 2:

        Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                    But it is larger in lexical order.

        Input: [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> adjList = new HashMap<String, List<String>>();
        for(List<String> ticket: tickets) {
            String src = ticket.get(0);
            String dest = ticket.get(1);
            List<String> neighbors = adjList.get(src);
            if(neighbors == null) {
                neighbors = new ArrayList<String>();
                neighbors.add(dest);
                adjList.put(src, neighbors);
            } else {
                neighbors.add(dest);
            }
        }
        // sort the destinations (lexically)
        for(Map.Entry<String, List<String>> entry: adjList.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        return getEulerCircuit("JFK", adjList);
    }
}