package datastructures.strings;

// https://leetcode.com/problems/long-pressed-name/
public class LongPressedName {

    /**
     * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

        You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

        

        Example 1:

        Input: name = "alex", typed = "aaleex"
        Output: true
        Explanation: 'a' and 'e' in 'alex' were long pressed.
        Example 2:

        Input: name = "saeed", typed = "ssaaedd"
        Output: false
        Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
        Example 3:

        Input: name = "leelee", typed = "lleeelee"
        Output: true
        Example 4:

        Input: name = "laiden", typed = "laiden"
        Output: true
        Explanation: It's not necessary to long press any character.
        

        Constraints:

        1 <= name.length <= 1000
        1 <= typed.length <= 1000
        The characters of name and typed are lowercase letters.

        Idea: sort of like merging two sorted arrays, if both chars match, move both pointers. Otherwise
        check if char in typed matches the previous match, if so skip all such characters. At the end there
        might be some extra chars in typed which matched the previous match, skip them to incr j.
        Finally both strings should have been exhausted for a match.
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int i=0,j=0;
        int m = name.length();
        int n = typed.length();
        Character prevMatch = null;
        Character x = null,y=null; 
        while(i<m && j<n){
            x = name.charAt(i);
            y = typed.charAt(j);
            // if both chars match go to next chars
            if(x == y){
                System.out.println(prevMatch + "->" +x);
                prevMatch = x;
                i++;
                j++;
            }
            // else check if char in typed matches previous matched char, then go to next 
            else if( y == prevMatch){
                while(j<n && (y=typed.charAt(j)) == prevMatch){
                    j++;
                }
                if(j == n && y != x){ // no match found for current x in name
                    return false;
                }
            } else {
                return false;
            }
        }
        // if there are left out chars in typed that match last match
        while(j<n && (y=typed.charAt(j)) == prevMatch){
            j++;
        }
        return i==m && j == n;
    }
}
