import strategies.StrategyChooser;
import strategies.SearchStrategy;
import util.LineUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The class represents search engine
 */
class SearchEngine {

    private final Scanner scanner;
    private final List<String> peoples;
    private final StrategyChooser strategyChooser;
    private final LineTokenizer lineTokenizer;

    public SearchEngine(FileReader fileReader, Scanner scanner, LineTokenizer lineTokenizer) throws IOException {
        peoples = fileReader.readFile();
        this.scanner = scanner;
        strategyChooser = new StrategyChooser();
        this.lineTokenizer = lineTokenizer;
    }

    /**
     * Display the menu, get the user's choice, process the choice
     */
    public void run() {
        while (true) {
            printMenu();
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> findPeople(lineTokenizer.convertToLinesIndexesByWord(peoples));
                case "2" -> printPeople(peoples);
                case "0" -> {
                    scanner.close();
                    System.out.println("\nBye!");
                    return;
                }
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    /**
     * Outputs the menu to the console
     */
    public void printMenu() {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
    }

    /**
     * Find all people depending on strategy and print to the console
     */
    public void findPeople(Map<String, List<Integer>> linesIndexesByWord) {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        SearchStrategy searchStrategy = strategyChooser.get(strategy);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] queryWords = LineUtils.convertToArray(scanner.nextLine());
        queryValidator(queryWords);

        List<String> foundPeople = searchStrategy.search(peoples, linesIndexesByWord, queryWords);
        if (foundPeople.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.printf("\n%d persons found:%n", foundPeople.size());
            printPeople(foundPeople);
        }
    }

    /**
     * Print all people to the console
     */
    public void printPeople(List<String> peoples) {
        System.out.println("\n=== List of people ===");
        peoples.stream()
                .distinct()
                .map(String::trim)
                .forEach(System.out::println);
    }

    /**
     * Tests the query if you've chosen to search for people using a specific strategy
     */
    private void queryValidator(String[] queryWords) {
        if (queryWords.length == 0) {
            throw new IllegalArgumentException("You pass the wrong query");
        }
    }
}
