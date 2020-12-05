package problemsolving;

import java.util.regex.*;

public class atoi {

    public int myAtoi(String str) {
        // initial space followed by numbers then any characters
        // extract sign and number separately
        Pattern numberExtractor = Pattern.compile("^[\\s]*([+-]?)([0-9]+).*$");
        if(str != null){
            Matcher numberMatch = numberExtractor.matcher(str);
            if(numberMatch.matches()){
                int value = 0;
                String sign = numberMatch.group(1);
                String number = numberMatch.group(2);
                System.out.println(sign +" " + number);
                for(int i=0;i<number.length();i++){
                    int digit = number.charAt(i) - '0';
                    // can overflow or underflow
                    // value = value * 10 + digit; 
                    // instead of checking value * 10 > MAX, we do value > MAX/10 (so no overflow can occur)
                    if(value > Integer.MAX_VALUE/10 || (value == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE%10)){
                        value = "-".equals(sign) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                        return value;
                    }
                    value = value * 10 + digit; 
                }
                return "-".equals(sign) ? -1* value : value;
            }
        }
        return 0;
    }
}