package stack;

import java.util.LinkedList;
import java.util.List;

public class StackLinkedList {

    private List<Integer> items;

    public StackLinkedList() {
        this.items = new LinkedList<>();
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return items.get(0);
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return items.remove(0);
    }

    public void push(int i) {
        items.add(0, i);
    }

    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        try {
            stack.push(6);
        } catch (FullStackException e) {
            System.err.println("Stack full");
        }

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        try {
            System.out.println(stack.pop());
        } catch (EmptyStackException e) {
            System.err.println("Empty stack");
        }

    }
}
