package trie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrieArray extends AbstractTrie {

    @Override
    protected Node createNode() {
        return new NodeArray();
    }

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
        public List<KeyValue> getChildren() {
            return IntStream.range(0, ALPHABET_SIZE)
                    .filter(index -> children[index] != null)
                    .mapToObj(index -> new KeyValue((char)(index + 'a'), children[index]))
                    .collect(Collectors.toList());
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

        @Override
        public long childrenSize() {
            return Arrays.stream(children)
                    .filter(c -> c != null)
                    .count();

        }
    }




}
