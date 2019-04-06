package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

public class Node {

    private int data;

    private Node left;

    private Node right;

    public Node(final int data) {
        this.data = data;
    }

    public Node(final int data, final Node left, final Node right) {
        this(data);
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public static Node closestLeftAncestorBst(final Node root, final int key) {
        return doClosestLeftAncestorBst(root, key, null);
    }

    private static Node doClosestLeftAncestorBst(final Node root, final int key, final Node ancestor) {
        if (root == null) {
            return null;
        }

        if (root.data == key) {
            return ancestor;
        }

        if (key < root.data) {
            return doClosestLeftAncestorBst(root.left, key, ancestor);
        }

        return root.right;
    }

    public static void dfs(final Node root) {
        Map<Node, Integer> first = new HashMap<>();
        Map<Node, Integer> second = new HashMap<>();
        Map<Node, Node> ancestors = calculateAncestors(root, first, second);

        dfs(root, first, second, ancestors);

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        Map<Node, Integer> levels = new HashMap<>();
        levels.put(root, 0);

        int level = 0;

        while (!nodes.isEmpty()) {
            Node t = nodes.poll();

            int f = first.get(t);

            String tabs = IntStream.range(0, f)
                    .mapToObj(i -> "\t")
                    .reduce("", (a, b) -> a + b);

            int l = levels.get(t);
            if (level != l) {
                System.out.println();
                level++;
            }

            System.out.print(tabs + t.data);

            if (t.left != null) {
                nodes.add(t.left);
                levels.put(t.left, l + 1);
            }

            if (t.right != null) {
                nodes.add(t.right);
                levels.put(t.right, l + 1);
            }
        }

    }

    private static Map<Node, Node> calculateAncestors(
            final Node root,
            final Map<Node, Integer> first,
            final Map<Node, Integer> second) {


        Map<Node, Node> ancestors = new HashMap<>();

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);


        while (!nodes.isEmpty()) {
            Node t = nodes.poll();
            first.put(t, 0);
            second.put(t, 0);

            Node a = closestLeftAncestorBst(root, t.data);

            ancestors.put(t, a);

            if (t.left != null) {
                nodes.add(t.left);
            }

            if (t.right != null) {
                nodes.add(t.right);
            }
        }

        return ancestors;
    }


    private static void dfs(
            final Node root,
            final Map<Node, Integer> first,
            final Map<Node, Integer> second,
            final Map<Node, Node> closestLeftAncestor) {
        if (root != null) {
            dfs(root.left, first, second, closestLeftAncestor);
            dfs(root.right, first, second, closestLeftAncestor);
            int f = 0;
            if (root.left != null) {
                f = second.get(root.left) + 1;
            } else {
                Node ancestor = closestLeftAncestor.get(root);
                if (ancestor != null) {
                    f = first.get(ancestor) + 1;
                }
            }
            int s = f;
            if (root.right != null) {
                s = second.get(root.right);
            }
            first.put(root, f);
            second.put(root, s);
        }
    }

    public static void printLevelOrder(final Node root) {

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        Map<Node, Integer> levels = new HashMap<>();
        levels.put(root, 0);
        int level = 0;

        while (!nodes.isEmpty()) {
            Node t = nodes.poll();

            int l = levels.get(t);

            if (level != l) {
                System.out.println();
                level++;
            }

            System.out.print(t.data + " ");

            if (t.left != null) {
                nodes.add(t.left);
                levels.put(t.left, l + 1);
            }

            if (t.right != null) {
                nodes.add(t.right);
                levels.put(t.right, l + 1);
            }

        }
    }

    public static class VerticalLinesOutput {
        private int min;
        private int max;
        private Map<Integer, List<Node>> verticalLines;

        public VerticalLinesOutput(final int min, final int max, final Map<Integer, List<Node>> verticalLines) {
            this.min = min;
            this.max = max;
            this.verticalLines = verticalLines;
        }

    }

    public static VerticalLinesOutput getVerticalLines(final Node node) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(node);

        Map<Integer, List<Node>> nodesPerVerticalLine = new HashMap<>();
        List<Node> verticalLine = new ArrayList<>();
        verticalLine.add(node);
        nodesPerVerticalLine.put(0,verticalLine);

        Map<Node, Integer> verticalLinesPerNode = new HashMap<>();
        verticalLinesPerNode.put(node,  0);
        int min = 0;
        int max = 0;

        while (!nodes.isEmpty()) {

            Node t = nodes.poll();
            Integer line = verticalLinesPerNode.get(t);

            if (t.left != null) {
                nodes.add(t.left);

                if (line - 1 < min) {
                    min = line - 1;
                }

                verticalLinesPerNode.put(t.left, line - 1);

                List<Node> nodesInLine = new ArrayList<>();
                nodesInLine.add(t.left);

                List<Node> nodesInLineOld = nodesPerVerticalLine.putIfAbsent(line - 1, nodesInLine);
                if (nodesInLineOld != null) {
                    nodesInLineOld.add(t.left);
                }
            }

            if (t.right != null) {
                nodes.add(t.right);

                if (line + 1 > max) {
                    max = line + 1;
                }

                verticalLinesPerNode.put(t.right, line + 1);

                List<Node> nodesInLine = new ArrayList<>();
                nodesInLine.add(t.right);

                List<Node> nodesInLineOld = nodesPerVerticalLine.putIfAbsent(line + 1, nodesInLine);
                if (nodesInLineOld != null) {
                    nodesInLineOld.add(t.right);
                }
            }
        }

        return new VerticalLinesOutput(min, max, nodesPerVerticalLine);
    }

    public static void printVerticalLines(Map<Integer, List<Node>> nodesPerVerticalLine) {
        nodesPerVerticalLine.entrySet().stream()
                .forEach(e -> System.out.println(e.getValue().stream().map(t -> t.data + "").reduce("", (a, b) -> a + " " + b)));
    }

    public static void getVerticalLinesPreOrder(final Node root) {

        Map<Integer, List<Integer>> r = new HashMap<>();
        int[] min = {0};
        int[] max = {0};
        doVerticalLines(root, 0, r, min, max);

        for (int i = min[0]; i <= max[0]; i++) {
            r.get(i).stream().forEach(v -> System.out.print(v + " "));
        }

    }

    private static void doVerticalLines(final Node root,
                                        final int v,
                                        final Map<Integer, List<Integer>> r,
                                        final int[] min,
                                        final int[] max) {
        if (root != null) {
            if (v < min[0]) {
                min[0] = v;
            }
            if (v > max[0]) {
                max[0] = v;
            }
            List<Integer> l = new ArrayList<>();
            l.add(root.data);
            List<Integer> old = r.putIfAbsent(v, l);
            if (old != null) {
                old.add(root.data);
            }
            doVerticalLines(root.left, v - 1, r, min, max);
            doVerticalLines(root.right, v + 1, r, min, max);
        }
    }

    public static void main(String[] args) {
//        Node root = new Node(10);
//        root.left = new Node(5);
//        root.right = new Node(20);
//        root.left.left = new Node(1);
//        root.left.right = new Node(8);
//        root.right.left = new Node(15);
//        root.right.right = new Node(25);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.left.left = new Node(100);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        root.right.right.right = new Node(9);
        root.right.right.left= new Node(10);
        root.right.right.left.right= new Node(11);
        root.right.right.left.right.right= new Node(12);


        VerticalLinesOutput verticalLinesOutput = getVerticalLines(root);

        printVerticalLines(verticalLinesOutput.verticalLines);
    }

}
