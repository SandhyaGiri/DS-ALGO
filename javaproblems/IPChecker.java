package javaproblems;

import java.util.regex.*;

public class IPChecker {
    // https://leetcode.com/problems/validate-ip-address/
    // Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
    public String validIPAddress(String IP) {
        String[] result={"IPv4", "IPv6", "Neither"};
        Boolean isIPv4Valid = null;
        Boolean isIPv6Valid = false;
        Pattern ipv4Pattern = Pattern.compile("^(0|[1-9][0-9]{0,2}).(0|[1-9][0-9]{0,2}).(0|[1-9][0-9]{0,2}).(0|[1-9][0-9]{0,2})$");
        Pattern ipv6Pattern = Pattern.compile("^[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}:[0-9A-Fa-f]{1,4}$");
        Matcher matcher = ipv4Pattern.matcher(IP);
        if(matcher.find()){
            int i=1;
            for(;i<=4;i++){
                int part = Integer.valueOf(matcher.group(i));
                if(part > 255){
                    isIPv4Valid = false;
                    break;
                }
            }
            if(i==5 && isIPv4Valid == null){
                isIPv4Valid = true;
            }
        } else {
            isIPv4Valid = false;
            // try IPv6
            Matcher otherMatcher = ipv6Pattern.matcher(IP);
            isIPv6Valid = otherMatcher.find();
        }
        return isIPv4Valid ? result[0]: isIPv6Valid ? result[1] : result[2];
    }
}