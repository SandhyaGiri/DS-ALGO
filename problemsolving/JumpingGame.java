package problemsolving;

import java.util.*;

public class JumpingGame {

    public static boolean canWinRecursive(int i, int leap, int[] game, int[] visited) {
        if (i==game.length-1 || i+leap>=game.length) {
            // Winning Criteria
            return true;
        } else if (game[i+1] != 0 && game[i+leap] != 0 && (i!=0 &&game[i-1] != 0)) {
            // No possible moves
            return false;
        }
        // 3 possible moves
        boolean result1=false,result2=false, result3=false;
        visited[i] = 1; // Visited is need to ensure completeness and to avoid stack overflow
        if(visited[i+1] == 0 && game[i+1] == 0) {
            result1 = canWinRecursive(i+1, leap, game, visited);
        }
        if(visited[i+leap] == 0 && game[i+leap] == 0) {
            result2 = canWinRecursive(i+leap, leap, game, visited);
        }
        if(i !=0 && game[i-1] == 0 && visited[i-1] == 0) {
            result3 = canWinRecursive(i-1, leap, game, visited);
        }
        return result1 || result2 || result3;
    }

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        int[] visited = new int[game.length];
        return canWinRecursive(0, leap, game, visited);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}

