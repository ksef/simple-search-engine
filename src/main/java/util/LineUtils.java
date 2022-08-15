package util;

/**
 * A class for processing lines
 */
public final class LineUtils {

    private LineUtils() {
        throw new IllegalStateException();
    }

    /**
     * Converts a string to an array
     */
    public static String[] convertToArray(String query) {
        return query.toUpperCase().trim().split("\\s+");
    }
}
