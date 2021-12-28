import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2 && args[0].equals("-fileName")) {
            ReadManager readManager = new ReadManager(new File(args[1]));
            SearchEngine searchEngine = new SearchEngine(readManager);
            searchEngine.run();
        } else {
            throw new IllegalArgumentException("Enter the '-fileName' and the path to the document.txt");
        }
    }
}
