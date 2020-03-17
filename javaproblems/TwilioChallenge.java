package javaproblems;

import java.util.*;

public class TwilioChallenge {

    public static final String EMPTY_MATCH = "";
    /**
    Sorts the strings with longest strings first, and shortest strings last.
    **/
    public static class LengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() == o2.length() ? 0 : (o1.length() < o2.length() ? +1 : -1);
        }
    }
    /*
     * Complete the 'match' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY prefixes
     *  2. STRING_ARRAY numbers
     */
    public static List<String> match(List<String> prefixes, List<String> numbers) {
        List<String> matches = new ArrayList<String>();
        if(prefixes.isEmpty()) {
            // empty prefix list means empty match result
            return matches;
        }
        // sorts prefix list with longest prefix at the beginning 
        Collections.sort(prefixes, new LengthComparator());
        for(String phoneNumber : numbers) {
            boolean matchFound = false;
            for(String prefix : prefixes) {
                if(phoneNumber.startsWith(prefix)) {
                    matchFound = true;
                    matches.add(prefix);
                    break;
                }
            }
            if(!matchFound) {
                matches.add(EMPTY_MATCH);
            }
        }
        return matches;
    }

    /*
     * Complete the 'segments' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING message as parameter.
     */

    public static List<String> segments(String message) {
        List<String> segments = new LinkedList<String>();
        if(message == null || message.length()<=160) {
            segments.add(message);
            return segments;
        }
        int messageLength = message.length();
        int segmentSuffixLength = 5; // "(1/5)" or "(9/9)"
        int segmentLength = 160; // max chars in any segment
        int actualSegmentLength = segmentLength - segmentSuffixLength;
        int numberSegments = messageLength / actualSegmentLength;
        if(messageLength % actualSegmentLength > 0) {
            numberSegments += 1;
        }
        for(int i=0;i<numberSegments;i++) {
            StringBuilder segment = new StringBuilder();
            int segStart = i*actualSegmentLength;
            int segEnd = Math.min(i*actualSegmentLength + actualSegmentLength, messageLength);
            segment.append(message.substring(segStart, segEnd));
            segment.append("("+ (i+1) + "/" + (numberSegments) + ")");
            segments.add(segment.toString());
        }
        return segments;
    }

     /*
     * Complete the 'segments' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING message as parameter.
     * 
     * Diff method for no word break case, split on spaces if the space at end of the segment is valid have it in same seg,
     * otherwise move it to start of next segment.
     */

    public static List<String> segments2(String message) {
        List<String> segments = new LinkedList<String>();
        if(message == null || message.length()<=160) {
            segments.add(message);
            return segments;
        }
        int segmentSuffixLength = 5; // "(1/5)" or "(9/9)"
        int segmentLength = 160; // max chars in any segment
        int actualSegmentLength = segmentLength - segmentSuffixLength;
        String[] words = message.split(" ");
        int wordIndex = 0;
        StringBuilder segment = new StringBuilder();
        boolean addSpaceNewSeg = false;
        while(wordIndex < words.length) {
            int newSegLengthWithSpace = segment.length() + words[wordIndex].length() + 1;
            int newSegLengthWithoutSpace = segment.length() + words[wordIndex].length();
            if(wordIndex != words.length -1 && (newSegLengthWithSpace) <= actualSegmentLength) {
                segment.append(words[wordIndex]);
                segment.append(" ");
            }
            else if(newSegLengthWithoutSpace <= actualSegmentLength) {
                segment.append(words[wordIndex]);
                addSpaceNewSeg = true;
            }
            if(segment.length() == actualSegmentLength || addSpaceNewSeg || wordIndex == words.length -1) {
                segments.add(segment.toString());
                segment = new StringBuilder();
                if(addSpaceNewSeg) {
                    segment = new StringBuilder(" ");
                    addSpaceNewSeg = false;
                }
            }
            wordIndex++;
        }
        int numberSegments = segments.size();
        for(int i=0;i<numberSegments;i++) {
            System.out.println(segments.get(i).length());
            StringBuilder s = new StringBuilder(segments.get(i));
            s.append("("+ (i+1) + "/" + (numberSegments) + ")");
            segments.set(i, s.toString());
        }
        return segments;
    }
}