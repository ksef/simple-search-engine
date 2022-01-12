package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NoneStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> peopleList, Map<String, List<Integer>> lines, String[] words) {
        List<Integer> wordIndexes =
                Arrays.stream(words)
                        .flatMap(word -> lines.getOrDefault(word, new ArrayList<>()).stream())
                        .toList();

        return lines.keySet().stream()
                .flatMap(word -> lines.getOrDefault(word, new ArrayList<>()).stream())
                .distinct()
                .filter(index -> !wordIndexes.contains(index))
                .map(peopleList::get)
                .distinct()
                .collect(Collectors.toList());
    }
}

