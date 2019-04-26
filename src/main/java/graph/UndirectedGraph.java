package graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph {

    public static final class Edge {
        private int src;
        private int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }

        public int getSrc() {
            return src;
        }

        public int getDest() {
            return dest;
        }

        @Override
        public String toString() {
            return String.format("Edge(%d, %d)", src, dest);
        }
    }

    private List<List<Integer>> adj;

    private List<Edge> edges;

    private int n;

    public UndirectedGraph(List<Edge> edges, int n) {
        this.n = n;

        this.edges = edges;

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (Edge e: edges) {
            adj.get(e.getSrc()).add(e.getDest());
            adj.get(e.getDest()).add(e.getSrc());
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getN() {
        return n;
    }

    public List<Integer> getAdj(int src) {
        return adj.get(src);
    }
}
