import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {
    private final Map<String, Integer> wordsMap;
    private final String SPACE = " ";
    private final ComparisionMode comparisionMode;

    public WordCounter() {
        this(ComparisionMode.CASE_SENSITIVE);
    }

    public WordCounter(ComparisionMode mode) {
        wordsMap = new HashMap<>();
        comparisionMode = mode;
    }

    public void analyze(String str) {
        if (StringUtils.isBlank(str)) {
            return;
        }
        Arrays.asList(str.split(SPACE))
                .stream()
                .map(this::normalize)
                .forEach(this::updateMap);
    }

    public List<Map.Entry<String, Integer>> sortedByValue() {
        return this.wordsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getWordsMap() {
        return this.wordsMap;
    }


    private void updateMap(String word) {
        if (StringUtils.isBlank(word)) {
            return;
        }

        if (!wordsMap.containsKey(word)) {
            wordsMap.put(word, 1);
        } else {
            wordsMap.put(word, wordsMap.get(word) + 1);
        }
    }

    private String normalize(String word) {
        String normalized = ComparisionMode.CASE_SENSITIVE == comparisionMode ? word : word.toLowerCase();
        normalized = normalized.replaceAll("[^\\p{IsDigit}\\p{IsAlphabetic}]", "");
        return normalized;
    }
}
