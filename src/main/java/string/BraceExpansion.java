package string;

import java.util.LinkedList;
import java.util.List;

public class BraceExpansion {

    public static final class Node {
        private String value;

        private Node next;

        public Node(String value) {
            this.value = value;
        }

    }

    public List<String> expansion(String input) {

        int n = input.length();

        Node head = null;
        Node previous = null;

        for (int i = 0; i < n; ) {

            char c = input.charAt(i);

            Node newNode;

            if (c == '{') {
                int j = input.indexOf('}', i);
                String s = input.substring(i + 1, j).replaceAll(",", "");
                newNode = new Node(s);
                i = j + 1;
            } else {
                newNode = new Node(c + "");
                i++;
            }


            if (head == null) {
                head = newNode;
                previous = newNode;
            } else {
                previous.next = newNode;
                previous = newNode;
            }
        }

        List<String> result = new LinkedList<>();

        dfs(head, new StringBuilder(), result);

        return result;

    }

    private void dfs(Node head, StringBuilder sb, List<String> result) {

        if (head == null) {
            result.add(sb.toString());
        } else {
            for (char c: head.value.toCharArray()) {
                dfs(head.next, sb.append(c), result);
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        BraceExpansion braceExpansion = new BraceExpansion();

        List<String> expansion = braceExpansion.expansion("a{d,c,b}{e,f,g}");

        System.out.println(expansion);
    }

}
