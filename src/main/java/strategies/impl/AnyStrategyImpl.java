package strategies.impl;

import strategies.SearchStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnyStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String[] words) {
        return stringsList.stream()
                .filter(s -> Arrays.stream(words).anyMatch(word -> s.toUpperCase().contains(word)))
                .collect(Collectors.toList());
    }
}
