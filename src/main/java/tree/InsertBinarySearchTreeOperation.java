package tree;

import java.util.HashMap;
import java.util.Map;

public class InsertBinarySearchTreeOperation {

    private static Node findParent(final Node root, final Node parent, final int key) {
        if (root == null) {
            return parent;
        }

        if (root.getData() > key) {
            return findParent(root.getLeft(), root, key);
        } else {
            return findParent(root.getRight(), root, key);
        }
    }

    public static Node insertBst(final Node root, final int key) {

        Node p = findParent(root, null, key);

        if (p == null) {
            return new Node(key);
        }

        if (p.getData() > key) {
            p.setLeft(new Node(key));
        } else {
            p.setRight(new Node(key));
        }

        return root;
    }

    public static Node insertBstIterative(final Node root, final int key) {
        if (root == null) {
            return new Node(key);
        }

        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (current.getData() > key) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (parent.getData() > key) {
            parent.setLeft(new Node(key));
        } else {
            parent.setRight(new Node(key));
        }

        return root;
    }

    public static Map<Node, Node> calculateParent(final Node root) {
        Map<Node, Node> parents = new HashMap<>();

        if (root != null) {
            parents.put(root, null);

            doCalculateParent(root, parents);
        }

        return parents;
    }

    private static void doCalculateParent(final Node root, final Map<Node, Node> parents) {
        if (root.getLeft() != null) {
            parents.put(root.getLeft(), root);
            doCalculateParent(root.getLeft(), parents);
        }

        if (root.getRight() != null) {
            parents.put(root.getRight(), root);
            doCalculateParent(root.getRight(), parents);
        }
    }
}
