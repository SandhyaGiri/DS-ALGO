package datastructures.arrays;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ArrayAdditional {
    public static int solution(int[] x, int[] y) {
        // Your code here
        Map<Integer, Integer> prisonerIdCount = new HashMap<>();
        for(int i=0;i<x.length;i++){
            if(prisonerIdCount.containsKey(x[i])){
                prisonerIdCount.put(x[i], prisonerIdCount.get(x[i])+1);
            } else{
                prisonerIdCount.put(x[i], 1);
            }
        }
        Integer additionalId = null;
        for(int i=0;i<y.length;i++){
            if(prisonerIdCount.containsKey(y[i])){
                if(prisonerIdCount.get(y[i]) > 1){
                    prisonerIdCount.put(y[i], prisonerIdCount.get(y[i])-1);
                } else {
                    prisonerIdCount.remove(y[i]);
                }
            } else{
                // new prisoner Id not observed in x array
                additionalId = y[i];
                break;
            }
        }
        if(additionalId == null){
            // take the one left prisoner Id
            List<Integer> prisonersLeft = new ArrayList(prisonerIdCount.keySet());
            additionalId = prisonersLeft.get(0);
        }
        return additionalId;
    }
    public static void main(String[] args){
        int x[] = {14, 27, 1, 4, 2, 50, 3, 1};
        int y[] = {2, 4, -4, 3, 1, 1, 14, 27, 50};
        System.out.println(solution(x, y));
    }
}
