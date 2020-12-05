package datastructures.graphs;

import java.util.*;
import java.util.stream.*;

class PairString{
    String first;
    String second;
    PairString(String i, String j){
        this.first = i;
        this.second = j;
    }
}
public class ItemAssociations {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	// RETURN "null" IF NO ITEM ASSOCIATION IS GIVEN
	String findParent(String item, Map<String, String> parents){
	    if(parents.get(item) == null){
	        return null;
	    }
	    if(parents.get(item) == item){
	        return item;
	    }
	    return findParent(parents.get(item), parents);
	}
	List<String> largestItemAssociation(List<PairString> itemAssociation)
	{
		// WRITE YOUR CODE HERE
		Map<String, String> parents = new HashMap<>();
		for(PairString pair: itemAssociation){
		    String parent1 = findParent(pair.first, parents);
		    String parent2 = findParent(pair.second, parents);
		    if(parent1 == null && parent2 == null){
		        parents.put(pair.first, pair.first);
		        parents.put(pair.second, pair.first);
		    } else if(parent1 != null && parent2 == null){
		        parents.put(pair.second,parent1);
		    } else if(parent1 == null && parent2 != null){
		        parents.put(pair.first, parent2);
		    } else {
		        // both have different parents - merge groups
    		    parents.put(pair.first, parent1);
    		    parents.put(pair.second, parent1);   
		    }
		}
		// compute group sizes
		List<String> resultItems = new ArrayList<>();
		Map<String, Set<String>> leaderMap = new HashMap<>();
		for(Map.Entry<String,String> pair: parents.entrySet()){
		    //System.out.println(pair.getKey() + "," + pair.getValue());
		    String parent = findParent(pair.getKey(), parents);
		    //System.out.println(parent);
		    if(parent != null){
		        Set<String> subordinates = leaderMap.get(parent);
		        if(subordinates == null){
		            subordinates = new TreeSet<>();
		            subordinates.add(pair.getKey());
		            leaderMap.put(parent, subordinates);
		        }else{
		            subordinates.add(pair.getKey());
		        }
		    }
		}
		String largestGroupLeader = null;
		int largestGroupSize = 0;
		for(String leader: leaderMap.keySet()){
		    //System.out.println("here");
		    if(leaderMap.get(leader).size() > largestGroupSize){
		        largestGroupSize = leaderMap.get(leader).size();
		        largestGroupLeader = leader;
		    }
		}
		return leaderMap.get(largestGroupLeader).stream().collect(Collectors.toList());
	}
	// METHOD SIGNATURE ENDS
}