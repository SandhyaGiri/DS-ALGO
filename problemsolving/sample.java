package problemsolving;

public class sample {
}

    // IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED


import java.util.ArrayList;
import java.util.*;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{ 
    class Movie implements Comparable<Movie>{
        int id;
        int duration;
        Movie(int id, int duration){
            this.id = id;
            this.duration = duration;
        }
        public int compareTo(Movie other){
            return this.duration - other.duration;
        }
    }
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	PairInt IDsOfmovies(int flightDuration, int numMovies,
	                    ArrayList<Integer> movieDuration)
	{
		// WRITE YOUR CODE HERE
		// sort the movie durations
		Set<Movie> movies = new TreeSet<>();
		for(int i=0;i<numMovies;i++){
		    movies.add(new Movie(i,movieDuration.get(i)));
		}
		List<Movie> movieList = new ArrayList<>();
		for(Movie x: movies){
		    //System.out.println(x.id + "," + x.duration);
		    movieList.add(x);
		}
		int targetTime = flightDuration - 30;
		if(flightDuration > 30) {
		    // initialize two pointers at ends
    		int i=0, j=numMovies-1;
    		boolean pairFound = false;
    		int movie1Dur = -1, movie2Dur=-1;
    		int bestMovie1=-1,bestMovie2=-1;
    		while(i<j){
    		    Movie movie1 = movieList.get(i);
    		    Movie movie2 = movieList.get(j);
    		    if(movie1.duration + movie2.duration == targetTime){
    		        pairFound = true;
    		        //System.out.println("here" + movie1.id + "," + movie2.id);
    		        if(Math.max(movie1.duration, movie2.duration) > Math.max(movie1Dur, movie2Dur)){
    		            bestMovie1 = movie1.id;
    		            bestMovie2 = movie2.id;
    		            movie1Dur = movie1.duration;
    		            movie2Dur = movie2.duration;
    		        }
    		        i++;
    		        j--;
    		    } else if(movie1.duration + movie2.duration > targetTime){
    		        j--;
    		    } else {
    		        i++;
    		    }
    		}
    		return new PairInt(bestMovie1, bestMovie2);
		}
	    return new PairInt(-1,-1);
	}
	// METHOD SIGNATURE ENDS
}