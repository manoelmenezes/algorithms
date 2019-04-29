package stack;

import java.util.Stack;

public class BalancedExpresion {

    public static boolean isBalanced(String input) {
        Stack<Character> chars = new Stack<>();

        for (char c: input.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                chars.push(c);
            } else {
                if (chars.isEmpty()) {
                    return false;
                }
                char top = chars.pop();
                if ((top == '(' && c != ')')
                        || (top == '{' && c != '}')
                        || (top == '[' && c != ']') ) {
                    return false;
                }
            }
        }

        return chars.isEmpty();

    }

    public static void main(String[] args) {
        System.out.println(isBalanced("{[{}{}]}[()]"));
        System.out.println(isBalanced("{{}{}}"));
        System.out.println(isBalanced("[]{}()"));
        System.out.println(isBalanced("{()}[)"));
        System.out.println(isBalanced("{(})"));
        System.out.println(isBalanced("{})"));
        System.out.println(isBalanced("{()}{"));
    }

}
