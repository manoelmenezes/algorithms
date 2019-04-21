package trie;

public interface Trie {

    void insert(String word);

    boolean search(String word);

    boolean delete(String word);

    abstract class Node {

        private boolean isWord;

        public abstract boolean hasChildren();

        public abstract Node getChild(char c);

        public abstract Node addChild(char c);

        public abstract boolean removeChild(char c);

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
