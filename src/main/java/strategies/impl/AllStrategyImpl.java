package strategies.impl;

import strategies.SearchStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AllStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> peopleList, String[] words) {
        return peopleList.stream()
                .filter(person -> Arrays.stream(words).allMatch(word -> person.toUpperCase().contains(word)))
                .collect(Collectors.toList());
    }
}
