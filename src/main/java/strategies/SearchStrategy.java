package strategies;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<String> search(List<String> peoples, Map<String, List<Integer>> linesIndexesByWord, String[] queryWords);
}
