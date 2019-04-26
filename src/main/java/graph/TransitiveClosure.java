package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TransitiveClosure {

    public boolean[][] transitiveClosure(DirectedGraph graph)  {
        int n = graph.getN();

        boolean[][] result = new boolean[n][n];

        for (int v = 0; v < n; v++) {
            bfs(graph, v, result);
        }

        return result;
    }

    private void bfs(DirectedGraph graph, int source, boolean[][] result) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        result[source][source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v: graph.getAdj(u)) {
                if (!result[source][v]) {
                    result[source][v] = true;
                    queue.add(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<DirectedGraph.Edge> edges = Arrays.asList(
                new DirectedGraph.Edge(0, 2),
                new DirectedGraph.Edge(1, 0),
                new DirectedGraph.Edge(3, 1)
        );

        // Set number of vertices in the graph
        final int N = 4;

        // create a graph from edges
        DirectedGraph graph = new DirectedGraph(edges, N);

        TransitiveClosure transitiveClosure = new TransitiveClosure();
        boolean[][] result = transitiveClosure.transitiveClosure(graph);

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }

}
