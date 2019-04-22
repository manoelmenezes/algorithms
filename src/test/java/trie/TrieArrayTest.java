package trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TrieArrayTest {

    @Test
    public void testInsert() {
        // set up
        final String java = "java";
        final String is = "is";
        final String great = "great";

        // SUT
        TrieArray trie = new TrieArray();
        trie.insert(java);
        trie.insert(is);
        trie.insert(great);

        // assert
        Assert.assertTrue(trie.search(java));
        Assert.assertTrue(trie.search(is));
        Assert.assertTrue(trie.search(great));
    }

    @Test
    public void testDelete() {
        // set up
        final String java = "java";

        // SUT
        TrieArray trie = new TrieArray();
        trie.insert(java);

        // assert
        Assert.assertTrue(trie.search(java));

        Assert.assertTrue(trie.delete(java));

        Assert.assertFalse(trie.search(java));
    }

    @Test
    public void testGetLongestCommonPrefix() {
        // set up
        List<String> words = List.of("code", "codable", "coder", "coding");

        // SUT
        Trie trie = new TrieArray();
        trie.insertAll(words);

        // assert
        Assert.assertEquals("cod", trie.getLongestCommonPrefix());
    }

    @Test
    public void testGetLexicographicSort() {
        // set up
        List<String> dict = List.of(
                "lexicographic", "sorting", "of", "a", "set", "of", "keys",
                "can", "be", "accomplished", "with", "a", "simple", "trie",
                "based", "algorithm", "we", "insert", "all", "keys", "in",
                "a", "trie", "output", "all", "keys", "in", "the", "trie",
                "by", "means", "of", "preorder", "traversal", "which",
                "results", "in", "output", "that", "is", "in",
                "lexicographically", "increasing", "order",
                "preorder", "traversal", "is", "a", "kind",
                "of", "depth", "first", "traversal"
        );

        List<String> expected = List.of("a",
                "accomplished",
                "algorithm",
                "all",
                "based",
                "be",
                "by",
                "can",
                "depth",
                "first",
                "in",
                "increasing",
                "insert",
                "is",
                "keys",
                "kind",
                "lexicographic",
                "lexicographically",
                "means",
                "of",
                "order",
                "output",
                "preorder",
                "results",
                "set",
                "simple",
                "sorting",
                "that",
                "the",
                "traversal",
                "trie",
                "we",
                "which",
                "with");

        // SUT
        Trie trie = new TrieArray();
        trie.insertAll(dict);

        List<String> lexicographicSort = trie.getLexicographicSort();

        // assert
        Assert.assertEquals(expected, lexicographicSort);
    }
}
