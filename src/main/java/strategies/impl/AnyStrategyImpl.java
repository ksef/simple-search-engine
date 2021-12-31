package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.QuerySplitter.queryWords;

public class AnyStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query) {
        String[] words = queryWords(query);
        List<String> found = new ArrayList<>();

        for (String word : words) {
            for (int index : searchMap.get(word)) {
                if (!found.contains(stringsList.get(index))) {
                    found.add(stringsList.get(index));
                }
            }
        }
        return found;
    }
}
