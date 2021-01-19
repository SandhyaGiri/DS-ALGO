package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/car-pooling/
public class CarPooling {
    /**
     * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

        Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

        Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

        

        Example 1:

        Input: trips = [[2,1,5],[3,3,7]], capacity = 4
        Output: false
        Example 2:

        Input: trips = [[2,1,5],[3,3,7]], capacity = 5
        Output: true
        Example 3:

        Input: trips = [[2,1,5],[3,5,7]], capacity = 3
        Output: true
        Example 4:

        Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
        Output: true
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // sorts the x locations (due east) so that we can drive accordingly
        TreeMap<Integer, Integer> locationToNumPassengers = new TreeMap<>();
        int numTrips = trips.length;
        for(int i=0;i<numTrips;i++){
            int[] trip = trips[i];
            // at start loc, new passengers get in, add these values
            // Note: map can have only unique keys, so keep updating already existing values
            locationToNumPassengers.put(trip[1], locationToNumPassengers.getOrDefault(trip[1], 0) + trip[0]);
            // at dest loc, passengers get out, subtract these values
            locationToNumPassengers.put(trip[2], locationToNumPassengers.getOrDefault(trip[2], 0) - trip[0]);
        }
        // now check if at any point numPassengers in car exceeds capacity
        for(int location: locationToNumPassengers.keySet()){
            int numPassengers = locationToNumPassengers.get(location);
            // System.out.println(numPassengers);
            if(numPassengers > 0 && numPassengers > capacity){ // new set of passengers exceed empty seats
                return false;
            }
            capacity -= numPassengers; // +ve values are subtracted, -ve values are added
            // System.out.println(capacity);
        }
        return true;
    }
}
