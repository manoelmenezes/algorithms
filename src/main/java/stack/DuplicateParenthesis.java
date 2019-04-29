package stack;

import java.util.Stack;

public class DuplicateParenthesis {

    public static boolean hasDuplicate(String input) {
        Stack<Character> chars = new Stack<>();

        boolean ignored = true;
        for (char c: input.toCharArray()) {
            if (c == '(') {
                chars.push(c);
            } else if (c == ')') {
                if (!ignored) {
                    return true;
                }
                chars.pop();
                ignored = false;
            } else {
                ignored = true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasDuplicate("((x+y))+z"));
        System.out.println(hasDuplicate("(x+y)"));
        System.out.println(hasDuplicate("((x+y)+((z)))"));
    }

}
