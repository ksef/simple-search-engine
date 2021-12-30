package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<String> search(List<String> list, Map<String, ArrayList<Integer>> searchMap, String query);

    default String[] queryWords(String query) {
        return query.trim().split("\\s+");
    }
}
