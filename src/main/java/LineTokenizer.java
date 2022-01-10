import util.QueryFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LineTokenizer {

    public Map<String, List<Integer>> mapWords(List<String> lines) {
        Map<String, List<Integer>> wordsChart = new HashMap<>();
        lines.stream()
                .map(QueryFilter::queryFilter)
                .flatMap(Stream::of)
                .map(String::toUpperCase)
                .filter(Predicate.not(wordsChart::containsKey))
                .forEach(word -> wordsChart.put(word, getLinesWithWord(lines, word)));
        return wordsChart;
    }

    private List<Integer> getLinesWithWord(List<String> list, String word) {
        return IntStream
                .range(0, list.size())
                .filter(rowIndex -> list.get(rowIndex).toUpperCase().contains(word))
                .boxed()
                .collect(Collectors.toList());
    }
}
