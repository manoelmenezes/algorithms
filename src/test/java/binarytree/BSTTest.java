package binarytree;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BSTTest {

    @Test
    public void testPostOrder() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(2);
        bst.add(8);

        // SUT
        List<Integer> postOrder = bst.postOrder();

        // assert
        Assert.assertEquals(List.of(2, 8, 5, 15, 10), postOrder);
    }

    @Test
    public void testRevert() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(2);
        bst.add(8);


        // SUT
        bst.revert();

        // assert
        Assert.assertEquals(List.of(15, 8, 2, 5, 10), bst.postOrder());
    }

    @Test
    public void testRemoveLeaf() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(2);
        bst.add(8);

        BST expected = new BST();
        expected.add(10);
        expected.add(5);
        expected.add(15);
        expected.add(2);

        // SUT
        boolean removed = bst.remove(8);

        // assert
        Assert.assertTrue(removed);
        Assert.assertTrue(expected.isEqual(bst));
    }

    @Test
    public void testRemoveRootWithSuccessorRightChild() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(2);
        bst.add(8);

        BST expected = new BST();
        expected.add(15);
        expected.add(5);
        expected.add(8);
        expected.add(2);

        // SUT
        boolean removed = bst.remove(10);

        // assert
        Assert.assertTrue(removed);
        Assert.assertTrue(expected.isEqual(bst));
    }

    @Test
    public void testRemoveRootWithSuccessor() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(13);
        bst.add(2);
        bst.add(8);

        BST expected = new BST();
        expected.add(13);
        expected.add(15);
        expected.add(5);
        expected.add(8);
        expected.add(2);

        // SUT
        boolean removed = bst.remove(10);

        // assert
        Assert.assertTrue(removed);
        Assert.assertTrue(expected.isEqual(bst));
    }

    @Test
    public void testRemoveRootWithPredecessor() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(2);
        bst.add(8);

        BST expected = new BST();
        expected.add(8);
        expected.add(5);
        expected.add(2);

        // SUT
        boolean removed = bst.remove(10);

        // assert
        Assert.assertTrue(removed);
        Assert.assertTrue(expected.isEqual(bst));
    }

    @Test
    public void testRemoveRootWithPredecessorLeftChild() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(2);

        BST expected = new BST();
        expected.add(5);
        expected.add(2);

        // SUT
        boolean removed = bst.remove(10);

        // assert
        Assert.assertTrue(removed);
        Assert.assertTrue(expected.isEqual(bst));
    }

    @Test
    public void testRemoveRoot() {
        // set up
        BST bst = new BST();
        bst.add(10);

        // SUT
        boolean removed = bst.remove(10);

        // assert
        Assert.assertTrue(removed);
        Assert.assertTrue(bst.isEmpty());
    }

    @Test
    public void testRemoveNotFound() {
        // set up
        BST bst = new BST();
        bst.add(10);
        bst.add(15);
        bst.add(5);

        BST expected = new BST();
        expected.add(10);
        expected.add(15);
        expected.add(5);

        // SUT
        boolean removed = bst.remove(11);

        // Assert
        Assert.assertFalse(removed);
        Assert.assertTrue(expected.isEqual(bst));
    }

}
