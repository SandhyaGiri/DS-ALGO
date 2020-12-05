package datastructures.graphs;

import java.util.*;

//fdagehjo
// Person - wishlist - list[product], friends - list[Person]
// ret: most commonly wished for Product

/*

{
    name: “Bob”,
	wishlist: [“tiger”, “car”, “dog”],
	friends: [
		{
			name: “John”,
			wishlist: [“tiger”, “ice cream”],
			friends: [
			    {
					name: “Jane”,
					wishlist: [“popcorn”, “horse”],
					friends: []
				},
				{
					name: “Ben”,
					wishlist: [“vegetables”, “dog”],
					friends: []
				}
			]
		}
	]
}

*/


class Product {
    int id;
    String name;
    Product(String productName){
        this.name = productName;
    }
}
class Person{
    int id;
    List<Person> friends;
    List<Product> wishlist;
    Person(){
        friends = new ArrayList<>();
        wishlist = new ArrayList<>();
    }
    // add to wishlist
    // add a friend
}
class PopularProduct {
    
    Map<Integer, Integer> getCounts(List<Product> wishedProducts){
        Map<Integer, Integer> countMap = new HashMap<>();
        for(Product product: wishedProducts){
            countMap.put(product.getId(), countMap.getOrDefault(product.getId(), 0) +1);
        }
        return countMap;
    }
    void accumulateCounts(Map<Integer, Integer> masterMap, Map<Integer, Integer> childMap){
        if(childMap != null){
            for(Map.Entry<Integer, Integer> countEntry : childMap.entrySet()){
                masterMap.put(countEntry.getKey(), masterMap.get(countEntry.getKey(), 0) + countEntry.getValue());
            }
        }
    }
    List<Integer> getTop(Person start){
        // BFS traversal
        Queue<Person> users = new LinkedList<>();
        Set<Integer> visitedUsers = new HashSet<>();
        // overall count map
        Map<Integer, Integer> candidatePopularProducts = new HashMap<>();
        // add the starting person to the queue
        users.add(start);
        while(!users.isEmpty()){
            Person user = users.poll();
            visitedUsers.add(user.getId());
            Map<Integer, Integer> localCountMap = getCounts(user.getWishlist());
            accumulateCounts(candidatePopularProducts, localCountMap);
            // check friends
            for(Person friend : user.getFriends()){
                if(!visitedUsers.contains(friend.getId()){
                    users.add(friend);
                }
            }
        }
        // find the most popular gift choice
        int maxCount = Integer.MIN_VALUE;
        List<Integer> mostPopularProductIds = new ArrayList<>();
        for(Map.Entry<Integer, Integer> countEntry : candidatePopularProducts.entrySet()){
            int productId = countEntry.getKey();
            int count = countEntry.getValue();
            if(count > maxCount){
                maxCount = count;
                mostPopularProductId.clear();
                mostPopularProductId.add(productId);
            } else if(count == maxCount){
                mostPopularProductId.add(productId);
            }
        }
        return mostPopularProductIds;
    }
    
}

public class MostWishedProducts {
    
}
