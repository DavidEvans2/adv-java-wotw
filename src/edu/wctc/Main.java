package edu.wctc;

import java.util.*;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<>();


    public Main() {
        String line;
        String[] words;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]", "")
                    .toLowerCase().trim();

            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }

            // Split string over one or more spaces
            words = line.split(" +");

            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                } else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            }
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){

            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        String choice; // choose menu option
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("Choose a number: ");
            System.out.println("1.) Show the 10 most frequent words");
            System.out.println("2.) Show the words that are only used once");
            System.out.println("Press any key to exit.");
            choice = keyboard.nextLine();
            System.out.println();

            if (choice.equals("1")) {
                int wordCount = 0;
                System.out.println("Top 10 Most Frequent Words:");
                System.out.println();
                for (Map.Entry<String, Integer> entry : list) {
                    wordCount++;
                    sortedMap.put(entry.getKey(), entry.getValue());
                    System.out.println(wordCount + ".) " + entry.getKey() + "was used " + entry.getValue() + " times.");
                    if (wordCount >= 10)
                        break;
                }
                System.out.println();
            }

            if (choice.equals("2")) {
                System.out.println("Words That Are Only Used Once:");
                System.out.println();
                for (Map.Entry<String, Integer> entry : list) {
                    if (entry.getValue() == 1) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }
                System.out.println();
            }
        } while (choice.equals("1") || choice.equals("2"));

    }

    public static void main(String[] args) {

        new Main();
    }

}