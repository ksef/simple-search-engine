package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.QuerySplitter.queryWords;

public class AllStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query) {
        String[] words = queryWords(query);
        List<String> found = new ArrayList<>();

        for (int index : searchMap.getOrDefault(words[0], new ArrayList<>())) {
            boolean temporaryIndex = false;

            for (String word : words) {
                if (!stringsList.get(index).toUpperCase().contains(word)) {
                    temporaryIndex = true;
                    break;
                }
            }

            if (!temporaryIndex) {
                found.add(stringsList.get(index));
            }
        }
        return found;
    }
}
