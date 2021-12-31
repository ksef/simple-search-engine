package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.QuerySplitter.queryWords;

public class NoneStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query) {
        String[] words = queryWords(query);
        List<String> found = new ArrayList<>();

        boolean skipThisIndex = false;
        for (String s : stringsList) {
            for (String word : words) {
                if (s.toUpperCase().contains(word)) {
                    skipThisIndex = true;
                    break;
                }
            }
            if (!skipThisIndex) {
                found.add(s);
            }
        }
        return found;
    }
}
