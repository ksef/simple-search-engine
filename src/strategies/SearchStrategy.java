package strategies;

import java.util.ArrayList;
import java.util.Map;

public interface SearchStrategy {

    void search(ArrayList<String> list, Map<String, ArrayList<Integer>> searchMap, String query);
}
