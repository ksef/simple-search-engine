import strategies.StrategyChooser;
import strategies.SearchStrategy;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static util.LineUtils.convertToArray;

class SearchEngine {

    private final Scanner scanner;
    private final LineTokenizer lineTokenizer;
    private final List<String> peopleList;
    private final StrategyChooser strategyChooser;

    public SearchEngine(FileReader fileReader) throws IOException {
        peopleList = fileReader.readFile();
        scanner = new Scanner(System.in);
        lineTokenizer = new LineTokenizer();
        strategyChooser = new StrategyChooser();
    }

    public void run() {
        while (true) {
            printMenu();
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> find(peopleList, lineTokenizer.mapWords(peopleList));
                case "2" -> printPeople(peopleList);
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

    private void find(List<String> list, Map<String, List<Integer>> lines) {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        SearchStrategy searchStrategy = strategyChooser.get(strategy);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] query = convertToArray(scanner.nextLine());
        List<String> found = searchStrategy.search(list, lines, query);

        if (found.isEmpty()) {
            System.out.println("No matching person found.");
        } else {
            System.out.println();
            System.out.println(found.size() + " persons found:");
            printPeople(found);
        }
    }

    private void printPeople(List<String> people) {
        System.out.println("\n=== List of people ===");
        people.forEach(s -> System.out.println(s.trim()));
    }
}
