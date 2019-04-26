package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BellmanFordShortestPath {

    public BellmanFordOutput shortestPath(WeightedDirectedGraph graph, int source) {
        int[] dist = new int[graph.getN()];

        for (int i = 0; i < graph.getN(); i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        int[] prev = new int[graph.getN()];
        prev[source] = -1;

        int k = graph.getN();

        while (--k > 0) {

            for (WeightedDirectedGraph.Edge e: graph.getEdges()) {
                int src = e.getSrc();
                int dest = e.getDest();
                int weight = e.getWeight();

                if (dist[src] != Integer.MAX_VALUE && dist[src] + weight < dist[dest]) {
                    dist[dest] = dist[src] + weight;
                    prev[dest] = src;
                }
            }

        }

        for (WeightedDirectedGraph.Edge e: graph.getEdges()) {
            int src = e.getSrc();
            int dest = e.getDest();
            int weight = e.getWeight();

            if (dist[src] != Integer.MAX_VALUE && dist[src] + weight < dist[dest]) {
                throw new IllegalArgumentException("Graph has negative cycle.");
            }
        }

        return new BellmanFordOutput(dist, prev);
    }

    public static final class BellmanFordOutput {
        private int[] dist;
        private int[] prev;

        public BellmanFordOutput(int[] dist, int[] prev) {
            this.dist = dist;
            this.prev = prev;
        }

        public int[] getDist() {
            return dist;
        }

        public int[] getPrev() {
            return prev;
        }
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<WeightedDirectedGraph.Edge> edges = Arrays.asList(
                // (x, y, w) -> edge from x to y having weight w
                new WeightedDirectedGraph.Edge( 0, 1, -1 ), new WeightedDirectedGraph.Edge( 0, 2, 4 ),
                new WeightedDirectedGraph.Edge( 1, 2, 3 ), new WeightedDirectedGraph.Edge( 1, 3, 2 ),
                new WeightedDirectedGraph.Edge( 1, 4, 2 ), new WeightedDirectedGraph.Edge( 3, 2, 5 ),
                new WeightedDirectedGraph.Edge( 3, 1, 1 ), new WeightedDirectedGraph.Edge( 4, 3, -3 )
        );

        int N = 5;
        WeightedDirectedGraph graph = new WeightedDirectedGraph(edges, N);

        BellmanFordOutput output = new BellmanFordShortestPath().shortestPath(graph, 0);

        for (int i = 0; i < graph.getN(); i++) {
            List<Integer> path = getPath(output.prev, i);
            System.out.println(String.format("Path from %d to %d has minimum cost of %d and path is %s", 0, i, output.getDist()[i], path.toString()));
        }

    }

    private static List<Integer> getPath(int[] prev, int i) {
        List<Integer> path = new LinkedList<>();

        while (i != -1) {
            path.add(0, i);
            i = prev[i];
        }

        return path;
    }

}
