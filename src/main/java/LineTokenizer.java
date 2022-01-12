import util.LineUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LineTokenizer {

    public Map<String, List<Integer>> mapWords(List<String> lines) {

        return lines.stream()
                .map(LineUtils::convertToArray)
                .flatMap(Stream::of)
                .map(String::toUpperCase)
                .collect(Collectors.toMap(
                        Function.identity(),
                        word -> getLinesWithWord(lines, word),
                        (existing, replacement) -> existing));
    }

    private List<Integer> getLinesWithWord(List<String> list, String word) {
        return IntStream
                .range(0, list.size())
                .filter(rowIndex -> list.get(rowIndex).toUpperCase().contains(word))
                .boxed()
                .collect(Collectors.toList());
    }
}
