package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakDp implements WordBreak {

    @Override
    public List<String> breakString(String input, List<String> dict) {
        return new ArrayList<>(wordBreak(input, 0, input.length() - 1, new HashSet<>(dict), new HashMap<>()));
    }

    public Set<String> wordBreak(String input, int i, int j, Set<String> dict, Map<String, Set<String >> table) {

        String key = i + "|" + j;

        Set<String> result = table.get(key);

        if (result != null) {
            return result;
        }

        Set<String> words = new HashSet<>();

        table.put(key, words);

        String s = input.substring(i, j + 1);

        if (dict.contains(s)) {
            words.add(s);
        }

        if (i == j) {
            return words;
        }

        for (int k = i; k <= j - 1; k++) {
            Set<String> words1 = wordBreak(input, i, k, dict, table);
            Set<String> words2 = wordBreak(input, k + 1, j, dict, table);

            for (String w1: words1) {
                for (String w2: words2) {
                        words.add(w1 + " " + w2);
                }
            }

        }

        return words;
    }
}
