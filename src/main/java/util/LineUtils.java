package util;

public final class LineUtils {

    private LineUtils() {
        throw new IllegalStateException();
    }

    public static String[] convertToArray(String query) {
        if (query.equals("")) {
            throw new IllegalArgumentException("You press the enter");
        } else {
            return query.toUpperCase().trim().split("\\s+");
        }
    }
}
