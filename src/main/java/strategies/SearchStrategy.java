package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<String> search(ArrayList<String> list, Map<String, ArrayList<Integer>> searchMap, String query);
}
