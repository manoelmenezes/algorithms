package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BipartiteDfs {

    public boolean isBipartite(UndirectedGraph graph) {
        Map<Integer, Integer> sets = new HashMap<>();

        boolean[] result = {true};

        doIsBipartite(graph, 0, sets, result, 0);

        return result[0];
    }

    private void doIsBipartite(UndirectedGraph graph, int source, Map<Integer, Integer> sets, boolean[] result, int parentSet) {

        sets.put(source, (parentSet + 1) % 2);

        for (int v: graph.getAdj(source)) {
            Integer vSet = sets.get(v);

            if (vSet == null) {
                doIsBipartite(graph, v, sets, result, sets.get(source));
            } else if (vSet.equals(sets.get(source))) {
                result[0] = false;
            }
        }
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<UndirectedGraph.Edge> edgesNonBipartite = Arrays.asList(
                new UndirectedGraph.Edge(0, 1), new UndirectedGraph.Edge(1, 2),
                new UndirectedGraph.Edge(1, 7), new UndirectedGraph.Edge(2, 3),
                new UndirectedGraph.Edge(3, 5), new UndirectedGraph.Edge(4, 6),
                new UndirectedGraph.Edge(4, 8), new UndirectedGraph.Edge(7, 8),
                new UndirectedGraph.Edge(1, 3)
                // if we remove 1->3 edge, graph is becomes Bipartite
        );

        // Set number of vertices in the graph
        final int N = 10;

        // create a graph from edges
        UndirectedGraph graphNonBipartite = new UndirectedGraph(edgesNonBipartite, N);

        List<UndirectedGraph.Edge> edgesBipartite = Arrays.asList(
                new UndirectedGraph.Edge(0, 1), new UndirectedGraph.Edge(1, 2),
                new UndirectedGraph.Edge(1, 7), new UndirectedGraph.Edge(2, 3),
                new UndirectedGraph.Edge(3, 5), new UndirectedGraph.Edge(4, 6),
                new UndirectedGraph.Edge(4, 8), new UndirectedGraph.Edge(7, 8)
        );

        // create a graph from edges
        UndirectedGraph graphBipartite = new UndirectedGraph(edgesBipartite, N);

        BipartiteDfs bipartite = new BipartiteDfs();

        System.out.println(bipartite.isBipartite(graphBipartite));
        System.out.println(bipartite.isBipartite(graphNonBipartite));
    }
}
