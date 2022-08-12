import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 2 && args[0].equals("-filePath")) {
            FileReader fileReader = new FileReader(args[1]);
            LineTokenizer lineTokenizer = new LineTokenizer();
            Scanner scanner = new Scanner(System.in);
            SearchEngine searchEngine = new SearchEngine(fileReader, scanner, lineTokenizer);
            searchEngine.run();
        } else {
            throw new IllegalArgumentException("Enter the '-filePath' and the path to the text.txt");
        }
    }
}
