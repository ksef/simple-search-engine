package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnyStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> peoples, Map<String, List<Integer>> linesIndexesByWord, String[] queryWords) {
        List<Integer> wordIndexes = Arrays.stream(queryWords)
                .flatMap(word -> linesIndexesByWord.getOrDefault(word, new ArrayList<>()).stream())
                .distinct()
                .toList();

        return linesIndexesByWord.keySet().stream()
                .flatMap(word -> linesIndexesByWord.getOrDefault(word, new ArrayList<>()).stream())
                .filter(wordIndexes::contains)
                .map(peoples::get)
                .distinct()
                .collect(Collectors.toList());
    }
}
