package strategies;

import strategies.impl.AllStrategyImpl;
import strategies.impl.AnyStrategyImpl;
import strategies.impl.NoneStrategyImpl;

public class StrategyChooser {

    public SearchStrategy get(String strategy) {
        switch (strategy) {
            case "ALL" -> {
                return new AllStrategyImpl();
            }
            case "ANY" -> {
                return new AnyStrategyImpl();
            }
            case "NONE" -> {
                return new NoneStrategyImpl();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
