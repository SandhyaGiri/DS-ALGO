package datastructures.strings;

public class CountAndSay {
    // https://leetcode.com/problems/count-and-say/
    /**
     * The count-and-say sequence is the sequence of integers with the first five terms as following:

        1.     1
        2.     11
        3.     21
        4.     1211
        5.     111221
        1 is read off as "one 1" or 11.
        11 is read off as "two 1s" or 21.
        21 is read off as "one 2, then one 1" or 1211.

        Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.

        Note: Each term of the sequence of integers will be represented as a string.
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if(n<=0){
            return "";
        }
        if(n==1){
            return "1";
        }
        String prevString = countAndSay(n-1);
        StringBuilder str = new StringBuilder();
        int count=1;
        Integer digit= null;
        System.out.println("prev:" +prevString);
        for(int i=0;i<prevString.length();i++){
            if(digit == null){
                digit = Character.getNumericValue(prevString.charAt(i));
                continue;
            }
            if(Character.getNumericValue(prevString.charAt(i)) == digit){
                count++;
            } else {
                str.append(String.valueOf(count));
                str.append(String.valueOf(digit));
                digit = Character.getNumericValue(prevString.charAt(i));
                count =1;
            }
        }
        str.append(String.valueOf(count));
        str.append(String.valueOf(digit));
        return str.toString();
    }
}