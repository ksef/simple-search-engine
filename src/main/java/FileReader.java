import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A class for reading a file from a path
 */
@AllArgsConstructor
class FileReader {

    private final String filePath;

    /**
     * Reads a file from the path
     */
    public List<String> readFile() throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}
