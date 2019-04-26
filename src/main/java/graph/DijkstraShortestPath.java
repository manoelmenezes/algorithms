package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

    public DijkstraOutput shortestPath(WeightedDirectedGraph graph, int source) {
        PriorityQueue<VertexDistance> distances = new PriorityQueue<>();

        int[] dist = new int[graph.getN()];
        dist[source] = 0;

        int[] prev = new int[graph.getN()];

        for (int i = 0; i < graph.getN(); i++) {
            if (i != source) {
                dist[i] = Integer.MAX_VALUE;
            }
            prev[i] = -1;
            distances.add(new VertexDistance(i, dist[i]));
        }

        while (!distances.isEmpty()) {
            VertexDistance distance = distances.poll();

            for (WeightedDirectedGraph.Node n: graph.getAdj(distance.getVertex())) {
                if (n.getWeight() + distance.getDistance() < dist[n.getTo()]) {
                    dist[n.getTo()] = n.getWeight() + distance.getDistance();
                    prev[n.getTo()] = distance.getVertex();
                    VertexDistance vd = new VertexDistance(n.getTo(), dist[n.getTo()]);
                    distances.remove(vd);
                    distances.add(vd);
                }
            }
        }

        return new DijkstraOutput(dist, prev);
    }

    public static final class DijkstraOutput {
        private int[] dist;
        private int[] prev;

        public DijkstraOutput(int[] dist, int[] prev) {
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

    public static final class VertexDistance implements Comparable<VertexDistance> {
        private int vertex;
        private int distance;

        public VertexDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof VertexDistance)) {
                return false;
            }

            VertexDistance vd = (VertexDistance) obj;

            return vertex == vd.vertex;
        }

        @Override
        public int hashCode() {
            int result = 17;

            result = 31 * result + vertex;

            return result;
        }

        @Override
        public int compareTo(VertexDistance o) {
            return distance - o.distance;
        }
    }

    public static void main(String[] args) {
        // initialize edges as per above diagram
        // (u, v, w) triplet represent undirected edge from
        // vertex u to vertex v having weight w
        List<WeightedDirectedGraph.Edge> edges = Arrays.asList(
                new WeightedDirectedGraph.Edge(0, 1, 10), new WeightedDirectedGraph.Edge(0, 4, 3),
                new WeightedDirectedGraph.Edge(1, 2, 2), new WeightedDirectedGraph.Edge(1, 4, 4),
                new WeightedDirectedGraph.Edge(2, 3, 9), new WeightedDirectedGraph.Edge(3, 2, 7),
                new WeightedDirectedGraph.Edge(4, 1, 1), new WeightedDirectedGraph.Edge(4, 2, 8),
                new WeightedDirectedGraph.Edge(4, 3, 2)
        );

        // Set number of vertices in the graph
        final int N = 5;

        // construct graph
        WeightedDirectedGraph graph = new WeightedDirectedGraph(edges, N);

        DijkstraOutput dijkstraOutput = new DijkstraShortestPath().shortestPath(graph, 0);

        for (int i = 1; i < graph.getN(); i++) {
            List<Integer> path = getPath(dijkstraOutput.prev, i);
            System.out.println(String.format("Path from %d to %d has minimum cost of %d and path is %s", 0, i, dijkstraOutput.getDist()[i], path.toString()));
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
