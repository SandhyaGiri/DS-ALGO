package javaproblems;

import java.util.*;
import java.util.stream.*;

public class JewelsAndStones {
    /**
     * https://leetcode.com/problems/jewels-and-stones/
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        // convert a string into char stream, actually ints, then map to Character object
        // and use collectors to create a set
        Set<Character> jewels = J.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        int count=0;
        for(int i=0;i<S.length();i++){
            if(jewels.contains(S.charAt(i))){
                count++;
            }
        }
        return count;
    }
}