package datastructures.graphs;

import java.util.*;

// https://leetcode.com/problems/largest-component-size-by-common-factor/
public class LargestComponent {
    // APPROACH 1 - build adjList and count componentSize using DFS
    int gcd(int x, int y){
        if(y == 0){
            return x;
        }
        return gcd(y, x % y);
    }
    int getConnectedCompSize(int v, Map<Integer, Boolean> visited, Map<Integer, List<Integer>> adjList){
        visited.put(v, true);
        int children = 0;
        if(adjList.get(v) != null){
            for(Integer n: adjList.get(v)){
                if(!visited.getOrDefault(n, false)){
                    children += getConnectedCompSize(n, visited, adjList);   
                }
            }
        }
        return 1 + children;
    }
    /**
     * Given a non-empty array of unique positive integers A, consider the following graph:

        There are A.length nodes, labelled A[0] to A[A.length - 1];
        There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
        Return the size of the largest connected component in the graph.

        

        Example 1:

        Input: [4,6,15,35]
        Output: 4

        Example 2:

        Input: [20,50,9,63]
        Output: 2

     * @param A
     * @return
     */
    public int largestComponentSize_timeLimit(int[] A) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i=0;i<A.length;i++){
            for(int j=i+1;j<A.length;j++){
                int gcdValue = gcd(A[i], A[j]);
                // System.out.println(A[i] + "," + A[j] + "=" + gcdValue);
                if(gcdValue != 1){
                    // forward edge
                    if(adjList.get(A[i]) == null){
                        List<Integer> neighbors = new ArrayList<>();
                        neighbors.add(A[j]);
                        adjList.put(A[i], neighbors);   
                    } else {
                        adjList.get(A[i]).add(A[j]);
                    }
                    // backward edge
                    if(adjList.get(A[j]) == null){
                        List<Integer> neighbors = new ArrayList<>();
                        neighbors.add(A[i]);
                        adjList.put(A[j], neighbors);   
                    } else {
                        adjList.get(A[j]).add(A[i]);
                    }
                }
            }
        }
        Map<Integer, Boolean> visited = new HashMap<>();
        int maxConnectedCompSize = 0;
        for(int i=0;i<A.length;i++){
            if(!visited.getOrDefault(A[i], false)){
                int compSize = getConnectedCompSize(A[i], visited, adjList);
                if(compSize > maxConnectedCompSize){
                    maxConnectedCompSize = compSize;
                }
            }
        }
        return maxConnectedCompSize;
    }
    // APPROACH 2 - build graph using find-union with isGCD!=1 as edge/relationship
    int find(Map<Integer, Integer> parent, int x){
        if(parent.get(x) == null){
            return x;
        }
        int p = find(parent, parent.get(x));
        parent.put(x, p);
        return p;
    }
    int union(int x, int y, int parent1, int parent2, Map<Integer, Integer> parent, Map<Integer, Integer> counts){
        if(counts.get(parent1) < counts.get(parent2)){
            parent.put(parent1, parent2); // make 2 the parent of 1
            counts.put(parent2, counts.getOrDefault(parent1, 0) + counts.getOrDefault(parent2, 0));
            return counts.get(parent2);
        } else if(counts.get(parent1) > counts.get(parent2)){
            parent.put(parent2, parent1); // make 1 the parent of 2
            counts.put(parent1, counts.getOrDefault(parent1, 0) + counts.getOrDefault(parent2, 0));
            return counts.get(parent1);
        } else{ // equal sizes
            parent.put(parent1, parent2); // make 2 the parent of 1   
            counts.put(parent2, counts.getOrDefault(parent1, 0) + counts.getOrDefault(parent2, 0));
            return counts.get(parent2);
        }
    }
    public int largestComponentSize_alsoTimeLimit(int[] A) {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> clustersizes = new HashMap<>();
        for(int i=0;i<A.length;i++){ // single node clusters
            clustersizes.put(A[i], 1);
        }
        int maxComponentSize = A.length > 0 ? 1 : 0;
        for(int i=0;i<A.length;i++){
            for(int j=i+1;j<A.length;j++){
                int gcdValue = gcd(A[i], A[j]);
                if(gcdValue != 1){
                    int parent1 = find(parent, A[i]);
                    int parent2 = find(parent, A[j]);
                    if(parent1 != parent2){
                        int size = union(A[i], A[j],parent1, parent2,  parent, clustersizes);
                        // System.out.println("combining " + parent1 + ","+ parent2 + "->" +size);
                        if(size > maxComponentSize){
                            maxComponentSize = size;
                        }   
                    }
                }
            }
        }
        return maxComponentSize;
    }
    // APPROACH 3 - reduce complexity on factor finding
    private int findParent (int parentKey, HashMap<Integer, Integer> parent) {
        
        if (!parent.containsKey (parentKey)) {
            parent.put (parentKey, parentKey);
        }
        
        while (parentKey != parent.get (parentKey)) {
            parentKey = parent.get (parentKey);
        }
        
        return parentKey;
    }
    
    private void union (int num, int factor, HashMap<Integer, Integer> parent) {
        
        int numParent = findParent (num, parent);
        int factorParent = findParent (factor, parent);
        
        if (numParent < factorParent) {
            parent.put (factorParent, numParent);
        }
        else {
            parent.put (numParent, factorParent);
        }
    }
    
    public int largestComponentSize (int[] A) {
        
        HashMap<Integer, Integer> parent = new HashMap <>();
        // builds a forest using is_factor relationship as edges between num, factor
        for (int num : A) {
            for (int factor = 2; factor * factor <= num; factor++) {	// O(logN) for factor finding
                if (num % factor == 0) {
                    union (num, factor, parent);
                    if (num / factor != factor) {
                        union (num, num / factor, parent);
                    }
                }
            }
        }
        
        HashMap<Integer, Integer> connectedComponent = new HashMap <>();
        int maxComponent = 0;
        
        // calculates cluster sizes for each parent (could be factor or num)
        for (int num : A) {
            int parentKey = findParent (num, parent);
            connectedComponent.put (parentKey, connectedComponent.getOrDefault (parentKey, 0) + 1);
            maxComponent = Math.max (maxComponent, connectedComponent.get (parentKey));
        }
        
        return maxComponent;
    }
}