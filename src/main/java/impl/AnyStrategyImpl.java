package impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnyStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(ArrayList<String> list, Map<String, ArrayList<Integer>> searchMap, String query) {
        String[] words = query.trim().split("\s");
        ArrayList<String> found = new ArrayList<>();

        for (String word : words) {
            for (int index : searchMap.get(word)) {
                if (!found.contains(list.get(index))) {
                    found.add(list.get(index));
                }
            }
        }
        return found;
    }
}
