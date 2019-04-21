package trie;

import java.util.List;

public interface Trie {

    void insert(String word);

    boolean search(String word);

    boolean delete(String word);

    Node getRoot();

    default void insertAll(List<String> words) {
        words.forEach(word -> insert(word));
    }

    default String getLongestCommonPrefix() {
        Node current = getRoot();

        if (current == null) {
            return "";
        }

        StringBuilder longestCommonPrefix = new StringBuilder();

        while (current != null && !current.isWord() && current.childrenSize() == 1) {
             KeyValue keyValue = current.getChildren().get(0);
             longestCommonPrefix.append(keyValue.getKey());
             current = keyValue.getNode();
        }

        return longestCommonPrefix.toString();

    }

    final class KeyValue {
        private final char key;
        private final Node node;

        public KeyValue(char key, Node node) {
            this.key = key;
            this.node = node;
        }

        public char getKey() {
            return key;
        }

        public Node getNode() {
            return node;
        }
    }

    abstract class Node {

        private boolean isWord;

        public abstract boolean hasChildren();

        public abstract List<KeyValue> getChildren();

        public abstract Node getChild(char c);

        public abstract Node addChild(char c);

        public abstract boolean removeChild(char c);

        public abstract long childrenSize();

        public boolean isWord() {
            return isWord;
        }

        public void word() {
            isWord = true;
        }

        public void notWord() {
            isWord = false;
        }



    }
}
