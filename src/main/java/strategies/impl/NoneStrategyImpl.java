package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoneStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> list, Map<String, ArrayList<Integer>> searchMap, String query) {
        String[] words = queryWords(query);
        List<String> found = new ArrayList<>();

        boolean skipThisIndex = false;
        for (String s : list) {
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
