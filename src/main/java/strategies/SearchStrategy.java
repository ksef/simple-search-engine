package strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<String> search(List<String> peoples, Map<String, List<Integer>> linesIndexesByWord, String[] queryWords);

    default List<Integer> matchWordIndexes(Map<String, List<Integer>> linesIndexesByWord, String[] queryWords) {
        return Arrays.stream(queryWords)
                .flatMap(word -> linesIndexesByWord.getOrDefault(word, new ArrayList<>()).stream())
                .distinct()
                .toList();
    }
}
