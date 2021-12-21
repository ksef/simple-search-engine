import strategies.SearchStrategy;
import strategies.StrategyALL;
import strategies.StrategyANY;
import strategies.StrategyNONE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchEngine {

    Map<String, ArrayList<Integer>> map = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public void run(File file) {
        ArrayList<String> peopleList = readFile(file);
        Map<String, ArrayList<Integer>> words = mapWords(peopleList);
        while (true) {
            int selection = printMenu();
            switch (selection) {
                case 1 -> find(peopleList, words);
                case 2 -> printList(peopleList);
                case 0 -> {
                    closeScanner();
                    System.out.println("\nBye!");
                    return;
                }
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    public ArrayList<String> readFile(File file) {
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                list.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return list;
    }

    public Map<String, ArrayList<Integer>> mapWords(ArrayList<String> lines) {
        int i = 0;
        for (String s : lines) {
            String[] words = s.split(" +");
            for (String word : words) {
                word = word.toUpperCase();
                if (map.containsKey(word)) {
                    map.get(word).add(i);
                    map.put(word, map.get(word));
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(i);
                    map.put(word, arrayList);
                }
            }
            i++;
        }
        return map;
    }

    public int printMenu() {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
        int selection = scanner.nextInt();
        scanner.nextLine();
        return selection;
    }

    public void printList(ArrayList<String> list) {
        System.out.println("\n=== List of people ===");
        for (String person : list) {
            System.out.println(person);
        }
    }

    public void find(ArrayList<String> list, Map<String, ArrayList<Integer>> map) {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        System.out.println("\nEnter a name or email to search all suitable people.");
        String query = scanner.nextLine();
        SearchStrategy matchingStrategy;
        switch (strategy) {
            case "ALL" -> {
                matchingStrategy = new StrategyALL();
                matchingStrategy.search(list, map, query);
            }
            case "ANY" -> {
                matchingStrategy = new StrategyANY();
                matchingStrategy.search(list, map, query);
            }
            case "NONE" -> {
                matchingStrategy = new StrategyNONE();
                matchingStrategy.search(list, map, query);
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
