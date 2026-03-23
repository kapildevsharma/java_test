package javaTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SortHashMapByValue {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("David", 95);
        scores.put("Jane", 80);
        scores.put("Mary", 97);
        scores.put("Lisa", 78);
        scores.put("Dino", 65);

        System.out.println(scores);

        scores = sortByValue(scores);

        System.out.println(scores);
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> scores) {
        List<Entry<String, Integer>> entries = new ArrayList<>(scores.entrySet());

        entries.sort(Entry.comparingByValue());

        Map<String, Integer> sortedScores = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : entries) {
            sortedScores.put(entry.getKey(), entry.getValue());
        }

        return sortedScores;
    }

}
