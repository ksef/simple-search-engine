package util;

public class QuerySplitter {

    public static String[] queryWords(String query) {
        return query.split("\\s+");
    }
}
