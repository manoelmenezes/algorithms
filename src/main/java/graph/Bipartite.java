package graph;

import java.util.*;

public class Bipartite {

    public boolean isBipartite(UndirectedGraph graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Map<Integer, Integer> sets = new HashMap<>();
        sets.put(0, 0);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            int set = sets.get(u);

            for (int v: graph.getAdj(u)) {
                Integer vSet = sets.get(v);
                if (vSet == null) {
                    sets.put(v, (set + 1) % 2);
                    queue.add(v);
                } else if (vSet == set) {
                    return false;
                }
            }
        }

        return true;

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

        Bipartite bipartite = new Bipartite();

        System.out.println(bipartite.isBipartite(graphBipartite));
        System.out.println(bipartite.isBipartite(graphNonBipartite));
    }

}
