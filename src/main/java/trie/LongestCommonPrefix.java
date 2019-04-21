package trie;

import java.util.List;

public class LongestCommonPrefix {

    public String getLongestCommonPrefix(List<String> words) {
        Trie trie = new TrieMap();

        trie.insertAll(words);

        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

}
