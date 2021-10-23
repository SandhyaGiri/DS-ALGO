package javaproblems;

import java.util.*;

// https://leetcode.com/problems/design-underground-system/
public class UndergroundRailwaySystem {
    class TravelDetails{
        int totalTime;
        int numTravels;
        TravelDetails(int t, int n){
            this.totalTime = t;
            this.numTravels = n;
        }
    }
    class StationPair{
        String source;
        String destination;
        StationPair(String src, String dest){
            this.source = src;
            this.destination = dest;
        }
        public boolean equals(Object y){
            // pairs with same src and dest are indeed equal.
            if(y instanceof StationPair){
                StationPair other = (StationPair)y;
                return this.source.equals(other.source) && this.destination.equals(other.destination);
            }
            return false;
        }
        public int hashCode(){
            // hashMap first calls hashCode()
            // if hashcode is same, then equals() is invoked to check if they are actually equal
            return source.hashCode() + destination.hashCode();
        }
    }
    class CheckInDetails{
        String stationName;
        int time;
        CheckInDetails(String name, int t ){
            this.stationName =name;
            this.time=t;
        }
    }
    /**
     * An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.

        Implement the UndergroundSystem class:

        void checkIn(int id, string stationName, int t)
        A customer with a card ID equal to id, checks in at the station stationName at time t.
        A customer can only be checked into one place at a time.
        void checkOut(int id, string stationName, int t)
        A customer with a card ID equal to id, checks out from the station stationName at time t.
        double getAverageTime(string startStation, string endStation)
        Returns the average time it takes to travel from startStation to endStation.
        The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
        The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
        There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
        You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.


        Example 1:

        Input
        ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
        [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

        Output
        [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

        Explanation
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
     */
    class UndergroundSystem {
    
        Map<Integer, CheckInDetails> customerCheckins;
        Map<StationPair, TravelDetails> completedJourneys;
        public UndergroundSystem() {
            customerCheckins = new HashMap<>();
            completedJourneys = new HashMap<>();
        }
        
        public void checkIn(int id, String stationName, int t) {
            customerCheckins.put(id, new CheckInDetails(stationName, t));
        }
        
        public void checkOut(int id, String stationName, int t) {
            CheckInDetails details = customerCheckins.get(id);
            // populate completedJourneys for avg time computation
            int totalTimeTaken = t-details.time;
            StationPair pair = new StationPair(details.stationName, stationName);
            if(completedJourneys.containsKey(pair)){
                TravelDetails previousJourneys = completedJourneys.get(pair);
                previousJourneys.totalTime += totalTimeTaken;
                previousJourneys.numTravels += 1;
                completedJourneys.put(pair, previousJourneys);
            } else {
                completedJourneys.put(pair, new TravelDetails(totalTimeTaken, 1));
            }
        }
        
        public double getAverageTime(String startStation, String endStation) {
            StationPair pair = new StationPair(startStation, endStation);
            TravelDetails previousJourneys = completedJourneys.get(pair);
            return (previousJourneys.totalTime/(double)previousJourneys.numTravels);
        }
    }
    
    /**
     * Your UndergroundSystem object will be instantiated and called as such:
     * UndergroundSystem obj = new UndergroundSystem();
     * obj.checkIn(id,stationName,t);
     * obj.checkOut(id,stationName,t);
     * double param_3 = obj.getAverageTime(startStation,endStation);
     */
}
