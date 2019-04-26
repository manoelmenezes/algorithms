package graph;

import java.util.*;

public class TopologicalSort {

    public List<Integer> topologicalSort(WeightedDirectedGraph graph) {
        List<Integer> result = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < graph.getN(); i++) {
            if (!visited.contains(i)) {
                doTopologicalSort(graph, i, visited, result);
            }
        }

        return result;
    }

    private void doTopologicalSort(WeightedDirectedGraph graph, int source, Set<Integer> visited, List<Integer> result) {
        visited.add(source);

        for (WeightedDirectedGraph.Node v: graph.getAdj(source)) {
            if (!visited.contains(v.getTo())) {
                doTopologicalSort(graph, v.getTo(), visited, result);
            }
        }

        result.add(0, source);
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<WeightedDirectedGraph.Edge> edges = Arrays.asList(
                new WeightedDirectedGraph.Edge(0, 6, 1), new WeightedDirectedGraph.Edge(1, 2, 4), new WeightedDirectedGraph.Edge(1, 4, 7),
                new WeightedDirectedGraph.Edge(1, 6, 2), new WeightedDirectedGraph.Edge(3, 0, 5), new WeightedDirectedGraph.Edge(3, 4, 8),
                new WeightedDirectedGraph.Edge(5, 1, 3), new WeightedDirectedGraph.Edge(7, 0, 6), new WeightedDirectedGraph.Edge(7, 1, 9)
        );

        // Set number of vertices in the graph
        final int N = 8;

        // create a graph from edges
        WeightedDirectedGraph graph = new WeightedDirectedGraph(edges, N);

        List<Integer> topologicalSort = new TopologicalSort().topologicalSort(graph);

        System.out.println(topologicalSort);

    }

}
