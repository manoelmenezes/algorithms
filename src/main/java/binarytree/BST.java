package binarytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

public class BST {

    private Node root;

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        Node(final int data) {
            this.data = data;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    public void add(int data) {
        if (root == null) {
            root = new Node(data);
        } else {

            Node p = null;
            Node c = root;
            while (c != null) {
                p = c;
                if (c.data >= data) {
                    c = c.left;
                } else {
                    c = c.right;
                }
            }

            if (p.data >= data) {
                p.left = new Node(data);
            } else {
                p.right = new Node(data);
            }

        }
    }

    public Optional<Node> successor(Node root) {
        if (root.right == null) {
            return Optional.empty();
        }

        Node successor = root.right;
        Node current = root.right.left;

        while (current != null) {
            successor = current;
            current = current.left;
        }

        return Optional.of(successor);
    }

    public Optional<Node> predecessor(Node root) {
        if (root.left == null) {
            return Optional.empty();
        }

        Node predecessor = root.left;
        Node current = root.left.right;

        while (current != null) {
            predecessor = current;
            current = current.right;
        }

        return Optional.of(predecessor);
    }

    public boolean remove(int value) {
        if (root == null) {
            return false;
        }

        Node parent = null;
        Node current = root;

        while (current != null) {
           if (current.data == value) {
               break;
           }

           parent = current;

           if (current.data > value) {
               current = current.left;
           } else {
               current = current.right;
           }
        }

        if (current == null) {
            return false;
        }

        if (current.isLeaf()) {
            if (parent == null) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }

            return true;
        }

        Node previous = null;
        Node successor = current;
        Node c = current.right;

        while (c != null) {
            previous = successor;

            successor = c;

            c = c.left;
        }

        if (successor == current) {
            // there is no successor

            Node predecessor = current;
            c = current.left;

            while (c != null) {
                previous = predecessor;

                predecessor = c;

                c = c.right;
            }

            current.data = predecessor.data;

            if (predecessor == current.left) {
                current.left = predecessor.left;
            } else {
                previous.right = predecessor.left;
            }

        } else {
            current.data = successor.data;

            if (successor == current.right) {
                current.right = successor.right;
            } else {
                previous.left = successor.right;
            }
        }

        return true;
    }

    public Optional<Node> find(int value) {
        Node current = root;

        while (current != null) {
            if (current.data == value) {
                return Optional.of(current);
            }

            if (current.data < value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return Optional.empty();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node root) {
        if (root == null) {
            return 0;
        }

        return Integer.max(heightHelper(root.left), heightHelper(root.right)) + 1;
    }

    public boolean isBalanced() {
        return isBalancedHelper(root, new int[] {0});
    }

    private boolean isBalancedHelper(Node root, int[] height) {
        if (root == null) {
            height[0] = 0;
            return true;
        }

        int[] leftHeight = {0};
        int[] rightHeight = {0};
        boolean leftBalanced = isBalancedHelper(root.left, leftHeight);
        boolean rightBalanced = isBalancedHelper(root.right, rightHeight);

        height[0] = Integer.max(leftHeight[0], rightHeight[0]) + 1;

        return Math.abs(leftHeight[0] - rightHeight[0]) <= 1
                && leftBalanced
                && rightBalanced;

    }

    public List<Integer> inOrder() {
        List<Integer> result = new LinkedList<>();
        doInOrder(root, result);
        return result;
    }

    private void doInOrder(Node root, List<Integer> result) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node n = stack.pop();
                result.add(n.data);
                current = n.right;
            }
        }
    }

    public List<Integer> preOrder() {
        List<Integer> result = new LinkedList<>();
        doPreOrder(root, result);
        return result;
    }

    private void doPreOrder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }


        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node n = stack.pop();
            result.add(n.data);

            if (n.right != null) {
                stack.push(n.right);
            }

            if (n.left != null) {
                stack.push(n.left);
            }
        }
    }

    public List<Integer> postOrder() {
        List<Integer> result = new LinkedList<>();

        doPostOrder(root, result);

        return result;
    }

    private void doPostOrder(Node root, List<Integer> result) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        Set<Node> rightDone = new HashSet<>();

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node n = stack.peek();
                if (n.right == null || rightDone.contains(n)) {
                    result.add(stack.pop().data);
                } else {
                    rightDone.add(n);
                    current = n.right;
                }
            }
        }


    }

    public boolean isSymmetric() {
        return root == null || isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        }

        if (left != null && right == null) {
            return false;
        }

        if (left == null && right != null) {
            return false;
        }

        return left.data == right.data
                && isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right, right.left);

    }

    public void revert() {
        doRevert(root);
    }

    private void doRevert(Node root) {
        if (root == null) {
            return;
        }

        Node tmp = root.left;
        doRevert(root.right);
        root.left = root.right;
        doRevert(tmp);
        root.right = tmp;
    }

    public boolean isEqual(BST bst) {
        return isEqual(root, bst.root);
    }

    private boolean isEqual(Node t1, Node t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        return t1.data == t2.data
                && isEqual(t1.left, t2.left)
                && isEqual(t1.right, t2.right);
    }

    public List<Integer> getLeafs() {
        List<Integer> result = new ArrayList<>();

        doGetLeafs(root, result);

        return result;
    }

    private void doGetLeafs(Node root, List<Integer> result) {
        if (root != null) {
            if (root.isLeaf()) {
                result.add(root.data);
            } else {
                if (root.left != null) {
                    doGetLeafs(root.left, result);
                }
                if (root.right != null) {
                    doGetLeafs(root.right, result);
                }
            }
        }
    }

}
