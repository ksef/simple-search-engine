import strategies.impl.AllStrategyImpl;
import strategies.impl.AnyStrategyImpl;
import strategies.impl.NoneStrategyImpl;
import strategies.SearchStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class SearchEngine {

    private final FileReader fileReader;
    private final Scanner scanner;
    private final LineTokenizer lineTokenizer;


    public SearchEngine(FileReader fileReader) {
        this.fileReader = fileReader;
        scanner = new Scanner(System.in);
        lineTokenizer = new LineTokenizer();
    }

    public void run() throws IOException {
        List<String> peopleList = fileReader.readFile();
        while (true) {
            printMenu();
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> find(peopleList, lineTokenizer.mapWords(peopleList));
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

    private void printMenu() {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
    }

    private void printList(List<String> list) {
        System.out.println("\n=== List of people ===");
        list.stream().map(String::new).forEach(s -> System.out.println(s.trim()));
    }

    private void find(List<String> list, Map<String, ArrayList<Integer>> lines) {
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
                found = matchingStrategy.search(list, lines, query);
            }
            case "NONE" -> {
                matchingStrategy = new NoneStrategyImpl();
                found = matchingStrategy.search(list, lines, query);
            }
            default -> found = new ArrayList<>();
        }

        if (found.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.println();
            System.out.println(found.size() + " persons found:");
            found.forEach(s -> System.out.println(s.trim()));
        }
    }
}
