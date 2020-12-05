package datastructures.trees;

import java.util.*;

// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
public class PathSumBinary {

    /**
     * IDEA1: store the binary number as decimal with base 10, *10 + curr val
     * 
     * Then finally convert to binary and add all values (didn't work for some cases??)
     * @param root
     * @param pathSumDecimal
     * @param decimalNumbers
     */
    void dfsUtil(TreeNode root, long pathSumDecimal, List<Long> decimalNumbers){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            decimalNumbers.add(pathSumDecimal*10 + root.val);
            return;
        }
        dfsUtil(root.left, pathSumDecimal*10 + root.val, decimalNumbers);
        dfsUtil(root.right, pathSumDecimal*10 + root.val, decimalNumbers);
    }
    /**
     * IDEA2: without post procssing, store the binary val as is by shifting left and doing
     * a bitwise OR with curr val (logical operators work on binary value of the number!!)
     * @param root
     * @param pathNumber
     * @param numbers
     */
    void dfsUtilBinary(TreeNode root, int pathNumber, List<Integer> numbers){
        if(root == null){
            return;
        }
        pathNumber = (pathNumber << 1) | root.val;
        if(root.left == null && root.right == null){
            numbers.add(pathNumber);
            return;
        }
        dfsUtilBinary(root.left, pathNumber, numbers);
        dfsUtilBinary(root.right, pathNumber, numbers);
    }
    /**
     * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

        For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

        Return the sum of these numbers.

        

        Example 1:



        Input: [1,0,1,0,1,0,1]
        Output: 22
        Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        List<Integer> numbers = new ArrayList<>();
        //dfsUtil(root, 0L, numbers);
        //int result =0;
        //int steps =0;
        /*
        for(long num: numbers){
            // find binary value
            int binValue=0;
            int pow=0;
            //System.out.println(num);
            while(num>0){
                binValue += (num%10) * Math.pow(2,pow);
                num = num/10;
                pow++;
            }
            //System.out.println(binValue);
            result += binValue;
            steps+=1;
            if(steps % 10 ==0){
                System.out.println(result + "," + steps);   
            }
        }*/
        
        dfsUtilBinary(root, 0, numbers);
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}