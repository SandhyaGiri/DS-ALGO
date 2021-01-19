package datastructures.strings;

import java.util.*;

public class GroupAnagrams {

    static String sortChars(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    /**
     * https://leetcode.com/problems/group-anagrams
     * 
     * Given an array of strings, group anagrams together.

    Example:

    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
    [
    ["ate","eat","tea"],
    ["nat","tan"],
    ["bat"]
    ]
    Note:

    All inputs will be in lowercase.
    The order of your output does not matter.
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> out = new HashMap<String,List<String>>();
        for(String s: strs) {
            String sorted = sortChars(s);
            if(out.get(sorted) == null) {
                List<String> ls = new ArrayList<String>();
                ls.add(s);
                out.put(sorted,ls);
            } else {
                out.get(sorted).add(s);
            }
        }
        for(String key: out.keySet()){
            result.add(out.get(key));
        }
        return result;
    }

    static boolean isAnagram(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        int[] chars = new int[26]; // if given strings contain only lowercase letters
        for(int i=0;i<s1.length();i++){
            chars[s1.charAt(i) -'a'] += 1;
            chars[s2.charAt(i) -'a'] -= 1;
        }
        boolean match = true;
        for(int i=0;i<26;i++){
            if(chars[i] != 0){
                match=false;
                break;
            }
        }
        return match;
    }
    
    static class AnagramComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            return sortChars(s1).compareTo(sortChars(s2));
        }
    }
    public static void sortAnagrams(String[] strings){
        Arrays.sort(strings, new AnagramComparator());
        System.out.println(Arrays.toString(strings));
    }

    public static void main(String args[]){
        String[] strings = {"cat", "dog", "tac", "god", "act"};
        sortAnagrams(strings);
    }
}