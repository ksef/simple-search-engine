//package strategies.impl;
//
//import strategies.SearchStrategy;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class AnyStrategyImpl implements SearchStrategy {
//
//    @Override
//    public List<String> search(List<String> peopleList, Map<String, List<Integer>> lines, String[] words) {
//        return peopleList.stream()
//                .filter(person -> Arrays.stream(words).anyMatch(word -> person.toUpperCase().contains(word)))
//                .collect(Collectors.toList());
//    }
//}


package strategies.impl;

import strategies.SearchStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AnyStrategyImpl implements SearchStrategy {

    @Override
    public List<String> search(List<String> peopleList, Map<String, List<Integer>> lines, String[] words) {
        List<String> foundPeoples = new ArrayList<>();
        Arrays.stream(words).forEach(word -> {
            lines.get(word)
                    .stream().mapToInt(index -> index)
                    .filter(i -> !foundPeoples.contains(peopleList.get(i)))
                    .forEach(i -> foundPeoples.add(peopleList.get(i)));
        });
        return foundPeoples;
    }
}
