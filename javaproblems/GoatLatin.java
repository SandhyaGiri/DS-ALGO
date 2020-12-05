package javaproblems;

import java.util.stream.*;
import java.util.regex.*;
import java.util.*;

// https://leetcode.com/problems/goat-latin/
public class GoatLatin {
    /**
     * 
     * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

        We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

        The rules of Goat Latin are as follows:

        If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
        For example, the word 'apple' becomes 'applema'.
        
        If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
        For example, the word "goat" becomes "oatgma".
        
        Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
        For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
        Return the final sentence representing the conversion from S to Goat Latin. 

        

        Example 1:

        Input: "I speak Goat Latin"
        Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
        Example 2:

        Input: "The quick brown fox jumped over the lazy dog"
        Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
        

        Notes:

        S contains only uppercase, lowercase and spaces. Exactly one space between each word.
        1 <= S.length <= 150.

     * @param S
     * @return
     */
    public String toGoatLatin(String S) {
        final String wordSuffix = "ma";
        Pattern vowelPattern = Pattern.compile("^[aeiouAEIOU].*$");
        if(S!= null && !S.isEmpty()){
            String[] words = S.split(" ");
            List<String> goatLatinWords = new LinkedList<>();
            for(int i=0;i<words.length;i++){
                StringBuilder modifiedWord = new StringBuilder(words[i]);
                if(vowelPattern.matcher(words[i]).matches()){ // starts with a vowel
                    modifiedWord.append(wordSuffix);
                } else { // starts with a consonant
                    char firstChar = modifiedWord.charAt(0);
                    modifiedWord.deleteCharAt(0);
                    modifiedWord.append(firstChar);
                    modifiedWord.append(wordSuffix);
                }
                // add 'a' for 1st word, 'aa' for second word ... at the end
                // stream.generate creates an infinite stream with given supplier
                // so we limit it using "limit" operation
                String aSuffix = Stream.generate(() -> "a").limit(i+1).reduce("", (x,y) -> x+y);
                modifiedWord.append(aSuffix);
                // add to list of words
                goatLatinWords.add(modifiedWord.toString());
            }
            // concatenate the words using " "
            return goatLatinWords.stream().reduce("", (x,y) -> x+ " " + y).substring(1);
        }
        return S;
    }
}