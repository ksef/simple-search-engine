package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.QueryFilter.queryFilter;

public class AllStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query) {
        String[] words = queryFilter(query);
        List<String> found = new ArrayList<>();

        searchMap.getOrDefault(words[0], new ArrayList<>())
                .forEach(index -> {
                    boolean temporaryIndex = Arrays.stream(words)
                            .anyMatch(word -> !stringsList.get(index).toUpperCase().contains(word));
                    if (!temporaryIndex) {
                        found.add(stringsList.get(index));
                    }
                });
        return found;
    }
}
