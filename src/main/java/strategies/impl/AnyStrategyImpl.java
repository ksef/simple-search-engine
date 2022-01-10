package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.QueryFilter.queryFilter;

public class AnyStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query) {
        String[] words = queryFilter(query);
        List<String> found = new ArrayList<>();

        Arrays.stream(words)
                .forEachOrdered(word -> searchMap.get(word)
                        .stream()
                        .mapToInt(index -> index)
                        .filter(index -> !found.contains(stringsList.get(index)))
                        .forEachOrdered(index -> found.add(stringsList.get(index))));
        return found;
    }
}
