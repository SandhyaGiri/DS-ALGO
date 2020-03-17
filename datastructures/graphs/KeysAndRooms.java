package datastructures.graphs;

import java.util.*;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * 
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Idea: BFSwith maintaining numVisited nodes, if at end, this count is same as num of vertices => then all unlocked otherwise false.
 */
public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean visited[] = new boolean[rooms.size()];
        Queue<Integer> roomNodes = new LinkedList<Integer>();
        roomNodes.add(0);
        int numVisited = 1;
        visited[0] = true;
        while(!roomNodes.isEmpty()) {
            int room = roomNodes.poll();
            List<Integer> unlockedKeys = rooms.get(room);
            for(int key : unlockedKeys) {
                if(!visited[key]) {
                    visited[key] = true;
                    numVisited++;
                    roomNodes.add(key);   
                }
            }
        }
        return (numVisited == rooms.size());
    }
}