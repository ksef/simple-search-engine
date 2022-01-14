package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> peoples, Map<String, List<Integer>> linesIndexesByWord, String[] queryWords) {
        List<Integer> wordIndexes = matchWordIndexes(linesIndexesByWord, queryWords);

        return linesIndexesByWord.values().stream()
                .flatMap(List::stream)
                .filter(wordIndexes::contains)
                .map(peoples::get)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> matchWordIndexes(Map<String, List<Integer>> linesIndexesByWord, String[] queryWords) {
        List<Integer> wordIndexes = linesIndexesByWord.getOrDefault(queryWords[0].toUpperCase(), new ArrayList<>());
        IntStream.range(1, queryWords.length)
                .mapToObj(i -> linesIndexesByWord.getOrDefault(queryWords[i], new ArrayList<>()))
                .forEach(wordIndexes::retainAll);
        return wordIndexes;
    }
}
