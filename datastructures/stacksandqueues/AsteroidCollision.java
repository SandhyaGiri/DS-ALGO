package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/asteroid-collision/
public class AsteroidCollision {
    boolean doCollide(int asteroid1, int asteroid2){
        // happens only when 1 goes right and 2 goes to left
        return asteroid1 > 0 && asteroid2 < 0;
    }
    /**
     * We are given an array asteroids of integers representing asteroids in a row.

        For each asteroid, the absolute value represents its size, and the sign represents its direction
        (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

        Find out the state of the asteroids after all collisions. If two asteroids meet,
        the smaller one will explode. If both are the same size, both will explode.
        Two asteroids moving in the same direction will never meet.

        

        Example 1:

        Input: asteroids = [5,10,-5]
        Output: [5,10]
        Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
        Example 2:

        Input: asteroids = [8,-8]
        Output: []
        Explanation: The 8 and -8 collide exploding each other.
        Example 3:

        Input: asteroids = [10,2,-5]
        Output: [10]
        Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
        Example 4:

        Input: asteroids = [-2,-1,1,2]
        Output: [-2,-1,1,2]
        Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right.
        Asteroids moving the same direction never meet, so no asteroids will meet each other.
     * 
     * Idea: Maintain a stack of all stable asteroids (all collisions have happened).
     * On seeing a new asteroid, check if top of stack is moving right and this to left
     * then -> collision. while there is collision, pop top of stack if it is equal or smaller
     * than asteroid to be inserted. insert the new asteroid finally.
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stableAsteroids = new Stack<>();
        for(int i=0;i<asteroids.length;i++){
            if(stableAsteroids.empty()){
                stableAsteroids.push(asteroids[i]);
            } else {
                Integer asteroid2 = asteroids[i];
                while(!stableAsteroids.empty() && doCollide(stableAsteroids.peek(), asteroid2)){
                    // collisions
                    int asteroid1 = stableAsteroids.peek();
                    int compareResults = Integer.compare(Math.abs(asteroid1), Math.abs(asteroid2));
                    if(compareResults == 0){
                        // equal - both explode
                        asteroid2 = null;
                        stableAsteroids.pop();
                        break;
                    } else if(compareResults < 0){
                        // asteroid2 is the winner, keep comparing with remaining stack elements
                        stableAsteroids.pop();
                    } else {
                        asteroid2 = null;
                        break; // don't push
                    }
                }
                if(asteroid2 != null){
                    stableAsteroids.push(asteroid2);
                }
            }
        }
        return stableAsteroids.stream().mapToInt(Integer::intValue).toArray();
    }
}
