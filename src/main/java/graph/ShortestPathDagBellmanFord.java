package graph;

import java.util.Arrays;
import java.util.List;

public class ShortestPathDagBellmanFord {

    public int[] shortesPath(WeightedDirectedGraph graph, int source) {
        TopologicalSort topologicalSort = new TopologicalSort();
        List<Integer> ts = topologicalSort.topologicalSort(graph);

        int[] distances = new int[graph.getN()];

        for (int i = 0; i < graph.getN(); i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        distances[source] = 0;

        for (int u: ts) {

            for (WeightedDirectedGraph.Node n: graph.getAdj(u)) {
                int v = n.getTo();
                int w = n.getWeight();

                if (distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
                    distances[v] = distances[u] + w;
                }
            }

        }

        return distances;
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<WeightedDirectedGraph.Edge> edges = Arrays.asList(
                new WeightedDirectedGraph.Edge(0, 6, 2), new WeightedDirectedGraph.Edge(1, 2, -4),
                new WeightedDirectedGraph.Edge(1, 4, 1), new WeightedDirectedGraph.Edge(1, 6, 8),
                new WeightedDirectedGraph.Edge(3, 0, 3), new WeightedDirectedGraph.Edge(3, 4, 5),
                new WeightedDirectedGraph.Edge(5, 1, 2), new WeightedDirectedGraph.Edge(7, 0, 6),
                new WeightedDirectedGraph.Edge(7, 1, -1), new WeightedDirectedGraph.Edge(7, 3, 4),
                new WeightedDirectedGraph.Edge(7, 5, -4)
        );

        // Set number of vertices in the graph
        final int N = 8;

        // create a graph from given edges
        WeightedDirectedGraph graph = new WeightedDirectedGraph(edges, N);

        // source vertex
        int source = 7;

        ShortestPathDagBellmanFord shortestPathDagBellmanFord = new ShortestPathDagBellmanFord();
        int[] result = shortestPathDagBellmanFord.shortesPath(graph, source);

        System.out.println(Arrays.toString(result));
    }

}
