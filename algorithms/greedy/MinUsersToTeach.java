package algorithms.greedy;

import java.util.*;
import java.util.function.*;

//https://leetcode.com/problems/minimum-number-of-people-to-teach/
public class MinUsersToTeach {
    void addLink(Map<Integer, List<Integer>> friends, int partnerA, int partnerB){
        if(!friends.containsKey(partnerA)){
            List<Integer> friendLinks = new ArrayList<>();
            friendLinks.add(partnerB);
            friends.put(partnerA, friendLinks);
        } else{
            friends.get(partnerA).add(partnerB);
        }
    }
    // somehow BFS with visited leads to less number of users to teach
    int canCommunicate(int start, int newLang, Map<Integer, List<Integer>> friends, Map<Integer, Set<Integer>> langsKnown, Set<Integer> visited){
        Queue<Integer> users = new LinkedList<>();
        users.add(start);
        Set<Integer> newUsers = new HashSet<>();
        while(!users.isEmpty()){
            int user = users.poll();
            visited.add(user);
            Set<Integer> myLang = langsKnown.get(user);
            if(friends.get(user) != null){
                for(int friend : friends.get(user)){
                    Set<Integer> friendLang = new HashSet<>(langsKnown.get(friend));
                    friendLang.retainAll(myLang);
                    if(friendLang.size() == 0 && !myLang.contains(newLang)){
                        newUsers.add(user);
                    }
                    if(friendLang.size() == 0 && !langsKnown.get(friend).contains(newLang)){
                        newUsers.add(friend);
                    }
                    if(!visited.contains(friend)){
                        users.add(friend);
                    }
                }   
            }
        }
        return newUsers.size();
    }
    /**
     * On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

        You are given an integer n, an array languages, and an array friendships where:

        There are n languages numbered 1 through n,
        languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
        friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
        You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

        Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
        

        Example 1:

        Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
        Output: 1
        Explanation: You can either teach user 1 the second language or user 2 the first language.
        Example 2:

        Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
        Output: 2
        Explanation: Teach the third language to users 1 and 2, yielding two users to teach.
        

        Constraints:

        2 <= n <= 500
        languages.length == m
        1 <= m <= 500
        1 <= languages[i].length <= n
        1 <= languages[i][j] <= n
        1 <= u​​​​​​i < v​​​​​​i <= languages.length
        1 <= friendships.length <= 500
        All tuples (u​​​​​i, v​​​​​​i) are unique
        languages[i] contains only unique values

        Idea: You can just use brute force and find out for each language the number of users you need to teach.
        Note that a user can appear in multiple friendships but you need to teach that user only once.
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // adj Map
        Map<Integer, List<Integer>> friends = new HashMap<>();
        Map<Integer, Set<Integer>> langsKnown = new HashMap<>();
        int user=1;
        int m = 0;
        for(int[] langs: languages){
            langsKnown.put(user, Arrays.stream(langs).boxed().collect(Collectors.toSet()));
            OptionalInt max = Arrays.stream(langs).max();
            if(max.isPresent()){
                m = Math.max(m, max.getAsInt());   
            }
            user+=1;
        }
        // only record edges where friends can't communicate with each other
        for(int[] link: friendships){
            int partnerA = link[0];
            int partnerB = link[1];
            Set<Integer> myLang = langsKnown.get(partnerA);
            Set<Integer> friendLang = new HashSet<>(langsKnown.get(partnerB));
            friendLang.retainAll(myLang);
            if(friendLang.size() == 0){
                addLink(friends, partnerA, partnerB);
                addLink(friends, partnerB, partnerA);   
            }
        }
        int minUsers = Integer.MAX_VALUE;
        // for each lang, find the number of users who need to learn it in order to communicate with their friends
        for(int lang=1;lang<=m;lang++){            
            // int usersToTeach = canCommunicate(1, lang, friends, langsKnown, new HashSet<>());
            int usersToTeach =0;
            for(int userId: friends.keySet()){
                if(!langsKnown.get(userId).contains(lang)){
                    usersToTeach+=1;
                }
            }
            minUsers = Math.min(minUsers,usersToTeach);
        }
        return minUsers;
    }
}
