package strategies;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<String> search(List<String> stringsList, Map<String, List<Integer>> searchMap, String query);
}
