import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SearchEngineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private List<String> peoples;
    private Scanner scanner;
    private FileReader fileReader;
    private SearchEngine searchEngine;
    private LineTokenizer lineTokenizer;

    @BeforeEach
    public void setUpStreams() throws IOException {
        lineTokenizer = new LineTokenizer();
        fileReader = new FileReader("src/test/java/fileForTest.txt");
        peoples = fileReader.readFile();
        scanner = new Scanner("");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        System.setOut(new PrintStream(outContent));
    }

    @SneakyThrows
    @Test
    void testExitFromMainMenu() {
        scanner = new Scanner("0");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = "Bye!";

        searchEngine.run();
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @SneakyThrows
    @Test
    void testFindPersonWithAllStrategy() {
        scanner = new Scanner("ALL\nerick");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = """
                Select a matching strategy: ALL, ANY, NONE
                                
                Enter a name or email to search all suitable people.
                                
                2 persons found:
                                
                === List of people ===
                Erick Harrington harrington@gmail.com
                Erick Burgess""";
        searchEngine.findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @SneakyThrows
    @Test
    void testFailedFindPersonWithAllStrategy() {
        scanner = new Scanner("ALL\nerick myrtle");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = "No matching person found.";
        searchEngine.findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @SneakyThrows
    @Test
    void testFindPersonWithAnyStrategy() {
        scanner = new Scanner("ANY\nerick myrtle");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = """
                Select a matching strategy: ALL, ANY, NONE
                                
                Enter a name or email to search all suitable people.
                                
                3 persons found:
                                
                === List of people ===
                Myrtle Medina
                Erick Harrington harrington@gmail.com
                Erick Burgess""";
        searchEngine.findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @SneakyThrows
    @Test
    void testFailedFindPersonWithAnyStrategy() {
        scanner = new Scanner("ANY\nsomestring");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = "No matching person found.";
        searchEngine.findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @SneakyThrows
    @Test
    void testFindPersonWithNoneStrategy() {
        scanner = new Scanner("NONE\nerick");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = """
                Select a matching strategy: ALL, ANY, NONE
                                
                Enter a name or email to search all suitable people.
                                
                4 persons found:
                               
                === List of people ===
                Myrtle Medina
                Katie Jacobs
                Rene Webb webb@gmail.com
                Dwight Joseph djo@gmail.com""";
        searchEngine.findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @SneakyThrows
    @Test
    void testFailedFindPersonWithNoneStrategy() {
        scanner = new Scanner("NONE\nerick myrtle katie rene dwight");
        searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
        String expected = "No matching person found.";
        searchEngine.findPeoples(lineTokenizer.convertToLinesIndexesByWord(peoples));
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @Test
    void printMenu() {
        String expected = """
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""";
        searchEngine.printMenu();
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }

    @Test
    void printPeoples() throws IOException {
        String expected = """
                === List of people ===
                Dwight Joseph djo@gmail.com
                Rene Webb webb@gmail.com
                Katie Jacobs
                Erick Harrington harrington@gmail.com
                Myrtle Medina
                Erick Burgess""";

        searchEngine.printPeoples(fileReader.readFile());
        var result = outContent.toString().trim();
        assertTrue(result.contains(expected));
    }
}
