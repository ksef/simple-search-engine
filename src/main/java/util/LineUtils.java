package util;

public final class LineUtils {

    private LineUtils() {
        throw new IllegalStateException();
    }

    public static String[] convertToArray(String query) {
        if (!query.isEmpty()) {
            return query.toUpperCase().trim().split("\\s+");
        } else {
            return null;
        }
    }
}
