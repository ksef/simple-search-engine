package strategies.impl;

import strategies.SearchStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.QueryFilter.queryFilter;

public class NoneStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query) {
        String[] words = queryFilter(query);
        return stringsList.stream()
                .filter(s -> Arrays.stream(words).noneMatch(word -> s.toUpperCase().contains(word)))
                .collect(Collectors.toList());
    }
}
