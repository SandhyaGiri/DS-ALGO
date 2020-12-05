package datastructures.arrays;

import java.util.*;

// https://leetcode.com/problems/maximize-distance-to-closest-person/
public class MaxDistanceSeat {
    /**
     * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat,
     * and seats[i] = 0 represents that the ith seat is empty (0-indexed).

        There is at least one empty seat, and at least one person sitting.

        Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

        Return that maximum distance to the closest person.

        seats = [1,0,0,0,1,0,1]
        Output: 2
        Explanation: 
        If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
        If Alex sits in any other open seat, the closest person has distance 1.
        Thus, the maximum distance to the closest person is 2.

        Idea: two types of freeseats -> 1. at corners, all of them amount to distance to next person.
        2. in between two occupied seats, only half of them amount to max distance to next person, as we will
        have a person sitting on either side.
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int maxDistance = Integer.MIN_VALUE;
        int prevOccupied = -1;
        for(int i=0;i<seats.length;i++){
            if(seats[i] == 1){ // occupied
                if(prevOccupied == -1){
                    prevOccupied = i;
                    maxDistance = Math.max(maxDistance, i);
                } else {
                    maxDistance = Math.max(maxDistance, (i-prevOccupied-2)/2 + 1);
                    prevOccupied = i;
                }
            }
        }
        // last corner check
        if((seats.length-1) != prevOccupied){
            maxDistance = Math.max(maxDistance, seats.length-prevOccupied-1);
        }
        return maxDistance == Integer.MIN_VALUE ? 0 : maxDistance;
    }
}

// https://leetcode.com/problems/exam-room/
class ExamRoom {

    // Just mainatian the already assigned seat numbers in a list.
    List<Integer> occupiedSeats;
    int totalSeats;
    public ExamRoom(int N) {
        occupiedSeats = new LinkedList<>();
        totalSeats = N;
    }
    
    /**
     * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

    When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. 
    If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room,
    then the student sits at seat number 0.)

    Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student
    sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room. 
    It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

    Idea: gaps at the corners can be used as distance directly, gaps between two occpied seat numbers should be reduced by half
    including the new seat. Find the largest distance to next person, and also maintain where the new seat needs to be inserted.
    That way we always maintain the list in a sorted fashion! (this would resemble the original array without having to store all 0s
    where the seat is still free.)

    Time: O(#occupiedSeats) at any time.
    Space: O(N)
     * @return
     */
    public int seat() {
        int seat = -1;
        if(occupiedSeats.size() == 0){
            seat = 0;
            occupiedSeats.add(0);
        } else {
            // find largest gap
            int prevSeat = -1;
            int maxDistance = Integer.MIN_VALUE;
            int maxInsertAfterPos = -1;
            for(int i=0;i<occupiedSeats.size();i++){
                int currSeat = occupiedSeats.get(i);
                if(prevSeat == -1){
                    if(currSeat > maxDistance) {
                        maxDistance = currSeat;
                        seat = 0;
                        maxInsertAfterPos = -1;
                    }
                } else if(currSeat>prevSeat+1){ // otherwise no empty seat in between
                    int dis = (currSeat-prevSeat-2)/2 +1;
                    if (dis > maxDistance){
                        maxDistance = dis;
                        seat = prevSeat + dis;
                        maxInsertAfterPos = i-1;
                    }
                }
                prevSeat = currSeat;
            }
            if(prevSeat != totalSeats-1 && totalSeats-prevSeat-1 > maxDistance){ // some right corner spaces left
                maxDistance = totalSeats-prevSeat-1;
                seat = prevSeat + maxDistance;
                maxInsertAfterPos = occupiedSeats.size()-1;
            }
            // insert the assigned seat
            occupiedSeats.add(maxInsertAfterPos+1, seat);
        }
        System.out.println(occupiedSeats);
        return seat;
    }
    
    // O(n), need to find the number and delete it. (unique as indices are distinct and a person will leave before the seat gets occupied.)
    public void leave(int p) {
        occupiedSeats.remove(Integer.valueOf(p));
        System.out.println("removal" + occupiedSeats);
    }
}