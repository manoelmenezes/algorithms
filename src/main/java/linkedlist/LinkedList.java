package linkedlist;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LinkedList {

    private Node head;
    private Node tail;

    public int length() {
        int length = 0;

        Node current = head;

        while (current != null) {
            length++;
            current = current.getNext();
        }

        return length;
    }

    public LinkedList sum(LinkedList l1, LinkedList l2) {
        int n1 = l1.convertToNum();
        int n2 = l2.convertToNum();

        int sum = n1 + n2;

        return convertToList(sum);
    }

    public static LinkedList convertToList(int n) {
        LinkedList list = new LinkedList();

        while (n > 0) {
            int v = n < 10 ? n : n - (n/10) * 10;

            list.addHead(v);

            n = n / 10;
        }

        return list;
    }

    private void addHead(int v) {
        if (head == null) {
            head = new Node(v);
            tail = head;
            return;
        }

        Node newNode = new Node(v);

        newNode.setNext(head);

        head = newNode;
    }

    public int convertToNum() {
        return doConvertToNum(head, new int[] {1});
    }

    private int doConvertToNum(Node head, int[] v) {
        if (head == null) {
            return 0;
        }

        int t = doConvertToNum(head.getNext(), v);

        int result = head.getData() * v[0] + t;

        v[0] *= 10;

        return result;
    }

    public Optional<Node> findNthFromEnd(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        int count = 0;

        Node current = head;

        while (current != null && count < n) {
            current = current.getNext();
            count++;
        }

        if (current == null) {
            return Optional.empty();
        }

        Node result = head;
        while (current != null) {
            current = current.getNext();
            result = result.getNext();
        }

        return Optional.of(result);
    }

    public int lengthRecursive() {
        return doLengthRecursive(head);
    }

    private int doLengthRecursive(Node current) {
        if (current == null) {
            return 0;
        }

        return 1 + doLengthRecursive(current.getNext());
    }

    public void add(int i) {
        if (head == null) {
            head = new Node(i);
            tail = head;
        } else {
            tail.next = new Node(i);
            tail = tail.next;
        }
    }

    public Node getHead() {
        return head;
    }

    public void sortAndRemoveDuplicates() {
        if (head == null) {
            return;
        }

        sort();

        Node previous = head;
        Node current = head.getNext();

        while (current != null) {
            if (previous.getData() == current.getData()) {
                previous.setNext(current.getNext());
            } else {
                previous = previous.getNext();
            }
            current = current.getNext();
        }
    }

    public void removeDuplicates() {
        Set<Integer> visited = new HashSet<>();

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (visited.contains(current.getData())) {
                previous.setNext(current.getNext());
            } else {
                visited.add(current.getData());
                previous = current;
            }
            current = current.getNext();
        }
        if (previous.getNext() == null) {
            tail = previous;
        }
    }

    public void sort() {
        doSort(head, tail);
    }

    private void doSort(Node start, Node end) {
        if (start != end) {
            Node middle = getMiddle(start, end);
            doSort(start, middle);
            doSort(middle.getNext(), end);
            merge(start, middle, end);
        }
    }

    // O(n)
    private void merge(Node start, Node middle, Node end) {
        Node l1 = copy(start, middle);
        Node l2 = copy(middle.getNext(), end);

        Node current = start;

        while (current != end.getNext()) {

            if (l1 != null && l2 != null && l1.getData() < l2.getData()) {
                current.data = l1.getData();
                l1 = l1.getNext();
            } else if (l1 != null && l2 != null) {
                current.data = l2.getData();
                l2 = l2.getNext();
            } else if (l1 != null) {
                current.data = l1.getData();
                l1 = l1.getNext();
            } else {
                current.data = l2.getData();
                l2 = l2.getNext();
            }

            current = current.getNext();
        }
    }

    private Node copy(Node start, Node end) {
        LinkedList l = new LinkedList();
        Node current = start;

        while (current != end.getNext()) {
            l.add(current.getData());
            current = current.getNext();
        }

        return l.head;
    }

    // O(n)
    public Node getMiddle(Node start, Node end) {
        Node slow = start;
        Node fast = start;

        while (fast != end && fast.getNext() != end) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    public Node getMiddle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    public void remove(Node n) {
        Node previous = null;
        Node current = head;

        while (current != null && current != n) {
            previous = current;
            current = current.getNext();

        }

        if (current == null) {
            return;// not found
        }

        if (previous == null) {
            // remove head
            Node tmp = head;
            head = head.getNext();
            tmp.setNext(null);
        } else {
            previous.setNext(current.getNext());
            current.setNext(null);
            if (previous.getNext() == null) {
                tail = previous;
            }
        }
    }

    public static final class Node {
        private int data;

        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this(data);
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
