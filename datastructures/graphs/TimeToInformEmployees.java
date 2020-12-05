package datastructures.graphs;

import java.util.*;

// https://leetcode.com/problems/time-needed-to-inform-all-employees/
public class TimeToInformEmployees {
    int maxPathLength;
    // DFS - time consuming and leads to Time Limit exceeded
    void dfsUtil(int v, List<List<Integer>> adjList, int[] informTime, boolean[] visited, int pathLength){
        visited[v] = true;
        if(informTime[v] == 0){
            maxPathLength = Math.max(maxPathLength, pathLength);
            return;
        }
        for(int subord: adjList.get(v)){
            if(!visited[subord]){
                dfsUtil(subord, adjList, informTime, visited, pathLength + informTime[v]);   
            }
        }
    }
    class EmpNode implements Comparable<EmpNode>{
        int empId;
        int timeTaken;
        EmpNode(int id, int time){
            this.empId = id;
            this.timeTaken = time;
        }
        public int compareTo(EmpNode other){
            return other.timeTaken - this.timeTaken;
        }
    }
    /**
     * 
     * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.

        Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.

        The head of the company wants to inform all the employees of the company of an urgent piece of news. He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.

        The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes, all his direct subordinates can start spreading the news).

        Return the number of minutes needed to inform all the employees about the urgent news.

    Example 1:

    Input: n = 1, headID = 0, manager = [-1], informTime = [0]
    Output: 0
    Explanation: The head of the company is the only employee in the company.

    Input: n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
    Output: 21
    Explanation: The head has id = 6. He will inform employee with id = 5 in 1 minute.
    The employee with id = 5 will inform the employee with id = 4 in 2 minutes.
    The employee with id = 4 will inform the employee with id = 3 in 3 minutes.
    The employee with id = 3 will inform the employee with id = 2 in 4 minutes.
    The employee with id = 2 will inform the employee with id = 1 in 5 minutes.
    The employee with id = 1 will inform the employee with id = 0 in 6 minutes.
    Needed time = 1 + 2 + 3 + 4 + 5 + 6 = 21.

    Idea: The informTime touches all children (sort of like level order traversal).
    While doing this maintain the timetaken for info to reach a node in the queue itself.
    Total time is nothing but the maximum of all the times in the last level.

     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // using level order traversal
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        // builds a directed graph from manager to his/her subordinates. as info flows from the top.
        for(int i=0;i<n;i++){
            int src=manager[i];
            int dst=i;
            if(src != -1){
                if(adjList.containsKey(src)){
                    adjList.get(src).add(dst);
                } else {
                    List<Integer> neighbors = new ArrayList<>();
                    neighbors.add(dst);
                    adjList.put(src, neighbors);
                }
            }
        }
        // keep track of maxtime taken to reach any node during level order traversal.
        int totalInformTime = 0;
        Queue<EmpNode> nodes = new LinkedList<>();
        nodes.add(new EmpNode(headID, 0));
        while(!nodes.isEmpty()){
            int levelSize = nodes.size();
            for(int i=0;i<levelSize;i++){
                EmpNode node = nodes.poll();
                if(adjList.get(node.empId) != null){ // has some lower level nodes to inform
                    int time = informTime[node.empId];
                    int totalTime = node.timeTaken + time;
                    if(totalTime > totalInformTime){
                        totalInformTime = totalTime;
                    }
                    for(int neighbor : adjList.get(node.empId)){
                        nodes.add(new EmpNode(neighbor, totalTime));    
                    }   
                }
            }
        }
        return totalInformTime;
    }
}
