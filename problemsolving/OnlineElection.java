package problemsolving;

/**
 * https://leetcode.com/problems/online-election/
 * 
 * In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

 

Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.
 

Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].

 */
class TopVotedCandidate {
    
    int winner[];
    int times[];
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int numPersons = persons.length+1;
        int[] votes= new int[numPersons];
        
        int maxVotesSoFar = Integer.MIN_VALUE;
        int leadingPersonSoFar = -1;
        
        winner = new int[times.length]; // find a winner/leader at each time point
        for(int i=0;i<times.length;i++){
            votes[persons[i]]+=1;
            if(votes[persons[i]] >= maxVotesSoFar){ // equals to max because the person with latest vote which is person[i] leads!
                // leader
                maxVotesSoFar = votes[persons[i]];
                leadingPersonSoFar = persons[i];
            }
            winner[i]= leadingPersonSoFar;
        }
    }
    int getInsertPosition(int t){
        int l=0,r=times.length-1;
        int insertPosition =-1;
        while(l<r){
            int mid=l+(r-l)/2;
            if(times[mid]==t){
                insertPosition = mid;
                break;
            } else if(t<times[mid]){
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return insertPosition != -1 ? insertPosition : l;
    }
    public int q(int t) {
        // get t's insert position in times array and get winner
        int timePoint = getInsertPosition(t);
        return t>=times[timePoint] ? winner[timePoint] : winner[timePoint-1];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */