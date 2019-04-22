package trie;

public abstract class AbstractTrie implements Trie {

    public static final int ALPHABET_SIZE = 26;

    protected Node root;

    protected abstract Node createNode();

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public void insert(final String word) {
        String tmp = word.toLowerCase();

        if (root == null) {
            root = createNode();
        }

        Node current = root;

        for (char c: tmp.toCharArray()) {
            Node child = current.getChild(c);

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

        Node current = root;

        for (char c: tmp.toCharArray()) {
            Node child = current.getChild(c);

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

    private boolean doDelete(Node root, String word) {
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

        Node child = root.getChild(c);

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
