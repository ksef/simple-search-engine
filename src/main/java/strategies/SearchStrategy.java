package strategies;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<String> search(List<String> peopleList, Map<String, List<Integer>> lines, String[] query);
}
