package algorithms.greedy;

import java.util.*;

public class MeetingRooms {
    // https://leetcode.com/problems/meeting-rooms/
    /**
     * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

        Example 1:

        Input: intervals = [[0,30],[5,10],[15,20]]
        Output: false
        Example 2:

        Input: intervals = [[7,10],[2,4]]
        Output: true

     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(int[][] intervals) {
        // sort by asc order of start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] prevInterval = null;
        if(intervals.length > 0){
            prevInterval = intervals[0];
        }
        for(int i=1;i<intervals.length;i++){
            int currStart = intervals[i][0];
            if(currStart<prevInterval[1]){ // starts earlier than previous meeting ends
                return false;
            }
            prevInterval = intervals[i];
        }
        return true;
    }

    //https://leetcode.com/problems/meeting-rooms-ii/
    /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

        Example 1:

        Input: [[0, 30],[5, 10],[15, 20]]
        Output: 2
        Example 2:

        Input: [[7,10],[2,4]]
        Output: 1

        Idea: Sort by start time. We already know the rooms we have allocated till now and we also know when are they due to get free because of the
        end times of the meetings going on in those rooms.
        We can simply check the room which is due to get vacated the earliest amongst all the allocated rooms. This we do storing end times
        of all meetings that are happening concurrently in a min heap -> serves the earliest end time. If curr meeting starts earlier than earliest
        end time, allocate a new room (enqueue current end time in heap). if the meeting starts after earliest end time, keep popping such meeting rooms.
        min conference rooms is the max size of this heap at any time.

        Time: O(n * log n)
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        // asc order of start time
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        int maxConcurrentMeetings = 0; // same as max rooms
        PriorityQueue<Integer> meetingEndTimes = new PriorityQueue<>();
        for(int[] interval: intervals){
            if(meetingEndTimes.isEmpty() || interval[0] < meetingEndTimes.peek()){
                // allocate new room if no concurrent meeting, or
                // if new meeting starts before the earliest ending meeting room
                meetingEndTimes.add(interval[1]);
            } else {
                // complete previously occuring meetings as new one is later than their ending times
               while(!meetingEndTimes.isEmpty() && interval[0] >= meetingEndTimes.peek()){
                    meetingEndTimes.poll();
               }
               meetingEndTimes.add(interval[1]); 
            }
            if(meetingEndTimes.size() > maxConcurrentMeetings){
                maxConcurrentMeetings = meetingEndTimes.size();
            }
        }
        return maxConcurrentMeetings;
    }
}
