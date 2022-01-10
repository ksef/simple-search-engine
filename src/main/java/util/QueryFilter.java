package util;

public final class QueryFilter {

    public static String[] queryFilter(String query) {
        return query.trim().split("\\s+");
    }
}
