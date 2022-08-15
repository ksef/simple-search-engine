import util.LineUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A class that tokenizes lines
 */
class LineTokenizer {

    /**
     * Converts to lines that are indexed by word
     */
    public Map<String, List<Integer>> convertToLinesIndexesByWord(List<String> lines) {
        return lines.stream()
                .map(LineUtils::convertToArray)
                .flatMap(Stream::of)
                .collect(Collectors.toMap(
                        Function.identity(),
                        word -> getLinesIndexes(lines, word),
                        (existing, replacement) -> existing));
    }

    /**
     * Returns a list of indexes
     */
    private List<Integer> getLinesIndexes(List<String> list, String word) {
        return IntStream
                .range(0, list.size())
                .filter(rowIndex -> list.get(rowIndex).toUpperCase().contains(word))
                .boxed()
                .collect(Collectors.toList());
    }
}
