package javaproblems;
import java.util.*;

public class StringProblems {
    // Pattern to check str beging with alphabet, and has only aplhanumeric chars and _, with length between 8-30.
    String regexCheck = "^[A-Za-z][A-Za-z0-9_]{7,29}";

    /**
     * Two strings,  and , are called anagrams if they contain all the same characters in the same frequencies. For example, the anagrams of CAT are CAT, ACT, TAC, TCA, ATC, and CTA.
        If  and  are case-insensitive anagrams, print "Anagrams"; otherwise, print "Not Anagrams" instead.
     * @param a
     * @param b
     * @return
     */
    static boolean isAnagram(String a, String b) {
        char[] charsA = a.toLowerCase().toCharArray();
        char[] charsB = b.toLowerCase().toCharArray();
        int[] charCounts = new int[26];
        for(int i=0;i<charsA.length;i++) {
            charCounts[charsA[i]-'a']++;
        }
        boolean isAnagram = true;
        for(int i=0;i<charsB.length;i++) {
            int currCount = charCounts[charsB[i]-'a'];
            if(currCount == 0){
                isAnagram = false;
                break;
            } else {
                charCounts[charsB[i]-'a']--;
            }
        }
        if(isAnagram){
            for(int i=0;i<charCounts.length;i++) {
                if(charCounts[i] != 0){
                    isAnagram = false;
                    break;
                }
            }
        }
        return isAnagram;
    }

    static void tokens(){
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        String[] tokens = s.split("[!?.,_ '@]+");
        System.out.println(tokens.length);
        for(int i=0;i<tokens.length;i++){
            System.out.println(tokens[i]);
        }
        scan.close();
    }

    /**
     * Finding smallest and largest lexicographical substrings in a string.
     * 
     * compareTo itself does lexicographical comparison, returns positive if string a appears later than str b in lexicographical ordering.
     * Lex ordering: A < B ... < Y < Z < a < b < ... < z
     * @param s
     * @param k
     * @return
     */
    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        
        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        for(int i=0;i<=s.length()-k;i++) {
            if(i==0 && i+k <=s.length()) {
                smallest = largest = s.substring(i, i+k);
                continue;
            }
            if(i+k <= s.length()){
                if(s.substring(i, i+k).compareTo(smallest) < 0) {
                    // substr appears before smallest
                    smallest = s.substring(i, i+k);
                }
                if(s.substring(i, i+k).compareTo(largest) > 0) {
                    // substr appears after largest
                    largest = s.substring(i, i+k);
                }
            }
        }
        return smallest + "\n" + largest;
    }

    /**
     * Gets 2 integers from command line, and prints their integer division. Exception handling.
     * 
     * @param args
     */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        try {
            int x = Integer.parseInt(scan.nextLine());
            int y = Integer.parseInt(scan.nextLine());
            int z = x/y;
            System.out.println(z);
        } 
        catch(NumberFormatException e) { // for parsing errors
            System.out.println("java.util.InputMismatchException");
        }
        catch (ArithmeticException e){ // for division by zero.
            System.out.println("java.lang.ArithmeticException: " + e.getMessage());
        }
        scan.close();
    }
}