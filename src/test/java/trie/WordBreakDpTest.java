package trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordBreakDpTest {

    @Test
    public void testBreakString() {
        // set up
        String input = "Wordbreakproblem";

        List dict = List.of("this", "th", "is", "famous", "Word", "break", "b",
                "r", "e", "a", "k", "br", "bre", "brea", "ak", "problem");

        // SUT
        WordBreakDp wordBreakDp = new WordBreakDp();

        List<String> words = wordBreakDp.breakString(input, dict);

        // assert
        Assert.assertEquals(List.of("ab cd ab"), words);
    }
}
