package graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A connected undirected graph is 2-edge connected if even removing one of
 * its any edge, the graph continues connected.
 *
 * One approach is to do a BFS or DFS for each edge of the graph considering
 * the edge removed, if it is connected for each execution of BFS/DFS the
 * graph is 2-edge connected.
 *
 * Another approach is to count the degree for each vertex, the graph
 * is 2-edge connected in case the degree of any edge is equals or greater
 * to 2.
 */
public class TwoEdgeConnected {

    public static final class TwoEdgeConnectedOutput {
        private boolean isTwoEdge;
        private Map<Integer, Integer> degree;
        private List<UndirectedGraph.Edge> bridges;

        public TwoEdgeConnectedOutput(boolean isTwoEdge,
                                      Map<Integer, Integer> degree,
                                      List<UndirectedGraph.Edge> bridges) {
            this.isTwoEdge = isTwoEdge;
            this.degree = degree;
            this.bridges = bridges;
        }
    }

    public TwoEdgeConnectedOutput isTwoEdgeConnected(UndirectedGraph graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Map<Integer, Integer> degree = new HashMap<>();
        degree.put(0, 0);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            degree.put(u, graph.getAdj(u).size());

            for (int v: graph.getAdj(u)) {
                if (!degree.containsKey(v)) {
                    degree.put(v, 0);
                    queue.add(v);
                }
            }
        }

        return buildOutput(graph, degree);
    }

    private TwoEdgeConnectedOutput buildOutput(UndirectedGraph graph, Map<Integer, Integer> degree) {
        List<UndirectedGraph.Edge> bridges = graph.getEdges().stream()
                .filter(e -> degree.get(e.getSrc()) < 2 || degree.get(e.getDest()) < 2)
                .collect(Collectors.toList());

        return new TwoEdgeConnectedOutput(bridges.size() == 0, degree, bridges);
    }

    public static void main(String[] args) {
        // initialize edges as per above diagram
        // (u, v, w) triplet represent undirected edge from
        // vertex u to vertex v having weight w
        List<UndirectedGraph.Edge> edges = Arrays.asList(
                new UndirectedGraph.Edge(0, 2), new UndirectedGraph.Edge(1, 2),
                new UndirectedGraph.Edge(2, 3), new UndirectedGraph.Edge(2, 4),
                new UndirectedGraph.Edge(3, 4), new UndirectedGraph.Edge(3, 5)
        );

        // Number of vertices in the graph
        final int N = 6;

        // construct graph
        UndirectedGraph graph = new UndirectedGraph(edges, N);

        TwoEdgeConnected twoEdgeConnected = new TwoEdgeConnected();

        TwoEdgeConnectedOutput output = twoEdgeConnected.isTwoEdgeConnected(graph);

        if (output.isTwoEdge) {
            System.out.println("It is 2-edge connected");
        } else {
            System.out.println("It is not 2-edge connected");
        }

        System.out.println(output.isTwoEdge);
        System.out.println(output.bridges);


    }
}
