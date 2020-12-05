package datastructures.strings;

import java.util.regex.*;

public class Palindrome {
    public boolean isPalindrome(String s) {
        int start=0, end=s.length()-1;
        boolean result = true;
        Pattern alphanumeric = Pattern.compile("[a-zA-Z0-9]");
        while(start < end){
            char c1 = s.charAt(start);
            char c2 = s.charAt(end);
            //System.out.println(c1 +"," +c2);
            Matcher char1Matcher = alphanumeric.matcher(c1+"");
            Matcher char2Matcher = alphanumeric.matcher(c2+"");
            if(!char1Matcher.matches()){
                start++;
                continue;
            }
            if(!char2Matcher.matches()){
                end--;
                continue;
            }
            if(Character.toLowerCase(c1) != Character.toLowerCase(c2)){
                result=false;
                break;
            }
            //System.out.println("here");
            start++;
            end--;
        }
        return result;
    }
}