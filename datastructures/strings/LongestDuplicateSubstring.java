package datastructures.strings;

import java.util.*;

// https://leetcode.com/problems/longest-duplicate-substring/
public class LongestDuplicateSubstring {
    /**
     * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.

        Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".

        Example 1:

        Input: s = "banana"
        Output: "ana"
        Example 2:

        Input: s = "abcd"
        Output: ""

     * @param S
     * @return
     */
    public String longestDupSubstring(String S) {
        int lo = 1;
        int hi = S.length();
        while (lo != hi) {
            int mi = lo + (hi - lo) / 2;
            if (possible(S, mi) != -1)
                lo = mi + 1;
            else
                hi = mi;
        }
        int start = possible(S, lo - 1);
        return start != -1 ? S.substring(start, start + lo - 1) : "";
    }
    // Robin Karp algorithm for pattern matching checks if a substr of len m is a duplicate
    // If so returns its index
    // https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
    public int possible(String s, int len) {
        long hash = 0;
        int a = 26;
        int n = s.length();
        final long mod = (long)Math.pow(2, 32);
        // computing hash of the first substr of length len
        for(int i = 0; i < len; ++i) 
            hash = (hash * a + (s.charAt(i)-'a')) % mod;

        HashSet<Long> set = new HashSet();
        set.add(hash);
        long global = 1;
        // just computing h in RK algo
        for (int i = 0; i < len; ++i) 
            global = (global * a) % mod;

        // we only have txt and no pattern, so we just check if any substr of length len
        // simply occurs already by storing all substr hashes in a set.
        for(int start = 1; start < n - len + 1; ++start) {
            hash = (hash * a - (s.charAt(start - 1)-'a') * global % mod + mod) % mod;
            hash = (hash + (s.charAt(start + len - 1)-'a')) % mod;
            if (set.contains(hash)) return start;
            set.add(hash);
        }
        return -1;
    }
}