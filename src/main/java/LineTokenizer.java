import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LineTokenizer {
    Map<String, ArrayList<Integer>> newMapWords;
    List<Integer> linesWithWord;

    public Map<String, ArrayList<Integer>> mapWords(List<String> lines) {
        newMapWords = new HashMap<>();
        lines.stream()
                .map(line -> line.trim().split("\\s+"))
                .flatMap(Stream::of)
                .map(String::toUpperCase).filter(word -> !newMapWords.containsKey(word))
                .forEach(word -> newMapWords.put(word, (ArrayList<Integer>) getLinesWithWord(lines, word)));
        return newMapWords;
    }

    private List<Integer> getLinesWithWord(List<String> list, String word) {
        linesWithWord = IntStream.range(0, list.size())
                .filter(rowIndex -> list.get(rowIndex).toUpperCase().contains(word))
                .boxed().collect(Collectors.toCollection(ArrayList::new));
        return linesWithWord;
    }
}
