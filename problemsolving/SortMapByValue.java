package problemsolving;

import java.util.*;
import java.util.stream.*;

public class SortMapByValue {
    // https://leetcode.com/problems/sort-characters-by-frequency/
    /**
     * Given a string, sort it in decreasing order based on the frequency of characters.

        Example 1:

        Input:
        "tree"

        Output:
        "eert"

        Explanation:
        'e' appears twice while 'r' and 't' both appear once.
        So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            System.out.println(c + ", " + i);
            if(charCounts.get(c) != null){
                charCounts.put(c, charCounts.get(c)+1);
            } else {
                charCounts.put(c, 1);
            }
        }
        Set<Map.Entry<Character, Integer>> entries = charCounts.entrySet();
        System.out.println(entries.size());
        Map<Character, Integer> sortedMap = entries.stream().sorted(Collections.reverseOrder(Map.Entry
            .comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
            // in case of duplicate keys, retain the new one e2 - merge function
                LinkedHashMap::new));
        StringBuilder str = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : sortedMap.entrySet()){
            int count = entry.getValue();
            char c = entry.getKey();
            // System.out.println(c + ", "+ count);
            while(count >0){
                str.append(c + "");
                count--;
            }
        }
        return str.toString();
    }
}