package datastructures.graphs;

import java.util.*;

public class ShortestPathAlternatingColors {
    class Node{
        int v;
        Boolean redIncomingEdge;
        Node(int v, Boolean redEdge) {
            this.v = v;
            this.redIncomingEdge = redEdge;
        }
    }
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<List<Integer>> redAdjList = new LinkedList<List<Integer>>();
        for(int i=0;i<n;i++) {
            redAdjList.add(new ArrayList<Integer>());
        }
        List<List<Integer>> blueAdjList = new LinkedList<List<Integer>>();
        for(int i=0;i<n;i++) {
            blueAdjList.add(new ArrayList<Integer>());
        }
        for(int i=0;i<red_edges.length;i++) {
            redAdjList.get(red_edges[i][0]).add(red_edges[i][1]);
        }
        for(int i=0;i<blue_edges.length;i++) {
            blueAdjList.get(blue_edges[i][0]).add(blue_edges[i][1]);
        }
        int[] answers = new int[n];
        for(int i=1;i<n;i++) {
            answers[i] = -1;
        }
        Queue<Node> nodes = new LinkedList<Node>();
        nodes.add(new Node(0, null));
        while(!nodes.isEmpty()){
            Node currNode = nodes.poll();
            if(currNode.redIncomingEdge == null || !currNode.redIncomingEdge) {
                for(int child: redAdjList.get(currNode.v)) {
                    if(answers[child] == -1){
                        answers[child] = answers[currNode.v] + 1;
                        nodes.add(new Node(child, true));
                    }
                }
            }
            if(currNode.redIncomingEdge == null || currNode.redIncomingEdge) {
                for(int child: blueAdjList.get(currNode.v)) {
                    if(answers[child] == -1){
                        answers[child] = answers[currNode.v] + 1;
                        nodes.add(new Node(child, false));
                    }
                }
            }
        }
        return answers;
    }
}