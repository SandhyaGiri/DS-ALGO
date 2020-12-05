package algorithms.backtracking;

/**
 * https://leetcode.com/problems/jump-game/
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    boolean[] visited;
    boolean dfsUtil(int[] nums, int index){
        if(index == nums.length-1){
            return true; // goal node reached!
        }
        if(index >= nums.length || nums[index] == 0){
            return false;
        }
        if(!visited[index]){
            int maxJump = nums[index];
            for(int i=1;i<=maxJump;i++){
                if(dfsUtil(nums, index+i)){
                    return true;
                }
            }
            // after exhausting all possibilities from an index and not finding any route
            // mark it as visited, so that it is not explored in any other paths!
            visited[index]= true;
        }
        return false;
    }
    public boolean canJump(int[] nums) {
        visited = new boolean[nums.length];
        return dfsUtil(nums, 0);
    }
    
    boolean dfs(int[] arr, boolean[] visited, int start){
        if(start < 0 || start >= arr.length || visited[start]){
            return false;
        }
        visited[start] = true;
        if(arr[start] == 0) {
            return true;
        }
        return dfs(arr, visited, start+arr[start]) || dfs(arr, visited, start-arr[start]);
    }
    /**
     * https://leetcode.com/problems/jump-game-iii/
     * 
     * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

        Notice that you can not jump outside of the array at any time.

        

        Example 1:

        Input: arr = [4,2,3,0,3,1,2], start = 5
        Output: true
        Explanation: 
        All possible ways to reach at index 3 with value 0 are: 
        index 5 -> index 4 -> index 1 -> index 3 
        index 5 -> index 6 -> index 4 -> index 1 -> index 3 
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, visited, start);
    }
}