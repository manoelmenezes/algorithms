package graph;

import java.util.ArrayList;
import java.util.List;

public class WeightDirectedGraph {

    private int N;

    private List<Edge> edges;

    private List<List<Edge>> adj;

    public WeightDirectedGraph(List<Edge> edges, int N) {
        this.N = N;
        this.edges = new ArrayList<>(edges);
        this.adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            this.adj.add(new ArrayList<>());
        }
        for (Edge e: this.edges) {
            this.adj.get(e.getSrc()).add(e);
        }
    }

    public int getN() {
        return N;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getAdj(int src) {
        return adj.get(src);
    }

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

}
