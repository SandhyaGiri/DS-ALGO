package javaproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* Tavern class which serves only beer and has customizable number of taps. Also provides the
* functionality to identify ideal tap numbers which serve the best beer.
*/
class BeerTavern {
    
    private int numTaps;
    
    BeerTavern(int numBeerTaps){
        this.numTaps = numBeerTaps;
    }
    
    /**
    * Calculates the ideal tap numbers out of the possible tap numbers. Ideal tap numbers
    * are the ones for which the given criteria is satisfied.
    *
    * @param conditionsToSatisfy - a {@code Predicate} which specifies a chained list of conditions
    *                              known to the caller, 
    *                              which must hold true for a tap number be considered
    *                              as an ideal tap to get the best beer.
    * @return a {@code List<Integer>} containing all ideal tap numbers.
    */
    public List<Integer> getIdealTapNumbers(final Predicate<Integer> conditionsToSatisfy){
        List<Integer> idealTaps = null;
        // generate a stream of tap numbers from 1.. numTaps (inclusive)
        Stream<Integer> availableTaps = Stream.iterate(1, (i) -> i+1).limit(this.numTaps);
        // filter the tap numbers based on the condition provided.
        idealTaps = availableTaps.filter(conditionsToSatisfy).collect(Collectors.toList());
        return idealTaps;
    }
}

/**
* Customer class which possesses the knowledge of the criteria needed
* for a tapNumber to satisfy in order to serve best beer. Since a customer can
* visit many {@code BeerTavern}s , the knowledge is stored specific to
* a beer tavern.
*
* <p>
* While placing an order at a tavern, the customer class uses the knowledge
* gained so far (may be from some other customer/stranger), to find out the
* best tap numbers at this tavern.
*/
class Customer{
    
    Map<BeerTavern, List<Predicate<Integer>>> criteriaForBestDrink;
    
    Customer(){
        criteriaForBestDrink = new HashMap<>();
    }
    
    /**
    * Adds the given criterion for best beer to the provided tavern.
    *
    * @param tavern - a {@code BeerTavern} for which the customer has obtained a new best beer criterion.
    * @param criterion - a {@code Predicate} which returns true if the tapNumber serves best beer.
    */
    public void addCriterionForBestDrink(final BeerTavern tavern, final Predicate<Integer> criterion){
        List<Predicate<Integer>> existingCriteria = criteriaForBestDrink.getOrDefault(tavern, new ArrayList<>());
        existingCriteria.add(criterion);
        criteriaForBestDrink.put(tavern, existingCriteria);
    }
    
    /**
    * Combines the accumulated criteria for a tavern into a single criterion and queries
    * the tavern to get best tap numbers. Customer can order beer from any of these taps.
    * The results are written to the output stream.
    *
    * @param tavern - a {@code BeerTavern} at which the customer is currently present, awaiting to order beer.
    */
    public void placeOrder(final BeerTavern tavern){
        // get the criteria gathered so far for the given tavern
        List<Predicate<Integer>> criteriaForBestBeer = criteriaForBestDrink.get(tavern);
        // chain all these to mandate each criterion be satisfied for determining ideal tap numbers
        // Improvement: we can get the accumulator function as an argument.
        Optional<Predicate<Integer>> finalCriteria = criteriaForBestBeer.stream().reduce((a,b) -> a.and(b));
        if(finalCriteria.isPresent()){
            // place an order only if you get to know the ideal tap numbers.
            List<Integer> bestTapNumbers = tavern.getIdealTapNumbers(finalCriteria.get());
            // Improvement: can also be done as a single line stream statement
            for(int tapNumber : bestTapNumbers){
                System.out.println(tapNumber);
            }  
        }
    }
}

/**
* A utility interface which provides functions related
* to the divisors of a given number. Some of the functions
* include - computing proper divisors, all divisors etc.
*/
interface DivisorUtil {
    /**
    * Calculates and returns only proper divisors of a number. Proper
    * divisors are numbers from 1 till (number-1) which divide the number
    * completely.
    *
    * @param number - input number for which proper divisors are calculated.
    * @return a {@code List<Integer>} of proper divisors.
    */
    static List<Integer> calculateProperDivisors(final int number){
        List<Integer> divisors = new ArrayList<>();
        for(int i=1;i<=(number/2);i++){
            if(number % i == 0){
                divisors.add(i);
            }
        }
        return divisors;
    }
    // static List<Integer> calculateAllDivisors(int number){}
}

/**
* A {@code Predicate} that checks if the sum of all proper divisiors
* of a given target number is greater than the target. 
*/
class DivisorSumGreaterThanTargetCheck implements Predicate<Integer>{
    
    @Override
    public boolean test(Integer target){
        List<Integer> properDivisors = DivisorUtil.calculateProperDivisors(target);
        // check if sum of all these proper divisors is greater than the target
        int sum = properDivisors.stream().mapToInt(Integer::intValue).sum();
        return sum > target;
    }
}

/**
* A {@code Predictate} that checks if no subset of the proper divisors of a given
* target number, sums up to the target.
*/
class NoDivisorSubsetSumEqualToTargetCheck implements Predicate<Integer>{
    
    /**
    * Checks if any subset of the given list of numbers, sum up to the target value.
    *
    * @param numbers - a {@code List} of integers, whose subsets are checked.
    * @param target - the target sum of a subset.
    * @param index - the index of the current number in the numbers list.
    * @param pathSum - the sum of the chosen numbers in the current recursive path.
    * @return true if such a subset exists, false otherwise.
    */
    private boolean isSubsetSumEquals(List<Integer> numbers, int target, int index, int pathSum){
        if(index >= numbers.size()){
            return pathSum == target;
        }
        if(pathSum > target){
            return false; // any further value will be greater than target
        }
        if(pathSum == target){
            return true; // short circuit and return an early result
        }
        // 2 choices: either include the number at the current index or don't
        int currNumber = numbers.get(index);
        return isSubsetSumEquals(numbers, target, index+1, pathSum) ||
        isSubsetSumEquals(numbers, target, index+1, pathSum+currNumber);
    }
    
    @Override
    public boolean test(Integer target){
        List<Integer> properDivisors = DivisorUtil.calculateProperDivisors(target);
        // check if any subset has a sum equal to target, then return false
        return !isSubsetSumEquals(properDivisors, target, 0, 0);
    }
}

public class TavernPersonio {
    public static void main(String args[] ) throws Exception {
        // 1. create a beer tavern with 100 taps.
        BeerTavern tavern = new BeerTavern(100);
        // 2. enrich a customer with the knowledge regarding best beer taps.
        Customer customer = new Customer();
        customer.addCriterionForBestDrink(tavern, new DivisorSumGreaterThanTargetCheck());
        customer.addCriterionForBestDrink(tavern, new NoDivisorSubsetSumEqualToTargetCheck());
        // 3. let the customer place an order for beer at our tavern.
        customer.placeOrder(tavern);
    }
}

/**
// Improvements 1: we can use Drink enum to create specific taverns which serve only drinks of particular type.
enum Drink {
    BEER, WINE
}

interface Tavern {
    Drink getServedDrink();
}
// Improvements 2: we can also create Taverns which serve multiple drink types.
// Then we can configure only beer to have number of taps, and wine to have number of bottles etc.
// When customer placesOrder to a tavern, he can also specify the Drink in addition to the criteria.
interface Tavern {
    List<Drink> getServedDrinks();
}
class Customer {
    Map<Pair<Tavern,Drink>, List<Predicate<Integer>>> criteriaForBestDrink;
    ...
    public void addCriterionForBestDrink(Tavern tavern, Drink drink, Predicate<Integer> criterion)...
    public void placeOrder(Tavern tavern, Drink drink)...
}
*/