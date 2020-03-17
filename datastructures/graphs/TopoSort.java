package datastructures.graphs;

import java.util.*;

public class TopoSort {

    /**
     * https://leetcode.com/problems/course-schedule/
     * 
     * There are a total of n courses you have to take, labeled from 0 to n-1.

        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

        Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

        Idea: topo sort with indegree based solution, in degree denoting the number of prerequisites needed for the node. If all nodes
        are met/visited during the toposort, then one can finish all courses given the preqreuisites, otherwise not.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int indegree[] = new int[numCourses];
        List<List<Integer>> adjList = new LinkedList<>();
        for(int i=0;i<numCourses;i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++) {
            int src = prerequisites[i][1]; // prerequisite
            int dst = prerequisites[i][0]; // course
            adjList.get(src).add(dst);
            indegree[dst]++;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<numCourses;i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        int visited = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            visited++;
            for(Integer n:adjList.get(node)){
                indegree[n]--;
                if(indegree[n] == 0){
                    q.add(n);
                }
            }
        }
        return visited == numCourses;
    }

    /**
     * https://leetcode.com/problems/course-schedule-ii
     * 
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
     * 
     * Idea: Just return topo sort of nodes.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int indegree[] = new int[numCourses];
        List<List<Integer>> adjList = new LinkedList<>();
        for(int i=0;i<numCourses;i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++) {
            int src = prerequisites[i][1];
            int dst = prerequisites[i][0];
            adjList.get(src).add(dst);
            indegree[dst]++;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<numCourses;i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        int visited = 0;
        int[] topoOrder = new int[numCourses];
        while(!q.isEmpty()) {
            int node = q.poll();
            topoOrder[visited] = node;
            visited++;
            for(Integer n:adjList.get(node)){
                indegree[n]--;
                if(indegree[n] == 0){
                    q.add(n);
                }
            }
        }
        int[] emptyArr = {};
        return visited == numCourses ? topoOrder : emptyArr;
    }
}