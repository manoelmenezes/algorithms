package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieMap implements Trie {

    public static final class NodeMap extends Node {

        private Map<Character, NodeMap> children;

        public NodeMap() {
            children = new HashMap<>();
        }

        @Override
        public boolean hasChildren() {
            return !children.isEmpty();
        }

        @Override
        public NodeMap getChild(char c) {
            return children.get(c);
        }

        @Override
        public NodeMap addChild(char c) {
            NodeMap child = new NodeMap();

            children.put(c, child);

            return child;
        }

        @Override
        public boolean removeChild(char c) {
            NodeMap removed = children.remove(c);

            return removed != null;
        }
    }

    private NodeMap root;

    @Override
    public void insert(String word) {
        if (root == null) {
            root = new NodeMap();
        }

        NodeMap current = root;

        for (char c: word.toCharArray()) {
            NodeMap child = current.getChild(c);
            if (child == null) {
                child = current.addChild(c);
            }
            current = child;
        }

        current.word();
    }

    @Override
    public boolean search(String word) {
        if (root == null) {
            return false;
        }

        NodeMap current = root;

        for (char c: word.toCharArray()) {
            NodeMap child = current.getChild(c);
            if (child == null) {
                return false;
            }
            current = child;
        }

        return current.isWord();
    }

    @Override
    public boolean delete(String word) {
        return doDelete(root, word);
    }

    private boolean doDelete(NodeMap root, String word) {
        if (root == null) {
            return false;
        }

        if (word.length() == 0) {
            boolean isWord = root.isWord();
            root.notWord();
            return isWord;
        }

        char c = word.charAt(0);

        NodeMap child = root.getChild(c);

        boolean deleted = doDelete(child, word.substring(1));

        if (deleted && !child.hasChildren()) {
            root.removeChild(c);
        }

        return deleted;
    }
}
