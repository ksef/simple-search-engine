import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 2 && args[0].equals("-filePath")) {
            FileReader fileReader = new FileReader(args[1]);
            SearchEngine searchEngine = new SearchEngine(fileReader);
            searchEngine.run();
        } else {
            throw new IllegalArgumentException("Enter the '-filePath' and the path to the text.txt");
        }
    }
}
