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
}
