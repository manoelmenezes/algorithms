package trie;

public class TrieArray implements Trie {

    public static final int ALPHABET_SIZE = 26;

    private NodeArray root;

    public static final class NodeArray extends Node {

        private NodeArray[] children;

        public NodeArray() {
            children = new NodeArray[ALPHABET_SIZE];
        }

        @Override
        public boolean hasChildren() {
            for (NodeArray n: children) {
                if (n != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public NodeArray getChild(char c) {
            return children[c - 'a'];
        }

        @Override
        public NodeArray addChild(char c) {
            NodeArray child = new NodeArray();

            children[c - 'a'] = child;

            return child;
        }

        @Override
        public boolean removeChild(char c) {
            NodeArray child = children[c - 'a'];

            children[c - 'a'] = null;

            return child != null;
        }
    }

    @Override
    public void insert(final String word) {
        String tmp = word.toLowerCase();

        if (root == null) {
            root = new NodeArray();
        }

        NodeArray current = root;

        for (char c: tmp.toCharArray()) {
            NodeArray child = current.getChild(c);

            if (child == null) {
                child = current.addChild(c);
            }

            current = child;
        }

        current.word();

    }

    @Override
    public boolean search(final String word) {
        String tmp = word.toLowerCase();

        if (root == null) {
            return false;
        }

        NodeArray current = root;

        for (char c: tmp.toCharArray()) {
            NodeArray child = current.getChild(c);

            if (child == null) {
                return false;
            }

            current = child;
        }

        return current.isWord();
    }

    @Override
    public boolean delete(final String word) {
        return doDelete(root, word.toLowerCase());
    }

    private boolean doDelete(NodeArray root, String word) {
        if (root == null) {
            return false;
        }

        if (word.length() == 0 && root.isWord()) {
            root.notWord();
            return true;
        }

        if (word.length() == 0) {
            return false;
        }

        char c = word.charAt(0);

        NodeArray child = root.getChild(c);

        if (child == null) {
            return false;
        }

        boolean deleted = doDelete(child, word.substring(1));

        if (deleted && !child.hasChildren()) {
            root.removeChild(c);
        }

        return deleted;
    }


}
