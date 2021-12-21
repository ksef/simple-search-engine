import java.io.File;

public class Main {

    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        File file = new File(args[1]);
        searchEngine.run(file);
    }
}
