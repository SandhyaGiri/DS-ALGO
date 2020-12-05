package datastructures.arrays;

public class DefuseBomb {
    /**
     * 
    You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.

    To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

    If k > 0, replace the ith number with the sum of the next k numbers.
    If k < 0, replace the ith number with the sum of the previous k numbers.
    If k == 0, replace the ith number with 0.
    As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

    Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

    

    Example 1:

    Input: code = [5,7,1,4], k = 3
    Output: [12,10,16,13]
    Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
    Example 2:

    Input: code = [1,2,3,4], k = 0
    Output: [0,0,0,0]
    Explanation: When k is zero, the numbers are replaced by 0. 
    Example 3:

    Input: code = [2,4,9,3], k = -2
    Output: [12,5,6,13]
    Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.

    Idea: Append code to itself, and compute sum[i] as sum of numbers up to i.
    Then update array based on k>0 or k<0.  
    */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] sum = new int[2*n];
        // append code at end of code, calculate sum[i] as sum of numbers up to i. this will break cyclic nature
        // and enable to compute sum of previous k numbers or sum of next k numbers easily (O(1))
        for(int i=0;i<n;i++){
            sum[i] = (i-1>=0 ? sum[i-1] : 0) + code[i];
        }
        for(int i=0;i<n;i++){
            sum[i+n] = sum[i+n-1] + code[i];
        }
        // decrypt the code by replacing correctly
        int[] decryptedCode = new int[n];
        if(k>0){
            for(int i=0;i<n;i++){
                decryptedCode[i] = sum[i+k] - sum[i];
            }
        }
        else if(k<0){
            int start = n; // this is so, because we need sum of prev numbers. So start from the duplicate code's sum array.
            for(int i=0;i<n;i++){
                int currIndex = i+start; // in sum array
                decryptedCode[i] = sum[currIndex] - sum[currIndex+k-1] -code[i];
            }
        } else {
            // nothing - default initialized to 0
        }
        return decryptedCode;
    }
}
