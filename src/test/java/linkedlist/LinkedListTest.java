package linkedlist;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testConvertToNum() {
        // set up
        LinkedList list = new LinkedList();
        list.add(5);
        list.add(6);
        list.add(3);

        // SUT
        long n = list.convertToNum();

        // Assert
        Assert.assertEquals(563, n);

    }

    @Test
    public void testSortAndRemoveDuplicates() {
        // Set up
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(-1);
        list.add(0);

        // SUT
        list.sortAndRemoveDuplicates();

        // assert
        Assert.assertEquals(-1, list.getHead().getData());
        Assert.assertEquals(0, list.getHead().getNext().getData());
        Assert.assertEquals(1, list.getHead().getNext().getNext().getData());
        Assert.assertNull(list.getHead().getNext().getNext().getNext());
    }

    @Test
    public void testRemoveDuplicates() {
        // Set up
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(-1);
        list.add(0);

        // SUT
        list.removeDuplicates();

        // assert
        Assert.assertEquals(1, list.getHead().getData());
        Assert.assertEquals(0, list.getHead().getNext().getData());
        Assert.assertEquals(-1, list.getHead().getNext().getNext().getData());
        Assert.assertNull(list.getHead().getNext().getNext().getNext());
    }

    @Test
    public void testSort() {
        // Set up
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(-1);
        list.add(0);

        // SUT
        list.sort();

        // assert
        Assert.assertEquals(-1, list.getHead().getData());
        Assert.assertEquals(0, list.getHead().getNext().getData());
        Assert.assertEquals(0, list.getHead().getNext().getNext().getData());
        Assert.assertEquals(1, list.getHead().getNext().getNext().getNext().getData());
        Assert.assertEquals(1, list.getHead().getNext().getNext().getNext().getNext().getData());
        Assert.assertNull(list.getHead().getNext().getNext().getNext().getNext().getNext());
    }
}

