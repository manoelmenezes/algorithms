package trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordBreakWithTrieTest {

    @Test
    public void testBreakString() {
        // set up
        String input = "Wordbreakproblem";

        List dict = List.of("this", "th", "is", "famous", "Word", "break", "b",
                "r", "e", "a", "k", "br", "bre", "brea", "ak", "problem");

        // SUT
        WordBreakWithTrie wordBreak = new WordBreakWithTrie();

        List<String> sentence = wordBreak.breakString(input, dict);

        // assert
        Assert.assertEquals("Word b r e a k problem", sentence.get(0));
    }

}
