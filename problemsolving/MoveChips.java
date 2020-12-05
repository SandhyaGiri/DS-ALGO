package problemsolving;

public class MoveChips {
    
    /**
     * 
     * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position
     * 
     * We have n chips, where the position of the ith chip is position[i].

        We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:

        position[i] + 2 or position[i] - 2 with cost = 0.
        position[i] + 1 or position[i] - 1 with cost = 1.
        Return the minimum cost needed to move all the chips to the same position.

        Idea: In fact, we can move all chips at even positions to position 0, and move all chips at the odd positions to position 1.

        Then, we only have many chips at position 0 and other chips at position 1. Next, we only need to move those two piles together.

        Given two piles of chips located at 0 and 1 respectively, intuitively it would be less effort-taking (i.e. less cost) to move the smaller pile to the larger one, which makes the total cost.
     * @param position
     * @return
     */
    public int minCostToMoveChips(int[] position) {
        int chipsAtPos0 = 0;
        int chipsAtPos1 = 0;
        for(int i=0;i<position.length;i++){
            if(position[i] % 2 == 0){ // move all even pos chips to pos 0
                chipsAtPos0 += 1;
            } else { // move all odd pos chips to pos1
                chipsAtPos1 += 1;
            }
        }
        return Math.min(chipsAtPos0,chipsAtPos1); // move smaller pile of chips to other pos
    }
}
