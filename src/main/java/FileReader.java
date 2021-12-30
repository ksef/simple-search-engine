import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class FileReader {

    private final String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readFile() throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}
