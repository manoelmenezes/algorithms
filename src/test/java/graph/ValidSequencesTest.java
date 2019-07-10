package graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ValidSequencesTest {

    @Test
    public void testIsValidWithValidSequences() {
        // set up
        List<Integer> l1 = List.of(0, 1, 2);
        List<Integer> l2 = List.of(1, 3);
        List<Integer> l3 = List.of(1, 4);
        List<List<Integer>> seqs = List.of(l1, l2, l3);

        // SUT
        ValidSequences.ValidSequenceOutput output = ValidSequences.isValid(seqs);

        // assert
        Assert.assertFalse(output.isContainsCycle());
        Assert.assertEquals(0, output.getSequence().indexOf(0));
        Assert.assertEquals(1, output.getSequence().indexOf(1));
        Assert.assertTrue(output.getSequence().indexOf(2) > output.getSequence().indexOf(1));
        Assert.assertTrue(output.getSequence().indexOf(3) > output.getSequence().indexOf(1));
        Assert.assertTrue(output.getSequence().indexOf(4) > output.getSequence().indexOf(1));
    }

    @Test
    public void testIsValidWithInvalidSequences() {
        // set up
        List<Integer> l1 = List.of(0, 1, 2);
        List<Integer> l2 = List.of(1, 3);
        List<Integer> l3 = List.of(2, 1);
        List<List<Integer>> seqs = List.of(l1, l2, l3);


        // SUT
        ValidSequences.ValidSequenceOutput output = ValidSequences.isValid(seqs);

        // assert
        Assert.assertTrue(output.isContainsCycle());
        Assert.assertNull(output.getSequence());
    }

}
