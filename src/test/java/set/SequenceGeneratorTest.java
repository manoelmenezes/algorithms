package set;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SequenceGeneratorTest {

    @Test
    public void testIsPossibleWhenInvalidSequences() {
        // set up
        List<Integer> l1 = List.of(0, 8, 1);
        List<Integer> l2 = List.of(1, 2, 8);
        List<List<Integer>> seqs = List.of(l1, l2);

        // SUT
        boolean result = SequenceGenerator.isPossible(seqs);

        // assert
        Assert.assertFalse(result);


    }

    @Test
    public void testIsPossibleWhenValidSequences() {
        // set up
        List<Integer> l1 = List.of(8, 1, 9);
        List<Integer> l2 = List.of(1, 3);
        List<Integer> l3 = List.of(1, 9);
        List<List<Integer>> seqs = List.of(l1, l2, l3);

        // SUT
        boolean result = SequenceGenerator.isPossible(seqs);

        // assert
        Assert.assertTrue(result);
    }
}
