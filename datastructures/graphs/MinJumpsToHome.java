package datastructures.graphs;

import java.util.*;

public class MinJumpsToHome {
    class PathNode{
        int pos;
        Boolean isForwardJump; // did forward jump lead to this pathnode, null for start node
        PathNode(int pos, Boolean isForward){
            this.pos = pos;
            this.isForwardJump = isForward;
        }
    }
    /**
        Minimum Jumps to Reach Home
        A certain bug's home is on the x-axis at position x. Help them get there from position 0.

        The bug jumps according to the following rules:

        It can jump exactly a positions forward (to the right).
        It can jump exactly b positions backward (to the left).
        It cannot jump backward twice in a row.
        It cannot jump to any forbidden positions.
        The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.

        Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.

        

        Example 1:

        Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
        Output: 3
        Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
        Example 2:

        Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
        Output: -1
        Example 3:

        Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
        Output: 2
        Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.

        Idea: BFS to get shallowest goal node. But TIME LIMIT EXCEEDED.
        **/
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenPositions = new HashSet<>();
        Set<Integer> visitedPositions = new HashSet<>();
        for(int i=0;i<forbidden.length;i++){
            forbiddenPositions.add(forbidden[i]);
        }
        Queue<PathNode> nodes = new LinkedList<>();
        nodes.add(new PathNode(0, null));
        boolean homeReached = false;
        boolean cycleFound = false;
        int level = 0;
        while(!nodes.isEmpty() && !homeReached){
            int levelSize = nodes.size();
            for(int i=0;i<levelSize;i++){
                PathNode currNode = nodes.poll();
                //System.out.println(currNode.pos);
                visitedPositions.add(currNode.pos);
                if(currNode.pos == x){
                    homeReached = true;
                    break;
                } else {
                    // compute next level nodes
                    // 1. always forward jump is possible
                    int nextForwardPos = currNode.pos + a;
                    if(visitedPositions.contains(nextForwardPos)){
                        cycleFound = true;
                        break;
                    }
                    // visited and forbidden check
                    if(!forbiddenPositions.contains(nextForwardPos)){
                        nodes.add(new PathNode(nextForwardPos, true));
                    }
                    // 2. backward jump only if prev was a forward jump
                    if(currNode.isForwardJump == null || currNode.isForwardJump){
                        int nextBackwardPos = currNode.pos - b;
                        if(visitedPositions.contains(nextBackwardPos)){
                            cycleFound = true;
                            break;
                        }
                        // visited, positive and forbidden check
                        if(nextBackwardPos >= 0 && !forbiddenPositions.contains(nextBackwardPos)){
                            nodes.add(new PathNode(nextBackwardPos, false));
                        }
                    }
                }
            }
            if(!homeReached && !cycleFound){
                level+= 1;   
            } else {
                break;
            }
        }
        return homeReached ? level : -1;
    }
}