package strategies;

import java.util.List;

public interface SearchStrategy {

    List<String> search(List<String> peopleList, String[] query);
}
