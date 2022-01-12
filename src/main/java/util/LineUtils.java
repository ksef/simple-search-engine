package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class LineUtils {

    public String[] convertToArray(String query) {
        return query.toUpperCase().trim().split("\\s+");
    }
}
