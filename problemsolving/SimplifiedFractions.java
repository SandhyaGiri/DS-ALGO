package problemsolving;

import java.util.*;

public class SimplifiedFractions {
    // Simplified Fractions
    /**
     * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is less-than-or-equal-to n. The fractions can be in any order.

        Example 1:

        Input: n = 2
        Output: ["1/2"]
        Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
        Example 2:

        Input: n = 3
        Output: ["1/2","1/3","2/3"]
        Example 3:

        Input: n = 4
        Output: ["1/2","1/3","1/4","2/3","3/4"]
        Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
        Example 4:

        Input: n = 1
        Output: []
     * @param num1
     * @param num2
     * @return
     */
    int getGCD(int num1, int num2){
        if(num2 == 0){
            return num1;
        }
        return getGCD(num2, num1%num2);
    }
    public List<String> simplifiedFractions(int n) {
        List<String> fractions = new ArrayList<>();
        for(int num=1;num<n;num++){
            for(int den=2;den<=n;den++){
                if(num/den < 1){ 
                    int gcd = getGCD(num, den);
                    // check for reducible fraction
                    // second condition is when gcd != 1, could be reducible
                    if(gcd == 1 || !fractions.contains((num/gcd) + "/" + (den/gcd))){
                        fractions.add(num + "/" + den);
                    }
                }
            }
        }
        return fractions;
    }
}