package impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoneStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(ArrayList<String> list, Map<String, ArrayList<Integer>> searchMap, String query) {
        String[] words = query.trim().split("\s");
        ArrayList<String> found = new ArrayList<>();

        outer:
        for (String s : list) {
            for (String word : words) {
                if (s.toUpperCase().contains(word)) {
                    continue outer;
                }
            }
            found.add(s);
        }
        return found;
    }
}
