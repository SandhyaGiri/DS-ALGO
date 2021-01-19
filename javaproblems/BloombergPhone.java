package javaproblems;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BloombergPhone {
    class Interval{
        int start;
        int end;
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    class Event{
        int start;
        int end;
        String title;
    }
    
    class Entity {
        String name;
        List<Event> events;
    }
    class Person extends Entity{
        
        // 
    }
    class Room extends Entity{
        
    }
    
    class EventComparator implements Comparator<Event>{
        public int compare(Event a, Event b){
            return a.start == b.start ? Integer.compare(a.end, b.end) : Integer.compare(a.start, b.start);
        }
    }
    // singleton or list of utils for calendar app
    class Scheduler{
        
        List<Interval> getPossibleMeetingSlots(List<Event> eventsOfPersonA, List<Event> eventsOfPersonB){
            
        }
        
        List<Interval> getBusyTimeSlots(List<Event> events){
            List<Interval> result = new ArrayList<>();
            if(events != null){
                // sort events based on their start time (asc), if start time is equal (tie) -> sort asc by end time 
                Collections.sort(events, new EventComparator());
                Integer start=null,end=null;
                for(Event event: events){
                    if(start!= null && end != null && event.start > end+1){
                        result.add(new Interval(start, end));
                        start = null;
                        end = null;
                    }
                    if(start == null){
                        start = event.start;
                    }
                    if(end == null){
                        end = event.end;
                    } else if(event.start>= start && event.start <= end && event.end > end){ // 1-3, 2-3
                        end = event.end;
                    } // else: event is already contained in the current start...end
                }
                // last interval
                if(start != null && end != null){
                    result.add(new Interval(start, end));
                }
            }
            return result;
        }
    
    }
    
    class Meeting{
        List<Person> persons;
        Room room;
    }
    class Calendar{
        Map<String, Person> personSchedules = new HashMap<>();
        Map<String, Room> roomSchedules = new HashMap<>();
        
        Meeting scheduleMeeting(Person personA, Person personB){
            List<Event> eventsOfPerson1 = schedules.get("Chris");
            List<Event> eventsOfPerson2 = schedules.get("Maxim");
            List<Interval> possibleSlots = Scheduler.getPossibleMeetingSlots(eventsOfPerson1, eventsOfPerson2);
            
        }
        
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        /*
          I want a meeting between Chris and Maxim.
         */
        Map<String, List<Event>> schedules = new HashMap<>();
        
    }
}
