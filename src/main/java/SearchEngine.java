import impl.AllStrategyImpl;
import impl.AnyStrategyImpl;
import impl.NoneStrategyImpl;
import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class SearchEngine {
    Scanner scanner = new Scanner(System.in);
    ReadManager readManager;

    public SearchEngine(ReadManager readManager) {
        this.readManager = readManager;
    }

    public void run() {
        ArrayList<String> peopleList = readManager.readFile();
        while (true) {
            printMenu();
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> find(peopleList, mapWords(peopleList));
                case "2" -> printList(peopleList);
                case "3" -> {
                    scanner.close();
                    System.out.println("\nBye!");
                    return;
                }
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        }
    }


    private Map<String, ArrayList<Integer>> mapWords(ArrayList<String> lines) {
        Map<String, ArrayList<Integer>> hashMapWords = new HashMap<>();
        lines.stream()
                .map(line -> line.trim().split("\s"))
                .flatMap(Stream::of)
                .map(String::toUpperCase)
                .forEach((word) -> {
                    if (!hashMapWords.containsKey(word)) {
                        hashMapWords.put(word, getLinesWithWord(lines, word));
                    }
                });
        return hashMapWords;
    }

    private ArrayList<Integer> getLinesWithWord(ArrayList<String> list, String word) {
        ArrayList<Integer> linesWithWord = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toUpperCase().contains(word)) {
                linesWithWord.add(i);
            }
        }
        return linesWithWord;
    }

    private void printMenu() {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
    }

    private void printList(ArrayList<String> list) {
        System.out.println("\n=== List of people ===");
        list.forEach(s -> System.out.println(s.trim()));
    }

    private void find(ArrayList<String> list, Map<String, ArrayList<Integer>> lines) {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        System.out.println("\nEnter a name or email to search all suitable people.");
        String query = scanner.nextLine().toUpperCase();
        SearchStrategy matchingStrategy;
        List<String> found;

        switch (strategy) {
            case "ALL" -> {
                matchingStrategy = new AllStrategyImpl();
                found = matchingStrategy.search(list, lines, query);
            }
            case "ANY" -> {
                matchingStrategy = new AnyStrategyImpl();
                found =matchingStrategy.search(list, lines, query);
            }
            case "NONE" -> {
                matchingStrategy = new NoneStrategyImpl();
                found = matchingStrategy.search(list, lines, query);
            }
            default -> found = new ArrayList<>();
        };

        if (found.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.println();
            System.out.println(found.size() + " persons found:");
            found.forEach(s -> System.out.println(s.trim()));
        }
    }
}
