package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KruskalMst {

    public List<WeightedUndirectedGraph.Edge> mst(WeightedUndirectedGraph graph) {

        int n = graph.getN();

        DisjointSet ds = createDisjointSet(n);

        graph.getEdges().sort(Comparator.comparingInt(WeightedUndirectedGraph.Edge::getWeight));

        List<WeightedUndirectedGraph.Edge> edges = new ArrayList<>();
        int e = 0;
        while (edges.size() < n - 1) {
            WeightedUndirectedGraph.Edge edge = graph.getEdges().get(e);

            if (ds.find(edge.getSrc()) != ds.find(edge.getDest())) {
                ds.union(edge.getSrc(), edge.getDest());
                edges.add(edge);
            }
            e++;
        }

        return edges;
    }

    private DisjointSet createDisjointSet(int n) {
        DisjointSet ds = new DisjointSet();
        int[] universe = new int[n];
        for (int i = 0; i < n; i++) {
            universe[i] = i;
        }
        ds.makeSet(universe);

        return ds;
    }

    public static void main(String[] args) {
        // (u, v, w) triplet represent undirected edge from
        // vertex u to vertex v having weight w
        List<WeightedUndirectedGraph.Edge> edges = Arrays.asList(
                new WeightedUndirectedGraph.Edge(0, 1, 7), new WeightedUndirectedGraph.Edge(1, 2, 8),
                new WeightedUndirectedGraph.Edge(0, 3, 5), new WeightedUndirectedGraph.Edge(1, 3, 9),
                new WeightedUndirectedGraph.Edge(1, 4, 7), new WeightedUndirectedGraph.Edge(2, 4, 5),
                new WeightedUndirectedGraph.Edge(3, 4, 15), new WeightedUndirectedGraph.Edge(3, 5, 6),
                new WeightedUndirectedGraph.Edge(4, 5, 8), new WeightedUndirectedGraph.Edge(4, 6, 9),
                new WeightedUndirectedGraph.Edge(5, 6, 11)
        );

        // Number of vertices in the graph
        final int N = 7;

        WeightedUndirectedGraph graph = new WeightedUndirectedGraph(edges, N);

        // construct graph
        List<WeightedUndirectedGraph.Edge> mst = new KruskalMst().mst(graph);

        for (WeightedUndirectedGraph.Edge edge: mst) {
            System.out.println(edge);
        }
    }

}
