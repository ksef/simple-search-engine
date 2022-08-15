package strategies.impl;

import strategies.SearchStrategy;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A class to find none matches
 */
public class NoneStrategyImpl implements SearchStrategy {

    /**
     * Returns a list of matching people
     */
    @Override
    public List<String> search(List<String> peoples, Map<String, List<Integer>> linesIndexesByWord, String[] queryWords) {
        List<Integer> wordIndexes = matchWordIndexes(linesIndexesByWord, queryWords);

        return linesIndexesByWord.values().stream()
                .flatMap(List::stream)
                .filter(Predicate.not(wordIndexes::contains))
                .map(peoples::get)
                .distinct()
                .collect(Collectors.toList());
    }
}
