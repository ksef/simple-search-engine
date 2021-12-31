import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LineTokenizer {

    private final Map<String, List<Integer>> wordsChart;

    public LineTokenizer() {
        this.wordsChart = new HashMap<>();
    }

    public Map<String, List<Integer>> mapWords(List<String> lines) {
        lines.stream()
                .map(line -> line.split("\\s+"))
                .flatMap(Stream::of)
                .map(String::toUpperCase)
                .filter(word -> !wordsChart.containsKey(word))
                .forEach(word -> wordsChart.put(word, getLinesWithWord(lines, word)));
        return wordsChart;
    }

    private List<Integer> getLinesWithWord(List<String> list, String word) {
        return IntStream
                .range(0, list.size())
                .filter(rowIndex -> list.get(rowIndex).toUpperCase().contains(word))
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
