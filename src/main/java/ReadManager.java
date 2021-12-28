import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadManager {

    private final ArrayList<String> list = new ArrayList<>();
    private final File file;

    public ReadManager(File file) {
        this.file = file;
    }

    public ArrayList<String> readFile() {
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                list.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return list;
    }
}
