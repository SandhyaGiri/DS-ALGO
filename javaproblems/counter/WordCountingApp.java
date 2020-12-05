package javaproblems.counter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

class WordProcessorAsyncTask implements Callable<Map<String, Integer>>{
    private String text;
    private String wordDelimiter;

    WordProcessorAsyncTask(String text, String wordDelimiter){
        this.text = text;
        this.wordDelimiter = wordDelimiter;
    }

    public Map<String, Integer> call(){
        WordProcessor processor = new WordProcessorImpl();
        return processor.countWords(text, wordDelimiter);
    }
}

interface WordProcessor {
    Map<String, Integer> countWords(String text, String wordDelimiter);
    Map<String, Integer> countWords(List<String> lines, String wordDelimiter);
}

class WordProcessorImpl implements WordProcessor {
    public Map<String, Integer> countWords(String text, String wordDelimiter){
        Map<String, Integer> wordCounts = new HashMap<>();
        if(text != null){
            String[] words = text.split(wordDelimiter);
            // words can contain  or have nothing but some special characters - filter?
            for(String word: words){
                wordCounts.put(word, wordCounts.getOrDefault(word, 0)+1);
            }
        }
        return wordCounts;
    }
    public Map<String, Integer> countWords(List<String> lines, String wordDelimiter){
        WordCountAggregator aggregator = new WordCountAggregator();
        if(lines != null){
            for(String line: lines){
                Map<String, Integer> wordLineCounts = countWords(line, wordDelimiter);
                aggregator.addCounts(wordLineCounts);
            }
        }
        return aggregator.getWordCounts();
    }
}

class WordCountAggregator {
    Map<String, Integer> wordCounts;
    WordCountAggregator(){
        wordCounts = new HashMap<>();
    }
    public void addCounts(Map<String, Integer> partialCounts){
        for(Map.Entry<String, Integer> entry: partialCounts.entrySet()){
            wordCounts.put(entry.getKey(), wordCounts.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }
    public Map<String, Integer> getWordCounts(){
        return this.wordCounts;
    }
    public Map<String, Integer> getWordCountsSorted(){
        TreeMap<String, Integer> orderedCountMap = new TreeMap<>();
        orderedCountMap.putAll(this.wordCounts);
        return orderedCountMap;
    }
}

public class WordCountingApp {
    private static final String WORD_DELIMITER = " ";

    private static void sequentialWordCounting(String filename){
        // 1. read from input file
        List<String> lines = new ArrayList<>();
        try{
            Reader reader = new FileReader(filename); // sample: "words.txt"
            BufferedReader input = new BufferedReader(reader);
            while(input.ready()){
                lines.add(input.readLine());
            }
            input.close();
        } catch(FileNotFoundException e){
            System.out.println("Please enter a valid file");
        }
        catch (IOException e){
            System.out.println("Error while reading the input file.");
        }
        // 2. read entire text and give it to WordProcessor
        // assume: space delimted words
        WordProcessor wordProcessor = new WordProcessorImpl();
        Map<String, Integer> counts = wordProcessor.countWords(lines, " ");
        // finally sort the results 
        TreeMap<String, Integer> orderedCountMap = new TreeMap<>();
        orderedCountMap.putAll(counts); // uses natural ordering of the string keys (which are words)
        for(Map.Entry<String, Integer> entry: orderedCountMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void main(String[] args){
        // OPTIMIZATION: For each line, create a FutureTask and submit it to a thread pool.
        // this way we can process all lines parallely and then gather the counts (later on) for faster processing

        // 1. read from input file
        LocalTime start = LocalTime.now();

        // test sequential time
        sequentialWordCounting(args[0]);
        System.out.println("Time taken sequentially: " + Duration.between(start, LocalTime.now()).toMillis());

        // reset time for parallel task
        start = LocalTime.now();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Map<String, Integer>>> futureTasks = new ArrayList<>();
        try{
            Reader reader = new FileReader(args[0]); // sample: "words.txt"
            BufferedReader input = new BufferedReader(reader);
            while(input.ready()){
                String line = input.readLine();
                Future<Map<String, Integer>> futureTask = executorService.submit(new WordProcessorAsyncTask(line, WORD_DELIMITER));
                futureTasks.add(futureTask);
            }
            input.close();
        } catch(FileNotFoundException e){
            System.out.println("Please enter a valid file");
        }
        catch (IOException e){
            System.out.println("Error while reading the input file.");
        }
        // 2. Wait on all future tasks to complete and aggregate the results
        executorService.shutdown(); // to stop submitting any new task to the thread pool.
        WordCountAggregator aggregator = new WordCountAggregator();
        try {
            // instead use completionService to get next completed task
            for(Future<Map<String, Integer>> future : futureTasks){
                Map<String, Integer> partialCounts = future.get();
                aggregator.addCounts(partialCounts);
            }
        } catch(InterruptedException | ExecutionException e){
            System.out.println("Error while fetching counts from a future task.");
        }
        Map<String, Integer> wordCounts = aggregator.getWordCountsSorted();
        for(Map.Entry<String, Integer> entry: wordCounts.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time taken : " + duration.toMillis());
    }
}
