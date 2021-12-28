package impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(ArrayList<String> list, Map<String, ArrayList<Integer>> searchMap, String query) {
        String[] words = query.trim().split("\s");
        ArrayList<String> found = new ArrayList<>();

        outer:
        for (int index : searchMap.getOrDefault(words[0], new ArrayList<>())) {
            for (String word : words) {
                if (!list.get(index).toUpperCase().contains(word)) {
                    continue outer;
                }
            }
            found.add(list.get(index));
        }
        return found;
    }
}
