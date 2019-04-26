package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TotalPathsExactEdgesDigraph {

    public static final class Node {
        private int vertex;
        private int length;

        public Node(int vertex, int length) {
            this.vertex = vertex;
            this.length = length;
        }
    }

    public int getTotalPaths(DirectedGraph graph, int source, int destination, int edges) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(source, 0));

        int totalPaths = 0;
        while (!queue.isEmpty()) {
            Node n = queue.poll();

            if (n.length >= edges) {
                continue;
            }

            for (int v: graph.getAdj(n.vertex)) {
                if (v == destination && n.length + 1 == edges) {
                    totalPaths++;
                }
                queue.add(new Node(v, n.length + 1));
            }
        }

        return totalPaths;
    }

    public static void main(String[] args) {
        List<DirectedGraph.Edge> edges = List.of(
                new DirectedGraph.Edge(0, 6), new DirectedGraph.Edge(0, 1),
                new DirectedGraph.Edge(1, 6), new DirectedGraph.Edge(1, 5),
                new DirectedGraph.Edge(1, 2), new DirectedGraph.Edge(2, 3),
                new DirectedGraph.Edge(3, 4), new DirectedGraph.Edge(5, 2),
                new DirectedGraph.Edge(5, 3), new DirectedGraph.Edge(5, 4),
                new DirectedGraph.Edge(6, 5), new DirectedGraph.Edge(7, 6),
                new DirectedGraph.Edge(7, 1)
        );


        // Number of nodes in the graph
        int N = 8;

        // create a graph from edges
        DirectedGraph graph = new DirectedGraph(edges, N);

        int src = 0, dest = 3, m = 4;

        TotalPathsExactEdgesDigraph totalPathsExactEdgesDigraph = new TotalPathsExactEdgesDigraph();
        int totalPaths = totalPathsExactEdgesDigraph.getTotalPaths(graph, src, dest, m);

        System.out.println("Total paths: " + totalPaths);

    }

}
