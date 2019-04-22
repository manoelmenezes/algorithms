package trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrieMap extends AbstractTrie {

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
        public List<KeyValue> getChildren() {
            return IntStream.range(0, ALPHABET_SIZE)
                    .mapToObj(index -> (char)(index + 'a'))
                    .filter(c -> children.get(c) != null)
                    .map(c -> new KeyValue(c, children.get(c)))
                    .collect(Collectors.toList());
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

        @Override
        public long childrenSize() {
            return children.size();
        }
    }

    @Override
    protected Node createNode() {
        return new NodeMap();
    }
}
