package datastructures.strings;

// https://leetcode.com/problems/implement-strstr/
public class PatternMatching {
    // Time: O(n * (m-1))
    int naiveMatch(String haystack, String needle){
        int m = haystack.length();
        int n = needle.length();
        if(n == 0){
            return 0;
        }
        for(int i=0;i<m;i++){
            char c = haystack.charAt(i);
            if(c == needle.charAt(0)){ // first char matches
                int k=i+1,j=1;
                while(k-i<n && k<m && j<n && haystack.charAt(k) == needle.charAt(j)){
                    k++;
                    j++;
                }
                if(k-i == n){//match found
                    return i;
                }
            }
        }
        return -1;
    }
    // https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
    int[] computeLPS(String needle, int n){
        // Compute lps for the pattern
        int lps[] = new int[n];
        lps[0] = 0;
        // length of the previous longest prefix suffix 
        int len = 0;
        int i=1;
        while(i< n){
            if(needle.charAt(i) == needle.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            } else { // mismatch
                if(len != 0){ // reset to previous len
                    len = lps[len-1];
                } else{
                    lps[i]= len;
                    i++;
                }
            }
        }
        return lps;
    }
    /**
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

        Example 1:

        Input: haystack = "hello", needle = "ll"
        Output: 2
        Example 2:

        Input: haystack = "aaaaa", needle = "bba"
        Output: -1
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if(n == 0){
            return 0;
        }
        int[] lps = computeLPS(needle, n);
        int i=0,j=0; // i pointer on text, j on pattern
        while(i<m){
            if(needle.charAt(j) == haystack.charAt(i)){
                i++;
                j++;
            } else {
                if(j != 0){
                    j = lps[j-1]; // don't match pattern 0..j-1 chars again   
                } else { // never moved on the pattern, just move on one pos in text
                    i = i+1;
                }
            }
            if(j == n){ // pattern found
                return i-j;
                //j = lps[j - 1];  // to find all occurences of the pattern, reset j
            }
        }
        return -1;
    }
}