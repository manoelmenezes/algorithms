package graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {

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
    }

    private List<List<Integer>> adj;

    private int N;

    public DirectedGraph(List<Edge> edges, int N) {
        this.N = N;

        this.adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (Edge e: edges) {
            adj.get(e.getSrc()).add(e.getDest());
        }
    }

    public List<Integer> getAdj(int src) {
        return adj.get(src);
    }

    public int getN() {
        return N;
    }
}
