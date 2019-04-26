package graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedUndirectedGraph {

    public static final class Edge implements Comparable<Edge> {
        private int src;
        private int dest;
        private int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }

        public int getSrc() {
            return src;
        }

        public int getDest() {
            return dest;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return String.format("Edge(%d, %d)", src, dest);
        }
    }

    public static final class Node {
        private int to;
        private int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    private List<List<Node>> adj;

    private List<Edge> edges;

    private int N;

    public WeightedUndirectedGraph(List<Edge> edges, int N) {
        this.edges = edges;

        this.N = N;

        this.adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (Edge e: edges) {
            adj.get(e.getSrc()).add(new Node(e.getDest(), e.getWeight()));
            adj.get(e.getDest()).add(new Node(e.getSrc(), e.getWeight()));
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Node> getAdj(int src) {
        return adj.get(src);
    }

    public int getN() {
        return N;
    }
}
