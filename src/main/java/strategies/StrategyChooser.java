package strategies;

import strategies.impl.AllStrategyImpl;
import strategies.impl.AnyStrategyImpl;
import strategies.impl.NoneStrategyImpl;

public class StrategyChooser {

    public SearchStrategy get(String strategy) {
        return switch (strategy) {
            case "ALL" -> new AllStrategyImpl();
            case "ANY" -> new AnyStrategyImpl();
            case "NONE" -> new NoneStrategyImpl();
            default -> throw new IllegalArgumentException("Choose correct strategy");
        };
    }
}
