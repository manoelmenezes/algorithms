package stack;

public class StackArray {

    private int maxSize;
    private int top;
    private int[] items;

    public StackArray(int maxSize) {
        this.maxSize = maxSize;
        this.items = new int[this.maxSize];
        this.top = 0;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == maxSize;
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.items[top - 1];
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.items[--top];
    }

    public void push(int i) {
        if (isFull()) {
            throw new FullStackException();
        }

        this.items[top++] = i;
    }

    public int size() {
        return top;
    }

    public static void main(String[] args) {
        StackArray stack = new StackArray(5);

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
