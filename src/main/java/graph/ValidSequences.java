package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidSequences {

    public static final class ValidSequenceOutput {
        private final boolean containsCycle;
        private final List<Integer> sequence;

        public ValidSequenceOutput(boolean containsCycle, List<Integer> sequence) {
            this.containsCycle = containsCycle;
            this.sequence = sequence;
        }

        public List<Integer> getSequence() {
            return sequence;
        }

        public boolean isContainsCycle() {
            return containsCycle;
        }
    }

    public static ValidSequenceOutput isValid(List<List<Integer>> sequences) {

        DirectedGraph graph = buildDirectedGraph(sequences);

        return containsCycle(graph);
    }

    private static ValidSequenceOutput containsCycle(DirectedGraph graph) {

        Set<Integer> visited = new HashSet<>();

        for (Map.Entry<Integer, List<Integer>> e: graph.getAdjMap().entrySet()) {
            if (visited.contains(e.getKey())) {
                return new ValidSequenceOutput(true, null);
            }

            if (doContainsCycle(graph, e.getKey(), visited)) {
                return new ValidSequenceOutput(true, null);
            }
        }

        List<Integer> topSort = new ArrayList<>();
        topSort(graph, topSort);

        return new ValidSequenceOutput(false, topSort);
    }

    private static void topSort(DirectedGraph graph, List<Integer> topSort) {
        Set<Integer> visited = new HashSet<>();

        for (int v = 0; v < graph.getN(); v++) {
            if (!visited.contains(v)) {
                doTopSort(v, graph, visited, topSort);
            }
        }
    }

    private static void doTopSort(int v, DirectedGraph graph, Set<Integer> visited, List<Integer> topSort) {
        visited.add(v);
        for (int u: graph.getAdj(v)) {
            if (!visited.contains(u)) {
                doTopSort(u, graph, visited, topSort);
            }
        }
        topSort.add(0, v);
    }


    private static boolean doContainsCycle(DirectedGraph graph, Integer n, Set<Integer> visited) {
        if (visited.contains(n)) {
            return false;
        }
        visited.add(n);
        for (Integer m: graph.getAdjMap().get(n)) {
            if (visited.contains(m)) {
                return true;
            }
            doContainsCycle(graph, m, visited);
        }
        visited.remove(n);
        return false;
    }

    private static DirectedGraph buildDirectedGraph(List<List<Integer>> sequences) {
        Set<DirectedGraph.Edge> edges = new HashSet<>();
        Set<Integer> nodes = new HashSet<>();

        for (List<Integer> seq: sequences) {

            nodes.add(seq.get(0));

            for (int i = 1; i < seq.size(); i++) {

                nodes.add(seq.get(i));

                edges.add(new DirectedGraph.Edge(seq.get(i - 1), seq.get(i)));

            }

        }

        return new DirectedGraph(new ArrayList<>(edges), nodes);
    }


}
