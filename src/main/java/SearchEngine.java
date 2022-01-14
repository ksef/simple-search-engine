import strategies.StrategyChooser;
import strategies.SearchStrategy;
import util.LineUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class SearchEngine {

    private final Scanner scanner;
    private final List<String> peoples;
    private final StrategyChooser strategyChooser;
    private final LineTokenizer lineTokenizer;

    public SearchEngine(FileReader fileReader) throws IOException {
        peoples = fileReader.readFile();
        scanner = new Scanner(System.in);
        strategyChooser = new StrategyChooser();
        lineTokenizer = new LineTokenizer();
    }

    public void run() {
        while (true) {
            printMenu();
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
                case "2" -> printPeoples(peoples);
                case "0" -> {
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

    private void findPeoples(Map<String, List<Integer>> linesIndexesByWord) {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        SearchStrategy searchStrategy = strategyChooser.get(strategy);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] queryWords = LineUtils.convertToArray(scanner.nextLine());
        queryValidator(queryWords);

        List<String> foundPeoples = searchStrategy.search(peoples, linesIndexesByWord, queryWords);
        if (foundPeoples.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.println(String.format("\n%d persons found:", foundPeoples.size()));
            printPeoples(foundPeoples);
        }
    }

    private void printPeoples(List<String> peoples) {
        System.out.println("\n=== List of people ===");
        peoples.stream()
                .distinct()
                .map(String::trim)
                .forEach(System.out::println);
    }

    private void queryValidator(String[] queryWords) {
        if (queryWords[0].equals("")) {
            throw new IllegalArgumentException("You pass the wrong query");
        }
    }
}
