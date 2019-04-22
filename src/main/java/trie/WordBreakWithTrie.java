package trie;

import java.util.List;
import java.util.Optional;

public class WordBreakWithTrie implements WordBreak {

    public List<String> breakString(String input, List<String> dict) {
        Trie trie = new TrieMap();

        trie.insertAll(dict);

        Optional<String> wordBreak = doBreakString(trie, input);

        if (wordBreak.isPresent()) {
            return List.of(wordBreak.get());
        }

        throw new RuntimeException("impossible to break.");
    }

    private  Optional<String> doBreakString(Trie trie, String input) {
        if (input.length() == 0) {
            return Optional.of("");
        }

        if (trie.search(input)) {
            return Optional.of(input);
        }

        for (int i = 0; i < input.length(); i++) {
            String prefix = input.substring(0, i + 1);

            if (trie.search(prefix)) {
                Optional<String> wordBreak = doBreakString(trie, input.substring(i + 1));

                if (wordBreak.isPresent()) {
                    return Optional.of(prefix + " " + wordBreak.get());
                }
            }

        }

        return Optional.empty();
    }
}
