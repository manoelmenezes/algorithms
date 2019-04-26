package graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedDirectedGraph {

    public static final class Edge {
        private int src;
        private int dest;
        private int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
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

    public WeightedDirectedGraph(List<Edge> edges, int N) {
        this.edges = edges;

        this.N = N;

        this.adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (Edge e: edges) {
            adj.get(e.getSrc()).add(new Node(e.getDest(), e.getWeight()));
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
